package com.d_cherkashyn.epam.dao.models.account;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Account;

/**
 * Interface with methods required to manage Account in DB as DAO
 */
public interface AccountDAO {
    
    /**
     * Find a account by id
     *
     * @param id
     * @return Account
     * @throws DAOException
     */
    Account find(Long id) throws DAOException;
    
    /**
     * Delete account by ID
     *
     * @param id
     * @return
     * @throws DAOException
     */
    boolean delete(Long id) throws DAOException;
    
    /**
     * Update account and return success true/false
     *
     * @param account
     * @return
     * @throws DAOException
     */
    boolean update(Account account) throws DAOException;
    
    /**
     * Create account and return new one with id
     *
     * @param account
     * @return
     * @throws DAOException
     */
    Account create(Account account) throws DAOException;
    
}
