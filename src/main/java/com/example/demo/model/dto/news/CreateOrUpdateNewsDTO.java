package com.example.demo.model.dto.news;

import com.example.demo.model.enums.StateEnum;
//import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateOrUpdateNewsDTO {

    private UUID id;

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

    private String publisherFirstName;

    private String publisherLastName;

    public String getPublisherFirstName() {
        return publisherFirstName;
    }

    public CreateOrUpdateNewsDTO setPublisherFirstName(String publisherFirstName) {
        this.publisherFirstName = publisherFirstName;
        return this;
    }

    public String getPublisherLastName() {
        return publisherLastName;
    }

    public CreateOrUpdateNewsDTO setPublisherLastName(String publisherLastName) {
        this.publisherLastName = publisherLastName;
        return this;
    }

    public String getPublisherFullName() {
        return publisherFirstName + " " + publisherLastName;
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

    public UUID getId() {
        return id;
    }

    public CreateOrUpdateNewsDTO setId(UUID id) {
        this.id = id;
        return this;
    }
}
