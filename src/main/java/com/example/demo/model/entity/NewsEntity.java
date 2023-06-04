package com.example.demo.model.entity;

import com.example.demo.model.enums.StateEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

import java.util.UUID;

@Entity
@Table(name = "news")
public class NewsEntity {

    //I was having some issues with adding a new article (500 Error "incorrect String value"),
    // Turns out it was because @Column() was set to @Column(CHAR(36))
    //Changing it to "VARBINARY(36) was saving the value with some weird characters"
    //@Type(type = "uuid-char") turned out to be deprecated, so i could't use it
    //@JdbcTypeCode(SqlTypes.VARCHAR) fixed the issue and values are now stored as UUIDs
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    //@Column(name = "id", columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StateEnum state;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private LocalDate creationDate;


    private String photoLink;

    @Column(nullable = false)
    private LocalDate validFrom;

    @Column(nullable = false)
    private LocalDate validTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public NewsEntity setCreationDate(LocalDate creationDate) {
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

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public NewsEntity setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public NewsEntity setValidTo(LocalDate validTo) {
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
