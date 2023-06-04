package com.example.demo.repository;

import com.example.demo.model.dto.news.SearchNewsDTO;
import com.example.demo.model.entity.NewsEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

// implementing the "Specification" interface for querying DB
public class NewsSpecification implements Specification<NewsEntity> {

    private final SearchNewsDTO searchNewsDTO;

    //Using the "SearchNewsDTO" as a constructor, which contains search criteria for news entities
    // Used to filter news entities when performing DB query
    public NewsSpecification(SearchNewsDTO searchNewsDTO) {
        this.searchNewsDTO = searchNewsDTO;
    }


    @Override
    //With this method a conjunction object is created. -> All added conditions must be true for NewsEntity to match
    public Predicate toPredicate(Root<NewsEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        //Checking if the "searchNewsDTO" has nonNull and nonEmpty  title
        // if both conditions are true, adding a condition to "predicate" and checking for equality between
        //title of the root entity and provided title value
        if(searchNewsDTO.getTitle() != null && !searchNewsDTO.getTitle().isEmpty()) {
            predicate.getExpressions().add(criteriaBuilder.and(criteriaBuilder.equal(root.join("title").get("title"), searchNewsDTO.getTitle())));
        }

        return predicate;
    }
}