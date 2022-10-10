package com.digitalbooks.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Publisher cannot be blank")
    private String publisher;

    @NotNull(message = "Release cannot be null")
    private Date releaseDate;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotNull(message = "Price cannot be null")
    private Long price;

    @NotNull(message = "Active flag cannot be null")
    private Boolean activeFlag;

    @ManyToOne
    private User user;

    @NotEmpty(message = "Content cannot be blank")
    private String content;

    public Book() {
    }

    public Book(String title, String publisher, Date releaseDate, String category, Long price, Boolean active, User user, String content) {
        this.title = title;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.category = category;
        this.price = price;
        this.activeFlag = active;
        this.user = user;
        this.content = content;
    }

    public Book(String title, String publisher, Date releaseDate, String category, Long price, Boolean activeFlag, String content) {
        this.title = title;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.category = category;
        this.price = price;
        this.activeFlag = activeFlag;
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
