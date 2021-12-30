package com.crud.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.crud.demo.model.Sale;


public class SaleDao {

    private EntityManager em;

    public SaleDao(EntityManager em) {
        this.em = em;
    }

    public List<Sale> getAllSale(String tid) {
        Query q = (Query) em.createQuery("from Sale");
        List<Sale> sales = q.getResultList();

        return sales;
    }

    public Sale saveSale(Sale sale, String tid) {
        Sale s = em.merge(sale);
        sale.setSale_id(s.getSale_id());
        
        em.getTransaction().commit();
        return sale;
    }

    public Sale findSaleById(int id, String tid) {
        Sale s = em.find(Sale.class, id);
        return s;
    }

    public void deleteSalesById(int id, String tid) {
        Query q = (Query) em.createQuery("delete from Sale where id=:sId");
        q.setParameter("sId", id);
        q.executeUpdate();
        
        em.getTransaction().commit();        
    }
    
}
