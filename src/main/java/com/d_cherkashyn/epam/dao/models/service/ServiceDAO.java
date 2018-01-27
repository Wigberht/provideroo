package com.d_cherkashyn.epam.dao.models.service;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Service;
import java.util.List;

public interface ServiceDAO {

    List<Service> all() throws DAOException;
    
    List<Service> all(String sort) throws DAOException;

    Service find(Long id) throws DAOException;

    boolean delete(Long id) throws DAOException;

    Service update(Service service) throws DAOException;

    Service create(Service service) throws DAOException;

}
