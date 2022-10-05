package com.digitalbooks.repository;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DigitalBookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT b FROM Book b where b.title=?1 and b.user.username=?2 and b.publisher=?3 and b.activeFlag=1")
    public List<Book> getBookDetails(String title, String author, String publisher);

    public List<Book> findByUserUserId(Integer userId);
}
