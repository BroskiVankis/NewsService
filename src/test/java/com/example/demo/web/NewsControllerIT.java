package com.example.demo.web;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.model.dto.news.CreateOrUpdateNewsDTO;
import com.example.demo.model.dto.news.NewsDetailDTO;
import com.example.demo.model.dto.news.SearchNewsDTO;
import com.example.demo.model.dto.news.UpdateDTO;
import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.user.TechUserDetails;
import com.example.demo.repository.NewsRepository;
import com.example.demo.service.NewsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;



public class NewsControllerIT {

    @Mock
    private NewsService newsService;

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private TechUserDetails userDetails;

    @Mock
    private NewsController newsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        newsController = new NewsController(newsService, newsRepository);
    }

    //Verifying the getAllNews() method of the newsService is called once with peagable object
    // addAttribute() method of model is called once with attribute name news and any value
    @Test
    public void testAllNews() {
        Pageable pageable = Pageable.ofSize(5); // Creating Pageable object with size 5
        newsController.allNews(model, pageable); // Calling Method from controller and passing the objects
        verify(newsService, times(1)).getAllNews(pageable); //verifying if method of the mock object is being called once with the pageable object
        verify(model, times(1)).addAttribute(eq("news"), any()); // verifying the behaviour of model mock object
    }

    //This test ensures that when method is called, it interacts correctly with the model object
    // by calling the addAttribute method with attribute name and value
    @Test
    public void testAddNews() {
        when(model.containsAttribute("addNewsModel")).thenReturn(false);
        newsController.addNews(model);
        verify(model, times(1)).addAttribute(eq("addNewsModel"), any());
    }

    //Testing if controller handles bindingErrors correctly
    @Test
    public void testAddNewsWithBindingErrors() {
        // Creating new instance of CreateOrUpdateNewsDTO that represents the DTO used to add or update news
        CreateOrUpdateNewsDTO addNewsModel = new CreateOrUpdateNewsDTO();
        when(bindingResult.hasErrors()).thenReturn(true); //Setting stub, specifying that when hasErrors() of bindingResult is called, should return true. Simulating scenario where there are errors
        newsController.addNews(addNewsModel, bindingResult, redirectAttributes, userDetails); // This is the method being tested
        verify(redirectAttributes, times(1)).addFlashAttribute(eq("addNewsModel"), eq(addNewsModel));
        verify(redirectAttributes, times(1)).addFlashAttribute(eq("org.springframework.validation.BindingResult.addNewsModel"), eq(bindingResult));
        verify(newsService, never()).addNews(any(), any()); // never() ensures the method is not involked during the test case
    }

    @Test
    public void testAddNewsWithNoBindingErrors() {
        CreateOrUpdateNewsDTO addNewsModel = new CreateOrUpdateNewsDTO();
        when(bindingResult.hasErrors()).thenReturn(false); // Simulation where there are no errors
        newsController.addNews(addNewsModel, bindingResult, redirectAttributes, userDetails);
        // Checking if mock object is never called with any arguments
        verify(redirectAttributes, never()).addFlashAttribute(any(), any());
        verify(newsService, times(1)).addNews(eq(addNewsModel), eq(userDetails));
    }

    //Checking the behaviour of searchQuery() method when there are binding errors
    @Test
    public void testSearchQueryWithBindingErrors() {
        SearchNewsDTO searchNewsDTO = new SearchNewsDTO(); // Creating new instance of class SearchNewsDTO
        when(bindingResult.hasErrors()).thenReturn(true); // When hasErrors() is called, should return true
        newsController.searchQuery(searchNewsDTO, bindingResult, model); // Method being tested
        verify(model, times(1)).addAttribute(eq("searchNewsModel"), eq(searchNewsDTO)); // addAttribute of mock object model is called only once with given arguments
        verify(model, times(1)).addAttribute(eq("org.springframework.validation.BindingResult.searchNewsModel"), eq(bindingResult));
        verify(newsService, never()).searchNews(any()); // Checking that searchNews() of newsService mock objct is never called with any arguments.
    }

    @Test
    public void testSearchQueryWithNoBindingErrorsAndEmptySearchNewsDTO() {
        SearchNewsDTO searchNewsDTO = new SearchNewsDTO(); // New instance created
        when(bindingResult.hasErrors()).thenReturn(false); // When hasErrors() is called returning false -> meaning there are no binding errors
        when(model.containsAttribute("searchNewsModel")).thenReturn(false); // When containsAttribute() is called the model object returning false. Meaning model does not contain searchNewsModel attribute
        newsController.searchQuery(searchNewsDTO, bindingResult, model);
        verify(model, times(1)).addAttribute(eq("searchNewsModel"), eq(searchNewsDTO));// Models object attribute called only once with given arguments. Checking if searchNewsModel attribute is added to the model with correct value
        verify(newsService, never()).searchNews(any());//SearchNews() is never called with any arguments. Ensuring that the method is not involked when there are NO binding errors and searchNewsModel is not present in the model
    }

    @Test
    public void testDeleteNews() {
        UUID uuid = UUID.randomUUID(); //Generating and assigning a new UUID to uuid
        newsController.deleteNews(uuid);//Calling the deleteNews() and passing uuid as argument. Testing deleting with specific uuid
        verify(newsService, times(1)).deleteNewsById(eq(uuid));//Veifying that method is called only once with uuid as argument. Checking if deleteNews method in NewsController correctly involkes deleteNewsById method from newsService with expected uuid param
    }

    @Test
    public void testGetNewsDetailWithNewsFound() {
        UUID uuid = UUID.randomUUID();
        NewsDetailDTO newsDto = new NewsDetailDTO();
        when(newsService.findNewsByUUID(uuid)).thenReturn(java.util.Optional.of(newsDto));
        newsController.getNewsDetail(uuid, model);
        verify(model, times(1)).addAttribute(eq("article"), eq(newsDto));
    }

    @Test
    public void testGetNewsDetailWithNewsNotFound() {
        UUID uuid = UUID.randomUUID();
        when(newsService.findNewsByUUID(uuid)).thenReturn(java.util.Optional.empty());// newsService object returning empty Optional when method with given uuid is called. Simulating the news with uuid not found.
        try {
            newsController.getNewsDetail(uuid, model); // testing when retrieving news detail with specific uuid
        } catch (ObjectNotFoundException e) {
            // expected exception
        }
    }
}
