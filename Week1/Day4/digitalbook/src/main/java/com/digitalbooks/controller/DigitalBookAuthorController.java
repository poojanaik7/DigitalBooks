package com.digitalbooks.controller;

import com.digitalbooks.entity.Book;
import com.digitalbooks.exception.DigitalBookException;
import com.digitalbooks.model.BookRequest;
import com.digitalbooks.model.BookResponse;
import com.digitalbooks.payload.response.MessageResponse;
import com.digitalbooks.service.DigitalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/digitalbooks/author")
public class DigitalBookAuthorController extends BaseController{
    @Autowired
    DigitalBookService userService;

    @PostMapping("/{authorId}/books")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    public ResponseEntity<?> createBook(@Valid @RequestBody BookRequest request, @PathVariable("authorId") Integer authorId) throws SQLException,DigitalBookException {
        Book response = userService.createBook(request,authorId);
        return ResponseEntity.ok(new MessageResponse("Book has been created successfully. Book Id:"+ response.getBookId()));
    }

    @PutMapping("/{authorId}/books/{bookId}")
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    public ResponseEntity<?> updateBookDetails(@Valid @RequestBody BookRequest request, @PathVariable Integer authorId, @PathVariable Integer bookId) throws SQLException, DigitalBookException {
       BookResponse bookResponse = userService.updateeBookDetails(request,authorId,bookId);
       return ResponseEntity.ok(new MessageResponse("Book Details has been updated successfully."));
    }
}
