package com.dimbo.dao.models.subscription;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Subscription;

import java.util.List;

public interface SubscriptionDAO {
    
    Subscription find(long id) throws DAOException;
    
    Subscription find(long tariffId, long subscriberId) throws DAOException;
    
    List<Subscription> findBySubscriber(long id) throws DAOException;
    
    List<Subscription> findTodaysSubscriptions() throws DAOException;
    
    boolean delete(Long id) throws DAOException;
    
    boolean update(Subscription subscription) throws DAOException;
    
    boolean prolongSubscriptions(long subscriberId) throws DAOException;
    
    Subscription create(Subscription subscription) throws DAOException;
    
}
