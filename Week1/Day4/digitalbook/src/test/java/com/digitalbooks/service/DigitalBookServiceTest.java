package com.digitalbooks.service;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;
import com.digitalbooks.entity.User;
import com.digitalbooks.exception.DigitalBookException;
import com.digitalbooks.exception.ResultNotFoundException;
import com.digitalbooks.model.BookRequest;
import com.digitalbooks.model.BookResponse;
import com.digitalbooks.model.PaymentModel;
import com.digitalbooks.model.PaymentRequest;
import com.digitalbooks.repository.DigitalBookRepository;
import com.digitalbooks.repository.PaymentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)

public class DigitalBookServiceTest {

    @InjectMocks
    DigitalBookService digitalBookService;

    @Mock
    DigitalBookRepository digitalBookRepository;

    @Mock
    PaymentRepository paymentRepository;

    @Test
    public void getBookDetails() {
        User user = new User();
        user.setId(123);
        user.setRoles(null);
        user.setEmail("abc");
        user.setUsername("abc");
        Book book = new Book("title","publisher",new Date(),"category",20l,true,user,"content");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        Mockito.when(digitalBookRepository.getBookDetails("title","author","publisher")).thenReturn(bookList);
        List<BookResponse> responses = digitalBookService.getBookDetails("title","author","publisher");
        Assert.assertNotNull(responses);
    }

    @Test(expected = ResultNotFoundException.class)
    public void getBookDetailsException() {
        List<Book> bookList = new ArrayList<>();
        Mockito.when(digitalBookRepository.getBookDetails("title","author","publisher")).thenReturn(bookList);
        List<BookResponse> responses = digitalBookService.getBookDetails("title","author","publisher");
        Assert.assertNotNull(responses);
    }

    @Test
    public void createBook() throws Exception {
        User user = new User();
        user.setId(123);
        Book book = new Book("title","publisher",new Date(),"category",20l,true,user,"content");
        Mockito.when(digitalBookRepository.save(Mockito.any())).thenReturn(book);
        BookRequest bookRequest = new BookRequest(123,123,"title","publisher",new Date(),"category",20l,"author","content",true);
        bookRequest.setUserId(123);
        Book b = digitalBookService.createBook(bookRequest,123);
        Assert.assertNotNull(b);
    }

    @Test(expected = DigitalBookException.class)
    public void createBookExceptionTest() throws Exception {
        User user = new User();
        user.setId(123);
        BookRequest bookRequest = new BookRequest(123,123,null,"publisher",new Date(),"category",20l,"author","content",true);
        Assert.assertNull(digitalBookService.createBook(bookRequest,123));
        throw new DigitalBookException("Exception");

    }

    @Test
    public void updateBookDetails() throws Exception{
        User user = new User();
        user.setId(123);
        Book book = new Book("title","publisher",new Date(),"category",20l,true,user,"content");
        Mockito.when(digitalBookRepository.save(Mockito.any())).thenReturn(book);
        BookRequest bookRequest = new BookRequest(123,123,"title","publisher",new Date(),"category",20l,"author","content",true);
        Book bookResponse1 = digitalBookService.updateeBookDetails(bookRequest,123,123);
        Assert.assertNotNull(bookResponse1);
    }

    @Test(expected = DigitalBookException.class)
    public void updateBookExceptionTest() throws Exception {
        User user = new User();
        user.setId(123);
        BookRequest bookRequest = new BookRequest();
        bookRequest.setBookId(123);
        Assert.assertNull(digitalBookService.updateeBookDetails(bookRequest,123,123));
        throw new DigitalBookException("Exception");
    }


    @Test
    public void buyBook() throws Exception{
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setBookId(123);
        paymentRequest.setUserId(123);
        User user = new User();
        user.setId(paymentRequest.getUserId());
        Book book = new Book("title","publisher",new Date(),"category",20l,true,user,"content");
        Payment payment = new Payment(new Date(),book,user);
        Mockito.when(paymentRepository.save(Mockito.any())).thenReturn(payment);
        Payment p = digitalBookService.buyBook(123,123);
        Assert.assertNotNull(p);
    }

    @Test(expected = DigitalBookException.class)
    public void buyBookException() throws Exception{
        PaymentRequest paymentRequest = null;
        digitalBookService.buyBook(null,null);
        throw new DigitalBookException("Exception");
    }

    @Test
    public void getPaymentDetails() {
        User user = new User();
        user.setId(123);
        Book book = new Book("title","publisher",new Date(),"category",20l,true,user,"content");
        Payment payment = new Payment(new Date(),book,user);
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        Mockito.when(paymentRepository.findByUserUserId(Mockito.anyInt())).thenReturn(paymentList);
        List<PaymentModel> paymentModels = digitalBookService.getPaymentDetails(123);
        Assert.assertNotNull(paymentModels);
    }

    @Test(expected = ResultNotFoundException.class)
    public void getPaymentDetailsException() {
        User user = new User();
        user.setId(123);
        List<Payment> paymentList = new ArrayList<>();
        Mockito.when(paymentRepository.findByUserUserId(Mockito.anyInt())).thenReturn(paymentList);
        List<PaymentModel> paymentModels = digitalBookService.getPaymentDetails(123);
        Assert.assertNull(paymentModels);
    }

    @Test
   public void readContent() {
        User user = new User();
        user.setId(123);
        Book book = new Book("title","publisher",new Date(),"category",20l,true,user,"content");
        Mockito.when(digitalBookRepository.findById(123)).thenReturn(Optional.of(book));
        Assert.assertNotNull(digitalBookService.readContent(123));
    }

    @Test
   public void getPaymentDetailsById() {
        User user = new User();
        user.setId(123);
        Book book = new Book("title","publisher",new Date(),"category",20l,true,user,"content");
        Payment payment = new Payment(new Date(),book,user);
        Mockito.when(paymentRepository.findById(12l)).thenReturn(Optional.of(payment));
        Assert.assertNotNull(digitalBookService.getPaymentDetailsById(12l));
    }
}