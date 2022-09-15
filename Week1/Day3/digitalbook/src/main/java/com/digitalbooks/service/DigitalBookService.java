package com.digitalbooks.service;

import com.digitalbooks.entity.Book;
import com.digitalbooks.repository.DigitalBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DigitalBookService {

    @Autowired
    DigitalBookRepository bookRepository;

    public List<Book> getBookDetails(String category, String author, Long price, String publisher) {
        return bookRepository.getBookDetails(category,author,price,publisher);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);

    }


}
