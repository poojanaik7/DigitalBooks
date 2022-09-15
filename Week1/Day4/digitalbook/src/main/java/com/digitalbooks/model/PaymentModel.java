package com.digitalbooks.model;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.User;

import javax.persistence.ManyToOne;
import java.util.Date;

public class PaymentModel {

    private Long paymentId;

    private Date paymentDate;

    private String readerName;

    private Integer bookId;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
