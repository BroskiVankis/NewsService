package com.example.demo.model.entity;

import com.example.demo.model.enums.StateEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "news")
public class NewsEntity {

    @Id
    @org.hibernate.validator.constraints.UUID
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StateEnum state;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column
    private String photoLink;

    @Column(nullable = false)
    private LocalDateTime validFrom;

    @Column(nullable = false)
    private LocalDateTime validTo;

    @ManyToOne
    private UserEntity publisher;

    public UUID getId() {
        return id;
    }

    public NewsEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public StateEnum getState() {
        return state;
    }

    public NewsEntity setState(StateEnum state) {
        this.state = state;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public NewsEntity setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public NewsEntity setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public NewsEntity setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
        return this;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public NewsEntity setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public NewsEntity setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
        return this;
    }

    public UserEntity getPublisher() {
        return publisher;
    }

    public NewsEntity setPublisher(UserEntity publisher) {
        this.publisher = publisher;
        return this;
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "id=" + id +
                ", state=" + state +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", photoLink='" + photoLink + '\'' +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", publisher=" + publisher +
                '}';
    }
}
