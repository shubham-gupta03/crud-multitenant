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
import com.crud.demo.dao.SaleDao;
import com.crud.demo.model.Sale;

@RestController
public class SaleController {

    dbConfig dbconfig = new dbConfig();

    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> getSale(@RequestHeader String tid){
        EntityManager em = dbconfig.getEntity(tid);
        SaleDao saleDao = new SaleDao(em);
        return new ResponseEntity<List<Sale>>(saleDao.getAllSale(tid), HttpStatus.OK);
    }

    @PostMapping("/sale")
    public Sale addSale( @RequestBody Sale sale, @RequestHeader String tid){
        EntityManager em = dbconfig.getEntity(tid);

        SaleDao saleDao = new SaleDao(em);
        return(saleDao.saveSale(sale, tid));
    }

    @PutMapping("/sale")
    public Sale updatSale(@RequestBody Sale sale, @RequestHeader String tid){
        EntityManager em = dbconfig.getEntity(tid);
        SaleDao saleDao = new SaleDao(em);
        Sale s = saleDao.findSaleById(sale.getSale_id(), tid);

        if(s == null) {
            throw new RuntimeException("Sale doesn't exist");
        }

        return (saleDao.saveSale(sale, tid));
    }

    @DeleteMapping("/sale/{id}")
    public String deleteUser(@PathVariable int id, @RequestHeader String tid){
        EntityManager em = dbconfig.getEntity(tid);
        SaleDao saleDao = new SaleDao(em);

        Sale s = saleDao.findSaleById(id, tid);

        if(s == null){
            throw new RuntimeException("Sale doesn't exist");
        }
        saleDao.deleteSalesById(id, tid);

        return "Deleted sale " + id;
    }
    
}

