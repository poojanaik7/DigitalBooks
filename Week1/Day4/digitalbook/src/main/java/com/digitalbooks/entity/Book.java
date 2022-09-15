package com.digitalbooks.entity;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;

    private String title;

    private String publisher;

    private String releaseDate;

    private String category;

    private Long price;

    private Boolean activeFlag;

    @ManyToOne
    private User user;

    private String content;

    public Book() {
    }

    public Book(String title, String publisher, String releaseDate, String category, Long price, Boolean active, User user, String content) {
        this.title = title;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.category = category;
        this.price = price;
        this.activeFlag = active;
        this.user = user;
        this.content = content;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Boolean getActive() {
        return activeFlag;
    }

    public void setActive(Boolean active) {
        this.activeFlag = active;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
