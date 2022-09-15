package com.digitalbooks.controller;

import com.digitalbooks.entity.Book;
import com.digitalbooks.model.BookRequest;
import com.digitalbooks.service.DigitalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/digitalbooks/author")
public class DigitalBookAuthorModeController {
    @Autowired
    DigitalBookService userService;

    @PostMapping("/books/{authorId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer createBook(@RequestBody BookRequest request, @PathVariable("authorId") Integer authorId) {
        Book response = userService.createBook(request,authorId);
        return response.getBookId();
    }

    @PutMapping("/books/{bookId}")
    public Book updateBookDetails(@RequestBody BookRequest request, @PathVariable Integer bookId) {
       Book response = userService.updateeBookDetails(request,bookId);
        return response;
    }


}
