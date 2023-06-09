package com.example.demo.model.mapper;

import com.example.demo.model.dto.news.CreateOrUpdateNewsDTO;
import com.example.demo.model.dto.news.NewsDetailDTO;
import com.example.demo.model.entity.NewsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsEntity createOrUpdateNewsDtoToNewsEntity(CreateOrUpdateNewsDTO addNewsDTO);

    NewsEntity createOrUpdateNewsDtoToNewsEntityUpdate(CreateOrUpdateNewsDTO updateNewsDTO);

    CreateOrUpdateNewsDTO newsEntityToCreateOrUpdateNewsDtoTo(NewsEntity newsEntity);

    @Mapping(source = "publisher.firstName", target = "publisherFirstName")
    @Mapping(source = "publisher.lastName", target = "publisherLastName")
    NewsDetailDTO newsEntityToNewsDetailDto(NewsEntity newsEntity);
}
