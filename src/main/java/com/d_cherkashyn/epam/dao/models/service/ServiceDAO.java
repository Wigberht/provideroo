package com.d_cherkashyn.epam.dao.models.service;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Service;

import java.util.List;

/**
 * Interface with methods required to manage Service in DB as DAO
 */
public interface ServiceDAO {
    
    /**
     * Fetch ALL services
     *
     * @return
     * @throws DAOException
     */
    List<Service> all() throws DAOException;
    
    /**
     * Find service by id
     *
     * @param id
     * @return
     * @throws DAOException
     */
    Service find(Long id) throws DAOException;
    
    /**
     * Delete by id
     *
     * @param id
     * @return
     * @throws DAOException
     */
    boolean delete(Long id) throws DAOException;
    
    /**
     * Update service
     *
     * @param service
     * @return
     * @throws DAOException
     */
    Service update(Service service) throws DAOException;
    
    /**
     * Creates service
     *
     * @param service
     * @return
     * @throws DAOException
     */
    Service create(Service service) throws DAOException;
    
}
