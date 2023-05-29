package com.example.demo.model.dto.news;

import com.example.demo.model.enums.StateEnum;

import java.time.LocalDate;
import java.util.UUID;

public class NewsDetailDTO {

    private UUID id;

    private String title;

    private String text;

    private LocalDate creationDate;

    private String photoLink;

    private LocalDate validFrom;

    private LocalDate validTo;

    private String publisherFirstName;

    private String publisherLastName;

    private StateEnum state;

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public NewsDetailDTO setCreationDate(LocalDate creationDate) {
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

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public NewsDetailDTO setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public NewsDetailDTO setValidTo(LocalDate validTo) {
        this.validTo = validTo;
        return this;
    }

    public StateEnum getState() {
        return state;
    }

    public NewsDetailDTO setState(StateEnum state) {
        this.state = state;
        return this;
    }

    public String getNewsHighlight() {
        return this.title + " " + this.creationDate;
    }
}
