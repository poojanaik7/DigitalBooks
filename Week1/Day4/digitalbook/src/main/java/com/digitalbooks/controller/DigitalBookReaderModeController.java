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
@RequestMapping("/api/digitalbooks/reader")
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
        return "Reader purchased book successfully. Payment Id:"+payment.getPaymentId();
    }

    @GetMapping("/{id}/books")
    public List<PaymentModel> readPaymentDetails(@PathVariable Integer id){
        return userService.getPaymentDetails(id);
    }

    @GetMapping("/books/{bookId}")
    public String readContent(@PathVariable Integer bookId){
        return userService.readContent(bookId);
    }

    @GetMapping("/books")
    public PaymentModel getPaymentById(@RequestParam Long paymentId){
        return userService.getPaymentDetailsById(paymentId);
    }
}
