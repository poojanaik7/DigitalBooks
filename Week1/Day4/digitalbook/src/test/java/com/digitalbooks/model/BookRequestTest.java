package com.digitalbooks.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class BookRequestTest {

    BookRequest request = new BookRequest();

    @Before
    public void test(){
        request.setUserId(123);
        request.setActive(true);
        request.setAuthor("abc");
        request.setCategory("tech");
        request.setContent("content");
        request.setTitle("title");
        request.setPublisher("publisher");
        request.setReleaseDate(new Date());
    }

    @Test
    public void getBookId() {
        Assert.assertNotNull(request.getActive());
    }

    @Test
    public void getTitle() {
        Assert.assertNotNull(request.getTitle());
    }

    @Test
    public void getPublisher() {
        Assert.assertNotNull(request.getPublisher());
    }

    @Test
    public void getReleaseDate() {
        Assert.assertNotNull(request.getReleaseDate());
    }

    @Test
    public void getCategory() {
        Assert.assertNotNull(request.getCategory());
    }
}
