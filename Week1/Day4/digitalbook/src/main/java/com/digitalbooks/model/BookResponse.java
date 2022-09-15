package com.digitalbooks.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class BookResponse {

    @NotEmpty
    private String title;

    @NotEmpty
    private String publisher;

    @NotEmpty
    private Date releaseDate;

    @NotEmpty
    private String category;

    @NotNull
    private Long price;

    @NotEmpty
    private String author;

    @NotEmpty
    private String content;

    @NotNull
    private Boolean active;

    public BookResponse(){
    }

    public BookResponse(String title, String publisher, Date releaseDate, String category, Long price, String author, String content, Boolean active) {
        this.title = title;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.category = category;
        this.price = price;
        this.author = author;
        this.content = content;
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
