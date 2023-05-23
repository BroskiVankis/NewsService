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

import java.util.UUID;

@Controller
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news/all")
    public String allNews(Model model, @PageableDefault(sort = "creationDate", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable pageable) {

        model.addAttribute("news", newsService.getAllNews(pageable));

        return "news";
    }

    @GetMapping("/news/add")
    public String addNews(Model model) {
        if(!model.containsAttribute("addNewsModel")) {
            model.addAttribute("addNewsModel", new CreateOrUpdateNewsDTO());
        }

        return "news-add";
    }

    @PostMapping("/news/add")
    public String addNews(@Valid CreateOrUpdateNewsDTO addNewsModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          @AuthenticationPrincipal TechUserDetails userDetails) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addNewsModel", addNewsModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewsModel",
                    bindingResult);

            return "redirect:/news/add";
        }

        newsService.addNews(addNewsModel, userDetails);

        return "redirect:/news/all";

    }

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

    @GetMapping("/news/{id}/edit")
    public String edit(@PathVariable("id") UUID uuid, Model model) {

        var news = newsService.getNewsEditDetails(uuid).
                orElseThrow(() -> new ObjectNotFoundException("News with ID: " + uuid + " was not found!"));

        model.addAttribute("news", news);

        return "details";
    }

    @PreAuthorize("isOwner(#uuid)")
    @DeleteMapping("/news/{id}")
    public String deleteNews(@PathVariable("id") UUID uuid) {
        newsService.deleteNewsById(uuid);

        return "redirect:/news/all";
    }

    @GetMapping("/news/{id}")
    public String getNewsDetails(@PathVariable("id") UUID uuid, Model model) {

        var newsDto = newsService.findNewsByUUID(uuid).
                orElseThrow(() -> new ObjectNotFoundException("News with ID " + uuid + " was not found!"));

        model.addAttribute("news", newsDto);

        return "details";
    }

}
