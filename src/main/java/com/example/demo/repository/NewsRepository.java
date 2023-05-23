package com.example.demo.repository;

import com.example.demo.model.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//Here also extending the "JpaSpecificationExecutor" to dynamically create queries
@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, UUID>, JpaSpecificationExecutor<NewsEntity> {
}
