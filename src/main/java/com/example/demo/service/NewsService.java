package com.example.demo.service;

import com.example.demo.model.dto.news.CreateOrUpdateNewsDTO;
import com.example.demo.model.dto.news.NewsDetailDTO;
import com.example.demo.model.dto.news.SearchNewsDTO;
import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.enums.UserRoleEnum;
import com.example.demo.model.mapper.NewsMapper;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.NewsSpecification;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    private final UserRepository userRepository;

    private final NewsMapper newsMapper;

    public NewsService(NewsRepository newsRepository, UserRepository userRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.newsMapper = newsMapper;
    }

    //Checking if user with userName is the publisher of the news with newsId
    //if offer exists and publisher Email matches the userName, then user is publisher
    public boolean isPublisher(String userName, UUID newsId) {

        boolean isPublisher = newsRepository.
                findById(newsId).
                filter(o -> o.getPublisher().getEmail().equals(userName)).
                isPresent();

        if(isPublisher) {
            return true;
        }

        return userRepository.
                findByEmail(userName).
                filter(this::isAdmin).
                isPresent();
    }

    //Checking if user is Admin
    private boolean isAdmin(UserEntity user) {
        return user.getUserRoles().
                stream().
                anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);
    }

    //Deleting the news by ID
    public void deleteNewsById(UUID newsId) {
        newsRepository.deleteById(newsId);
    }

    //Getting all the news -> Returns a Page of NewsDetailDTO Objects
    public Page<NewsDetailDTO> getAllNews(Pageable pageable) {
        return newsRepository.findAll(pageable).map(newsMapper::newsEntityToNewsDetailDto);
    }

    //Retrieving of news with specified newsId using newsRepository, maps the entity
    //and returns Optional of the result
    public Optional<CreateOrUpdateNewsDTO> getNewsEditDetails(UUID newsId) {
        return newsRepository.findById(newsId).map(newsMapper::newsEntityToCreateOrUpdateNewsDtoTo);
    }

    public Optional<NewsDetailDTO> findNewsByUUID(UUID newsId) {
        return newsRepository.findById(newsId).map(newsMapper::newsEntityToNewsDetailDto);
    }

    public NewsEntity getNewsById(UUID id) {
        return newsRepository.findById(id).get();
    }

    //Adding new news by creating NewsEntity from CreateOrUpdateNewsDTO
    public void addNews(CreateOrUpdateNewsDTO addNewsDTO, UserDetails userDetails) {
        NewsEntity newNews = newsMapper.createOrUpdateNewsDtoToNewsEntity(addNewsDTO);

        UserEntity publisher = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        newNews.setPublisher(publisher);
        newsRepository.save(newNews);
    }

    public List<NewsDetailDTO> searchNews(SearchNewsDTO searchNewsDTO) {
        return this.newsRepository.findAll(new NewsSpecification(searchNewsDTO)).
                stream().map(newsMapper::newsEntityToNewsDetailDto).toList();
    }

    public NewsEntity updateArticle(NewsEntity news) {
        return newsRepository.save(news);
    }

}
