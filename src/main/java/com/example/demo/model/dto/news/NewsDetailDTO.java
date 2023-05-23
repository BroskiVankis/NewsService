package com.example.demo.model.dto.news;

import java.time.LocalDateTime;
import java.util.UUID;

public class NewsDetailDTO {

    private UUID id;

    private String title;

    private String text;

    private LocalDateTime creationDate;

    private String photoLink;

    private LocalDateTime validFrom;

    private LocalDateTime validTo;

    private String publisherFirstName;

    private String publisherLastName;

    public String getPublisherFirstName() {
        return publisherFirstName;
    }

    public NewsDetailDTO setPublisherFirstName(String publisherFirstName) {
        this.publisherFirstName = publisherFirstName;
        return this;
    }

    public String getPublisherFullName() {
        return publisherFirstName + " " + publisherLastName;
    }

    public String getPublisherLastName() {
        return publisherLastName;
    }

    public NewsDetailDTO setPublisherLastName(String publisherLastName) {
        this.publisherLastName = publisherLastName;
        return this;
    }

    public NewsDetailDTO() {
    }

    public UUID getId() {
        return id;
    }

    public NewsDetailDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsDetailDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public NewsDetailDTO setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public NewsDetailDTO setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public NewsDetailDTO setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
        return this;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public NewsDetailDTO setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public NewsDetailDTO setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
        return this;
    }
}
