package com.digitalbooks.model;

import javax.validation.constraints.NotNull;

public class BookRequest {

    private Integer bookId;

    private Integer userId;

    @NotNull
    private BookResponse response;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BookResponse getResponse() {
        return response;
    }

    public void setResponse(BookResponse response) {
        this.response = response;
    }
}
