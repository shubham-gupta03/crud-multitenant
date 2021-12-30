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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.config.dbConfig;
import com.crud.demo.dao.UserDao;
import com.crud.demo.model.User;

@RestController
public class UserController {

    dbConfig dbconfig = new dbConfig();

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUser(@RequestHeader String tid){
        
        EntityManager em = dbconfig.getEntity(tid);

        UserDao userDao = new UserDao(em);

        return new ResponseEntity<List<User>>(userDao.getAllUsers(tid), HttpStatus.OK);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user, @RequestHeader String tid) {
        EntityManager em = dbconfig.getEntity(tid);

        UserDao userDao = new UserDao(em);
        return(userDao.saveUser(user, tid));
    }

    @PutMapping("/user")
    public User updatUser(@RequestBody User user, @RequestHeader String tid){
        EntityManager em = dbconfig.getEntity(tid);
        UserDao userDao = new UserDao(em);

        User u = userDao.findUserById(user.getUser_id(), tid);

        if(u == null) {
            throw new RuntimeException("User doesn't exist");
        }

        return (userDao.saveUser(user, tid));
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id, @RequestHeader String tid){
        EntityManager em = dbconfig.getEntity(tid);
        UserDao userDao = new UserDao(em);

        User u = userDao.findUserById(id, tid);

        if(u == null){
            throw new RuntimeException("User doesn't exist");
        }
        
        userDao.deleteUserById(id, tid);
        return "Deleted user " + id;
    }
}

