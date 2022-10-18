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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DigitalBookService {

    @Autowired
    DigitalBookRepository bookRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public List<BookResponse> getBookDetails(String title, String author, String publisher) {
        List<Book> bookList = bookRepository.getBookDetails(title, author, publisher);
        List<BookResponse> bookResponseList = new ArrayList<>();
        if (!bookList.isEmpty()) {
            bookList.forEach(book ->
                    bookResponseList.add(new BookResponse(book.getTitle(), book.getPublisher(), book.getReleaseDate(), book.getCategory(),
                            book.getPrice(), book.getUser().getUsername(), book.getContent(), book.getActive(), book.getBookId()))
            );
        } else {
            throw new ResultNotFoundException("Book not found/Book is not active");
        }
        return bookResponseList;
    }

    public List<BookResponse> getAllBookDetails() {
        List<Book> bookList = bookRepository.findBooks();
        List<BookResponse> bookResponseList = new ArrayList<>();
        if (!bookList.isEmpty()) {
            bookList.forEach(book ->
                    bookResponseList.add(new BookResponse(book.getTitle(), book.getPublisher(), book.getReleaseDate(), book.getCategory(),
                            book.getPrice(), book.getUser().getUsername(), book.getContent(), book.getActive(), book.getBookId()))
            );
        } else {
            throw new ResultNotFoundException("Book not found/Book is not active");
        }
        return bookResponseList;
    }

    public Book createBook(BookRequest request, Integer authorId) throws SQLException, DigitalBookException {
        try {
            User user = new User();
            user.setId(authorId);
            Book book = new Book(request.getTitle(), request.getPublisher(), request.getReleaseDate(), request.getCategory(),
                    request.getPrice(), request.getActive(), user, request.getContent());
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new DigitalBookException("Exception while persisting into db please try again later");
        }
    }

    public Book updateeBookDetails(BookRequest request, Integer authorId, Integer bookId) throws SQLException, DigitalBookException {
        try {
            User user = new User();
            user.setId(authorId);
            Book book = new Book(request.getTitle(), request.getPublisher(), request.getReleaseDate(), request.getCategory(),
                    request.getPrice(), request.getActive(), user, request.getContent());
            book.setBookId(bookId);
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new DigitalBookException("Exception while persisting into db please try again later");
        }
    }

    public List<BookResponse> getAllBookDetails(Integer authorId) {
        List<Book> bookList = bookRepository.findByUserUserId(authorId);
        List<BookResponse> bookResponseList = new ArrayList<>();
        if (!bookList.isEmpty()) {
            bookList.forEach(book ->
                    bookResponseList.add(new BookResponse(book.getTitle(), book.getPublisher(), book.getReleaseDate(), book.getCategory(),
                            book.getPrice(), book.getUser().getUsername(), book.getContent(), book.getActive(), book.getBookId()))
            );
        } else {
            throw new ResultNotFoundException("Book not found/Book is not active");
        }
        return bookResponseList;
    }

    public Payment buyBook(Integer userId, PaymentRequest request) throws SQLException, DigitalBookException {
        try {
            Book book = new Book();
            book.setBookId(request.getBookId());
            User user = new User();
            user.setId(userId);
            Payment payment = new Payment(new Date(), book, user);
            payment.setTitle(request.getBookName());
            return paymentRepository.save(payment);
        } catch (Exception e) {
            throw new DigitalBookException("Exception while persisting into db please try again");
        }
    }

    public List<PaymentModel> getPaymentDetails(Integer id) {
        List<Payment> paymentList = paymentRepository.findByUserUserId(id);
        if (!paymentList.isEmpty()) {
            List<PaymentModel> paymentModels = new ArrayList<>();
            paymentList.forEach(payment ->
                    paymentModels.add(new PaymentModel(payment.getPaymentId(), payment.getPaymentDate(), payment.getUser().getUsername(), payment.getBook().getBookId(),payment.getTitle()))
            );
            return paymentModels;
        } else {
            throw new ResultNotFoundException("Payment Details Not Found");
        }
    }

    public Book readContent(Integer bookId) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new ResultNotFoundException("Unable to fetch the content"));
        return book;
    }

    public PaymentModel getPaymentDetailsById(Long paymentId) {
        PaymentModel paymentModel = new PaymentModel();
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResultNotFoundException("Unable to fetch payment details"));
        paymentModel.setBookId(payment.getBook().getBookId());
        paymentModel.setPaymentDate(payment.getPaymentDate());
        paymentModel.setPaymentId(payment.getPaymentId());
        paymentModel.setReaderName(payment.getUser().getUsername());
        return paymentModel;
    }

}
