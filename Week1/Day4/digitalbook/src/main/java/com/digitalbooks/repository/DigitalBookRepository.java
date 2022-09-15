package com.digitalbooks.repository;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DigitalBookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT b FROM Book b where (b.category=?1 or b.user.username=?2 or b.price=?3 or b.publisher=?4) and b.activeFlag=1")
    public List<Book> getBookDetails(String category, String author, Long price, String publisher);



}
