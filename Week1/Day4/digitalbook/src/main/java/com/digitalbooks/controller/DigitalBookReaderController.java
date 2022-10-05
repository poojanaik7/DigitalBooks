package com.digitalbooks.controller;

import com.digitalbooks.entity.Payment;
import com.digitalbooks.exception.DigitalBookException;
import com.digitalbooks.model.BookResponse;
import com.digitalbooks.model.PaymentModel;
import com.digitalbooks.model.PaymentRequest;
import com.digitalbooks.payload.response.MessageResponse;
import com.digitalbooks.service.DigitalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/digitalbooks/reader")
public class DigitalBookReaderController extends BaseController{
    @Autowired
    DigitalBookService userService;

    @GetMapping("/search")
    public ResponseEntity<?> searchBook(@RequestParam String title, @RequestParam String author, @RequestParam String publisher) {
        List<BookResponse> bookResponse = userService.getBookDetails(title, author, publisher);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/searchBooks")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<?> searchAllBook() {
        List<BookResponse> bookResponse = userService.getAllBookDetails();
        return ResponseEntity.ok(bookResponse);
    }

    @PostMapping("/buy/{userId}/{bookId}")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<?> buyBook(@PathVariable Integer userId,@PathVariable Integer bookId) throws SQLException, DigitalBookException {
        Payment payment = userService.buyBook(userId,bookId);
        return ResponseEntity.ok(new MessageResponse("Reader purchased book successfully. Payment Id : "+payment.getPaymentId()));
    }

    @GetMapping("/{id}/books")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<?> readPaymentDetails(@PathVariable Integer id){
       List<PaymentModel> paymentModelList = userService.getPaymentDetails(id);
       return ResponseEntity.ok(paymentModelList);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> readContent(@PathVariable Integer bookId){
        return ResponseEntity.ok(userService.readContent(bookId));
    }

    @GetMapping("/books")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<?> getPaymentById(@RequestParam Long paymentId){
        return ResponseEntity.ok(userService.getPaymentDetailsById(paymentId));
    }
}
