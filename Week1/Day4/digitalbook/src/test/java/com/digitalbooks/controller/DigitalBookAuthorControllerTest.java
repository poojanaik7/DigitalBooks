package com.digitalbooks.controller;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.User;
import com.digitalbooks.model.BookRequest;
import com.digitalbooks.model.BookResponse;
import com.digitalbooks.service.DigitalBookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class DigitalBookAuthorControllerTest {

    @Mock
    DigitalBookService userService;

    @InjectMocks
    DigitalBookAuthorController digitalBookAuthorController;

    @Test
    public void createBookTest() throws Exception{
        BookRequest bookRequest = new BookRequest(123,123,"title","publisher",new Date(),"category",20l,"author","content",true);
        Book book = new Book("title","publisher",new Date(),"category",20l,true,null,"content");
        Mockito.when(userService.createBook(bookRequest,123)).thenReturn(book);
        Assert.assertNotNull(digitalBookAuthorController.createBook(bookRequest,123));
    }

    @Test
    public void updateBookDetailsTest() throws Exception{
        BookRequest bookRequest = new BookRequest(123,123,"title","publisher",new Date(),"category",20l,"author","content",true);
        Book book = new Book("title","publisher",new Date(),"category",20l,true,null,"content");
        Mockito.when(userService.updateeBookDetails(bookRequest,123,123)).thenReturn(book);
        Assert.assertNotNull(digitalBookAuthorController.updateBookDetails(bookRequest,123,123));
    }

    @Test
    public void getAllBookDetailsTest() throws Exception{
        BookResponse bookResponse = new BookResponse("title","publisher",new Date(),"category",20l,"author","content",true,123);
        List<BookResponse> bookResponseList = new ArrayList<>();
        bookResponseList.add(bookResponse);
        Mockito.when(userService.getAllBookDetails(123)).thenReturn(bookResponseList);
        ResponseEntity<?> res = digitalBookAuthorController.getAllBookDetails(123);
        Assert.assertNotNull(res);
    }

}