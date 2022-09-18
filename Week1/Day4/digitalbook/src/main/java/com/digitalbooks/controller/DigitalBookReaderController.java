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

@RestController
@RequestMapping("/api/digitalbooks/reader")
public class DigitalBookReaderController extends BaseController{
    @Autowired
    DigitalBookService userService;

    @GetMapping("/search")
    public ResponseEntity<?> searchBook(@RequestParam String category, @RequestParam String author, @RequestParam Long price, @RequestParam String publisher) {
        List<BookResponse> bookResponse = userService.getBookDetails(category, author, price, publisher);
        return ResponseEntity.ok(bookResponse);
    }

    @PostMapping("/buy")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<?> buyBook(@RequestBody PaymentRequest bookRequest) throws SQLException, DigitalBookException {
        Payment payment = userService.buyBook(bookRequest);
        return ResponseEntity.ok(new MessageResponse("Reader purchased book successfully. Payment Id : "+payment.getPaymentId()));
    }

    @GetMapping("/{id}/books")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<?> readPaymentDetails(@PathVariable Integer id){
       List<PaymentModel> paymentModelList = userService.getPaymentDetails(id);
       return ResponseEntity.ok(paymentModelList);
    }

    @GetMapping("/books/{bookId}")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<?> readContent(@PathVariable Integer bookId){
        return ResponseEntity.ok(userService.readContent(bookId));
    }

    @GetMapping("/books")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<?> getPaymentById(@RequestParam Long paymentId){
        return ResponseEntity.ok(userService.getPaymentDetailsById(paymentId));
    }
}
