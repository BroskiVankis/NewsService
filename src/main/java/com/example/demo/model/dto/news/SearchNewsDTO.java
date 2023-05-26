package com.example.demo.model.dto.news;

public class SearchNewsDTO {

    private String title;

    public String getTitle() {
        return title;
    }

    public SearchNewsDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public boolean isEmpty() {
        return (title == null || title.isEmpty());
    }

    @Override
    public String toString() {
        return "SearchNewsDTO{" +
                "title='" + title + '\'' +
                '}';
    }
}
