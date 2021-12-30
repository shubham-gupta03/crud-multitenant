package com.crud.demo.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.config.dbConfig;
import com.crud.demo.dao.BookDao;
import com.crud.demo.model.Book;

@RestController
public class BookController {
    dbConfig dbconfig = new dbConfig();

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBook(@RequestHeader String tid){
        
        EntityManager em = dbconfig.getEntity(tid);
        BookDao bookDao = new BookDao(em);
        
        return new ResponseEntity<List<Book>>(bookDao.getAllBooks(tid), HttpStatus.OK);
        
    }

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book, @RequestHeader String tid){
        EntityManager em = dbconfig.getEntity(tid);
        BookDao bookDao = new BookDao(em);
        
        return bookDao.saveBook(book, tid);
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book, @RequestHeader String tid){
        EntityManager em = dbconfig.getEntity(tid);
        BookDao bookDao = new BookDao(em);

        Book b = bookDao.findBookById(book.getBook_id(), tid);

        if(b == null) {
            throw new RuntimeException("Book doesn't exist");
        }

        return bookDao.saveBook(book, tid);
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable int id, @RequestHeader String tid){
        EntityManager em = dbconfig.getEntity(tid);
        BookDao bookDao = new BookDao(em);

        Book b = bookDao.findBookById(id, tid);

        if(b == null){
            throw new RuntimeException("Book doesn't exist");
        }
        bookDao.deleteBookById(id, tid);

        return "Deleted book " + id;
    }

}
