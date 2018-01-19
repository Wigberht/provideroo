package com.dimbo.dao.models.service;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Service;
import java.util.List;

public interface ServiceDAO {

    public List<Service> all() throws DAOException;

    public Service find(Long id) throws DAOException;

    public boolean delete(Long id) throws DAOException;

    public Service update(Service service) throws DAOException;

    public Service create(Service service) throws DAOException;

}
