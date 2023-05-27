package com.example.demo.web;

import com.example.demo.model.dto.news.CreateOrUpdateNewsDTO;
import com.example.demo.model.dto.news.SearchNewsDTO;
import com.example.demo.model.user.TechUserDetails;
import com.example.demo.service.NewsService;
import jakarta.validation.Valid;
import com.example.demo.exception.ObjectNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // Getting all the news and returning the news view
    @GetMapping("/news/all")
    public String allNews(Model model, @PageableDefault(sort = "creationDate", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable pageable) {

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

    // POST request
    @PostMapping("/news/add")
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

        addNewsModel.setCreationDate(addNewsModel.getCreationDate());
        addNewsModel.setValidFrom(addNewsModel.getValidFrom());
        addNewsModel.setValidTo(addNewsModel.getValidTo());

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

    // {id} representing the UUID of the news to be edited
    // Model used for adding attributes for rendering the view
    @GetMapping("/news/{id}/edit")
    public String edit(@PathVariable("id") UUID uuid, Model model) {

        //userService being used to get news details based ot the provided UUID
        //If news found, adding it to the model and returning "details" view
        // If News is not found throwing an exception
        var news = newsService.getNewsEditDetails(uuid).
                orElseThrow(() -> new ObjectNotFoundException("News with ID: " + uuid + " was not found!"));

        model.addAttribute("news", news);

        return "details";
    }

    //Deleting the News by provided UUID, where {id} is the Path
    @PreAuthorize("isOwner(#uuid)") // specifying an authorization condition -> "isOwner(#uuid)" determines if authorized user is owner of the news item
    @DeleteMapping("/news/{id}")
    public String deleteNews(@PathVariable("id") UUID uuid) {
        newsService.deleteNewsById(uuid);

        return "redirect:/news/all";
    }

    // Getting the news based of UUID and rendering the "details" view
    @GetMapping("/news/{id}")
    public String getNewsDetails(@PathVariable("id") UUID uuid, Model model) {

        var newsDto = newsService.findNewsByUUID(uuid).
                orElseThrow(() -> new ObjectNotFoundException("News with ID " + uuid + " was not found!"));

        model.addAttribute("news", newsDto);

        return "details";
    }

}
