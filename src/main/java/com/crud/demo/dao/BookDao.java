package com.crud.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.crud.demo.model.Book;


public class BookDao {
	
    private EntityManager em;

    public BookDao(EntityManager em) {
        this.em = em;
    }

    public List<Book> getAllBooks(String tid) {
        Query q = (Query) em.createQuery("from Book");
        List<Book> books = q.getResultList();

        return books;
    }

    public Book saveBook(Book book, String tid) {
        Book b = em.merge(book);
        book.setBook_id(b.getBook_id());
        
        em.getTransaction().commit();        
        return book;
    }

    public Book findBookById(int id, String tid) {
        Book b = em.find(Book.class, id);
        
        return b;
    }

    public void deleteBookById(int id, String tenant_id) {
        Query q = (Query) em.createQuery("delete from Book where id=:bId");
        q.setParameter("bId", id);
        q.executeUpdate();
        
        em.getTransaction().commit();        
    }

}

