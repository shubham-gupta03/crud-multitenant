package com.crud.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.crud.demo.model.User;

public class UserDao {

    private EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }

    public List<User> getAllUsers(String tid) {
        Query q = (Query) em.createQuery("from User");
        List<User> users = q.getResultList();

        return users;
    }

    public User saveUser(User user, String tid) {
        User u = em.merge(user);
        user.setUser_id(u.getUser_id());
        
        em.getTransaction().commit();
        return user;
    }

    public User findUserById(int id, String tid) {
        User u = em.find(User.class, id);
        return u;
    }

    public void deleteUserById(int id, String tid) {
        Query q = (Query) em.createQuery("delete from User where id=:uId");
        q.setParameter("uId", id);
        q.executeUpdate();
        
        em.getTransaction().commit();   
    }

    
}
