package com.example.demo.model.dto.news;


import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public class UpdateDTO {

    private UUID id;

    @NotEmpty(message = "News Title is required!")
    private String title;

    @NotEmpty(message = "Text for this article is required!")
    private String text;

    @NotEmpty(message = "Article image URL is required!")
    private String photoLink;

    public String getTitle() {
        return title;
    }

    public UpdateDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public UpdateDTO setText(String text) {
        this.text = text;
        return this;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public UpdateDTO setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public UpdateDTO setId(UUID id) {
        this.id = id;
        return this;
    }
}
