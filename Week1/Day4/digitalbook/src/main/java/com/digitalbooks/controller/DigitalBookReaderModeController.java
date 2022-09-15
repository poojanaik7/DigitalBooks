package com.digitalbooks.controller;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;
import com.digitalbooks.model.BookRequest;
import com.digitalbooks.model.BookResponse;
import com.digitalbooks.model.PaymentModel;
import com.digitalbooks.service.DigitalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/digitalbooks")
public class DigitalBookReaderModeController {
    @Autowired
    DigitalBookService userService;

    @GetMapping("/search")
    public List<BookResponse> searchBook(@RequestParam String category, @RequestParam String author, @RequestParam Long price, @RequestParam String publisher) {
        return userService.getBookDetails(category, author, price, publisher);
    }

    @PostMapping("/buy")
    public String buyBook(@RequestBody BookRequest bookRequest){
        Payment payment = userService.buyBook(bookRequest);
        return "Reader purchased book successfully"+payment.getPaymentId();
    }

    @GetMapping("/id/book")
    public List<Payment> readPaymentDetails(@RequestBody Payment payment){
        return userService.getPaymentDetails(payment);
    }

    @GetMapping("/id/books/{bookId}")
    public String readContent(@RequestBody BookRequest bookRequest,@PathVariable Integer bookId){
        return userService.readContent(bookId);
    }

    @GetMapping("/id/books")
    public PaymentModel getPaymentById(@RequestParam Long paymentId){
        return userService.getPaymentDetailsById(paymentId);
    }
}
