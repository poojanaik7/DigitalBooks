package com.digitalbooks.model;

public class BookRequest {

    private Integer bookId;

    private Integer userId;

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
