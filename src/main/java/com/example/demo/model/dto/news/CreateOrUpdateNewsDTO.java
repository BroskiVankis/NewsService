package com.example.demo.model.dto.news;

import com.example.demo.model.enums.StateEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateOrUpdateNewsDTO {

    @NotNull
    @Min(1)
    private Long articleId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Creation Date is required!")
    private LocalDate creationDate;

    @NotEmpty
    private String photoLink;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Valid From is required!")
    private LocalDate validFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Valid To is required!")
    private LocalDate validTo;

    private StateEnum state;

    public Long getArticleId() {
        return articleId;
    }

    public CreateOrUpdateNewsDTO setArticleId(Long articleId) {
        this.articleId = articleId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CreateOrUpdateNewsDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public CreateOrUpdateNewsDTO setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public CreateOrUpdateNewsDTO setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public CreateOrUpdateNewsDTO setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
        return this;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public CreateOrUpdateNewsDTO setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public CreateOrUpdateNewsDTO setValidTo(LocalDate validTo) {
        this.validTo = validTo;
        return this;
    }

    public StateEnum getState() {
        return state;
    }

    public CreateOrUpdateNewsDTO setState(StateEnum state) {
        this.state = state;
        return this;
    }
}
