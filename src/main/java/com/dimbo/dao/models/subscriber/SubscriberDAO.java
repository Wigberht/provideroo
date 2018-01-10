package com.dimbo.dao.models.subscriber;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Subscriber;
import java.util.List;

public interface SubscriberDAO {

    public List<Subscriber> all() throws DAOException;

    public Subscriber find(Long id) throws DAOException;

    public Subscriber find(String firstName, String lastName) throws DAOException;

    public boolean delete(Long id) throws DAOException;

    public Subscriber update(Subscriber subscriber) throws DAOException;

    public Subscriber create(Subscriber subscriber) throws DAOException;

}
