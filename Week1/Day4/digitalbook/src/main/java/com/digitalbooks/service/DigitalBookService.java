package com.digitalbooks.service;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;
import com.digitalbooks.entity.User;
import com.digitalbooks.exception.ResultNotFoundException;
import com.digitalbooks.exception.DigitalBookException;
import com.digitalbooks.model.BookRequest;
import com.digitalbooks.model.BookResponse;
import com.digitalbooks.model.PaymentModel;
import com.digitalbooks.model.PaymentRequest;
import com.digitalbooks.repository.DigitalBookRepository;
import com.digitalbooks.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DigitalBookService {

    @Autowired
    DigitalBookRepository bookRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public List<BookResponse> getBookDetails(String category, String author, Long price, String publisher) {
        List<Book> bookList = bookRepository.getBookDetails(category,author,price,publisher);
        List<BookResponse> bookResponseList = new ArrayList<>();
        if(!bookList.isEmpty()) {
            bookList.forEach(book ->
                    bookResponseList.add(new BookResponse(book.getTitle(), book.getPublisher(), book.getReleaseDate(), book.getCategory(),
                            book.getPrice(), book.getUser().getUsername(), book.getContent(), book.getActive()))
            );
        }
        else{
            throw new ResultNotFoundException("Book not found/Book is not active");
        }
        return bookResponseList;
    }

    public Book createBook(BookRequest request, Integer authorId) throws SQLException, DigitalBookException {
       try {
           User user = new User();
           user.setId(authorId);
           Book book = new Book(request.getResponse().getTitle(), request.getResponse().getPublisher(), request.getResponse().getReleaseDate(), request.getResponse().getCategory(),
                   request.getResponse().getPrice(), request.getResponse().getActive(), user, request.getResponse().getContent());
           return bookRepository.save(book);
       }
       catch (Exception e){
           throw new DigitalBookException("Exception while persisting into db please try again");
       }
    }

    public BookResponse updateeBookDetails(BookRequest request,Integer authorId,Integer bookId) throws SQLException,DigitalBookException {
        try {
            User user = new User();
            user.setId(authorId);
            Book book = new Book(request.getResponse().getTitle(),request.getResponse().getPublisher(),request.getResponse().getReleaseDate(),request.getResponse().getCategory(),
                    request.getResponse().getPrice(),request.getResponse().getActive(),user,request.getResponse().getContent());
            book.setBookId(bookId);
            bookRepository.save(book);
        }
        catch (Exception e){
            throw new DigitalBookException("Exception while persisting into db please try again");
        }
        return request.getResponse();
    }

    public Payment buyBook(PaymentRequest request) throws SQLException,DigitalBookException{
        try {
            Book book = new Book();
            book.setBookId(request.getBookId());
            User user = new User();
            user.setId(request.getUserId());
            Payment payment = new Payment(new Date(),book,user);
            return paymentRepository.save(payment);
        }
        catch (Exception e){
            throw new DigitalBookException("Exception while persisting into db please try again");
        }

    }

    public List<PaymentModel> getPaymentDetails(Integer id){
        List<Payment> paymentList = paymentRepository.findByUserUserId(id);
        if(!paymentList.isEmpty()) {
            List<PaymentModel> paymentModels = new ArrayList<>();
            paymentList.forEach(payment ->
                    paymentModels.add(new PaymentModel(payment.getPaymentId(), payment.getPaymentDate(), payment.getUser().getUsername(), payment.getBook().getBookId()))
            );
            return paymentModels;
        }
        else {
            throw new ResultNotFoundException("Payment Details Not Found");
        }
    }

    public String readContent(Integer bookId){
        Book book = bookRepository.findById(bookId).
                orElseThrow(()-> new ResultNotFoundException("Unable to fetch the content"));
        return book.getContent();
    }

    public PaymentModel getPaymentDetailsById(Long paymentId){
        PaymentModel paymentModel = new PaymentModel();
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(()-> new ResultNotFoundException("Unable to fetch payment details"));
         paymentModel.setBookId(payment.getBook().getBookId());
         paymentModel.setPaymentDate(payment.getPaymentDate());
         paymentModel.setPaymentId(payment.getPaymentId());
         paymentModel.setReaderName(payment.getUser().getUsername());
         return paymentModel;
    }

}
