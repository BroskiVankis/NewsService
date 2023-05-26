package com.example.demo.model.dto.news;

import com.example.demo.model.enums.StateEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateOrUpdateNewsDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    @NotNull(message = "Creation Date is required!")
    private LocalDateTime creationDate;

    @NotEmpty
    private String photoLink;

    @NotNull(message = "Valid From is required!")
    private LocalDateTime validFrom;

    @NotNull(message = "Valid To is required!")
    private LocalDateTime validTo;

    private StateEnum state;

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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public CreateOrUpdateNewsDTO setCreationDate(LocalDateTime creationDate) {
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

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public CreateOrUpdateNewsDTO setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public CreateOrUpdateNewsDTO setValidTo(LocalDateTime validTo) {
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
