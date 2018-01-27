package com.d_cherkashyn.epam.dao.models.subscriber;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Subscriber;

import java.util.List;

public interface SubscriberDAO {
    
    List<Subscriber> all() throws DAOException;
    
    List<Subscriber> all(boolean limited) throws DAOException;
    
    List<Subscriber> all(int limit) throws DAOException;
    
    List<Subscriber> all(int limit, int offset) throws DAOException;
    
    Subscriber findByUserId(Long id) throws DAOException;
    
    Subscriber find(Long id) throws DAOException;
    
    Subscriber find(String firstName, String lastName) throws DAOException;
    
    double calculateDebt(long id) throws DAOException;
    
    List<Subscriber> findSubscriptionExpirers() throws DAOException;
    
    boolean delete(Long id) throws DAOException;
    
    boolean update(Subscriber subscriber) throws DAOException;
    
    Subscriber create(Subscriber subscriber) throws DAOException;
    
}
