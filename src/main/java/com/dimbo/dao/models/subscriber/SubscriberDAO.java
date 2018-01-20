package com.dimbo.dao.models.subscriber;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Subscriber;
import java.util.List;

public interface SubscriberDAO {

    public List<Subscriber> all() throws DAOException;

    public List<Subscriber> all(boolean limited) throws DAOException;

    public List<Subscriber> all(int limit) throws DAOException;

    public List<Subscriber> all(int limit, int offset) throws DAOException;
    
    public Subscriber findByUserId(Long id) throws DAOException;

    public Subscriber find(Long id) throws DAOException;

    public Subscriber find(String firstName, String lastName) throws DAOException;

    public boolean delete(Long id) throws DAOException;

    public boolean update(Subscriber subscriber) throws DAOException;

    public Subscriber create(Subscriber subscriber) throws DAOException;

}
