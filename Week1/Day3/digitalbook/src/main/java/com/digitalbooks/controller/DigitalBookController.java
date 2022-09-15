package com.digitalbooks.controller;

import com.digitalbooks.entity.Book;
import com.digitalbooks.service.DigitalBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/digBooks")
public class DigitalBookController {
    @Autowired
    DigitalBookService userService;

    @GetMapping("/search")
    public List<Book> searchBook(@RequestParam String category, @RequestParam String author, @RequestParam Long price, @RequestParam String publisher) {
        return userService.getBookDetails(category, author, price, publisher);
    }

    @PostMapping("/author/books")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer createBook(@RequestBody Book book) {
        userService.createBook(book);
        return book.getBookId();
    }

    @PutMapping("/author/books/{bookId}")
    public Book editeBook(@RequestBody Book book, @PathVariable Integer bookId) {
        return book;
    }
}
