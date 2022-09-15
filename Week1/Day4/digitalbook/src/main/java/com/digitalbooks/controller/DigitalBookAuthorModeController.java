package com.digitalbooks.controller;

import com.digitalbooks.entity.Book;
import com.digitalbooks.model.BookRequest;
import com.digitalbooks.model.BookResponse;
import com.digitalbooks.service.DigitalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/digitalbooks/author")
public class DigitalBookAuthorModeController {
    @Autowired
    DigitalBookService userService;

    @PostMapping("/{authorId}/books")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer createBook(@Valid @RequestBody BookRequest request, @PathVariable("authorId") Integer authorId) {
        Book response = userService.createBook(request,authorId);
        return response.getBookId();
    }

    @PutMapping("/{authorId}/books/{bookId}")
    public BookResponse updateBookDetails(@Valid @RequestBody BookRequest request, @PathVariable Integer authorId, @PathVariable Integer bookId) {
       return userService.updateeBookDetails(request,authorId,bookId);
    }

}
