package com.example.demo.web;

import com.example.demo.model.dto.news.CreateOrUpdateNewsDTO;
import com.example.demo.model.dto.news.SearchNewsDTO;
import com.example.demo.model.dto.news.UpdateDTO;
import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.user.TechUserDetails;
import com.example.demo.repository.NewsRepository;
import com.example.demo.service.NewsService;
import jakarta.validation.Valid;
import com.example.demo.exception.ObjectNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.UUID;

@Controller
public class NewsController {

    private final NewsService newsService;

    private final NewsRepository newsRepository;

    public NewsController(NewsService newsService, NewsRepository newsRepository) {
        this.newsService = newsService;
        this.newsRepository = newsRepository;
    }

    // Getting all the news and returning the news view
    @GetMapping("/news/all")
    public String allNews(Model model, @PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC, page = 0, size = 5) Pageable pageable) {

        model.addAttribute("news", newsService.getAllNews(pageable));

        return "news";
    }

    // Checking if model contains "addNewsModel" attribute, if not adding new instance
    // of "CreateOrUpdateNewsDTO" class to model and returning "news-add" view
    @GetMapping("/news/add")
    public String addNews(Model model) {
        if(!model.containsAttribute("addNewsModel")) {
            model.addAttribute("addNewsModel", new CreateOrUpdateNewsDTO());
        }

        return "news-add";
    }

    // POST request for adding new News article
    @PostMapping(value = "/news/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public String addNews(@Valid CreateOrUpdateNewsDTO addNewsModel, // validating the form data submitted for adding news
                          BindingResult bindingResult, // Object holding the validation and binding errors
                          RedirectAttributes redirectAttributes, // storing flash attributes for redirecting
                          @AuthenticationPrincipal TechUserDetails userDetails) { // authenticated user details

        // if binding errors, flash attributes are added and redirecting to "/news/add"
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addNewsModel", addNewsModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewsModel",
                    bindingResult);

            return "redirect:/news/add";
        }

        // if no errors, adding the news and redirecting to "/news/all"
        newsService.addNews(addNewsModel, userDetails);

        return "redirect:/news/all";

    }

    // if errors, adding attributes and rendering "news-search".
    // If there are no errors "searchNewsDTO" added as attribute and using newsService to search based on the DTO
    @GetMapping("/news/search")
    public String searchQuery(@Valid SearchNewsDTO searchNewsDTO,
                              BindingResult bindingResult,
                              Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("searchNewsModel", searchNewsDTO);
            model.addAttribute("org.springframework.validation.BindingResult.searchNewsModel", bindingResult);

            return "news-search";
        }

        if(!model.containsAttribute("searchNewsModel")) {
            model.addAttribute("searchNewsModel", searchNewsDTO);
        }

        if(!searchNewsDTO.isEmpty()) {
            model.addAttribute("news", newsService.searchNews(searchNewsDTO));
        }

        return "news-search";
    }

    //Deleting the News by provided UUID, where {id} is the Path
    @PreAuthorize("isPublisher(#uuid)") // specifying an authorization condition -> "isPublisher(#uuid)" determines if authorized user is publisher of the news item
    @DeleteMapping("/news/{id}")
    public String deleteNews(@PathVariable("id") UUID uuid) {
        newsService.deleteNewsById(uuid);

        return "redirect:/news/all";
    }

    // Getting the news based of UUID and rendering the "details" view
    @GetMapping("/news/{id}/details")
    public String getNewsDetail(@PathVariable("id") UUID uuid, Model model) {

        var newsDto = newsService.findNewsByUUID(uuid).
                orElseThrow(() -> new ObjectNotFoundException("News with ID " + uuid + " was not found!"));

        model.addAttribute("article", newsDto);

        return "details";
    }

    // Rendering the update view
    @GetMapping("/news/edit/{id}")
    public String editNews(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("updateNewsModel", newsService.getNewsById(id));
        return "update";
    }

    // Post request to change the values of an already existing article
    @PostMapping("/news/edit/{id}")
    public String updateNews(@PathVariable UUID id, @ModelAttribute("updateNewsModel") UpdateDTO updateNewsModel) {

        NewsEntity existingNews = newsService.getNewsById(id);

        existingNews.setTitle(updateNewsModel.getTitle());
        existingNews.setText(updateNewsModel.getText());
        existingNews.setPhotoLink(updateNewsModel.getPhotoLink());

        newsService.updateArticle(existingNews);

        return "redirect:/news/all";

    }
}
