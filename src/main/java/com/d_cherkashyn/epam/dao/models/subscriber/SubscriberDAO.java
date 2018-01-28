package com.d_cherkashyn.epam.dao.models.subscriber;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Subscriber;

import java.util.List;

/**
 * Interface with methods required to manage Subscriber in DB as DAO
 */
public interface SubscriberDAO {
    
    /**
     * Fetch ALL of the subscribers
     *
     * @return
     * @throws DAOException
     */
    List<Subscriber> all() throws DAOException;
    
    /**
     * Fetch limited amount of subscribers
     *
     * @param limited
     * @return
     * @throws DAOException
     */
    List<Subscriber> all(boolean limited) throws DAOException;
    
    
    /**
     * Fetch limited amount of subscribers
     *
     * @param limit
     * @return
     * @throws DAOException
     */
    List<Subscriber> all(int limit) throws DAOException;
    
    /**
     * Fetch limited amount of subscribers
     *
     * @param limit
     * @param offset
     * @return
     * @throws DAOException
     */
    List<Subscriber> all(int limit, int offset) throws DAOException;
    
    /**
     * Find subscriber by his user ID
     *
     * @param id
     * @return
     * @throws DAOException
     */
    Subscriber findByUserId(Long id) throws DAOException;
    
    
    /**
     * Get total amount of subscribers in system
     *
     * @return
     * @throws DAOException
     */
    long numberOfSubscribers() throws DAOException;
    
    /**
     * Find subscriber by subscriber id
     *
     * @param id
     * @return
     * @throws DAOException
     */
    Subscriber find(Long id) throws DAOException;
    
    /**
     * Find subscriber by first and last names
     *
     * @param firstName
     * @param lastName
     * @return
     * @throws DAOException
     */
    Subscriber find(String firstName, String lastName) throws DAOException;
    
    /**
     * Calculate debt of a subscriber
     *
     * @param id
     * @return
     * @throws DAOException
     */
    double calculateDebt(long id) throws DAOException;
    
    /**
     * Gets a list of subscribers that have debts for services for current date
     *
     * @return
     * @throws DAOException
     */
    List<Subscriber> findSubscriptionExpirers() throws DAOException;
    
    /**
     * Deletes a subscriber
     *
     * @param id
     * @return
     * @throws DAOException
     */
    boolean delete(Long id) throws DAOException;
    
    /**
     * Updates a subscriber
     *
     * @param subscriber
     * @return
     * @throws DAOException
     */
    boolean update(Subscriber subscriber) throws DAOException;
    
    /**
     * Creates a subscriber
     *
     * @param subscriber
     * @return
     * @throws DAOException
     */
    Subscriber create(Subscriber subscriber) throws DAOException;
    
    List<Subscriber> search(String word1, String word2,
                            String word3) throws DAOException;
    
}
