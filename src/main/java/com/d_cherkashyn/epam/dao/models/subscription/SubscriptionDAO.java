package com.d_cherkashyn.epam.dao.models.subscription;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Subscription;

import java.util.List;

public interface SubscriptionDAO {
    
    Subscription find(long id) throws DAOException;
    
    Subscription find(long tariffId, long subscriberId) throws DAOException;
    
    List<Subscription> findBySubscriber(long id) throws DAOException;
    
    boolean delete(Long id) throws DAOException;
    
    boolean update(Subscription subscription) throws DAOException;
    
    boolean prolongSubscriptions(long subscriberId) throws DAOException;
    
    Subscription create(Subscription subscription) throws DAOException;
    
}
