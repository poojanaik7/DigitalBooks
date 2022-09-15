package com.digitalbooks.repository;

import com.digitalbooks.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DigitalBookRepository extends CrudRepository<Book, Integer> {

    @Query(value = "SELECT b FROM Book b where b.category=?1 and b.author=?2 and b.price=?3 and b.publisher=?4")
    public List<Book> getBookDetails(String category, String author, Long price, String publisher);

}
