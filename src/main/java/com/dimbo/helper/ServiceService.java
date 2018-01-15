package com.dimbo.helper;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.DAOException;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.service.ServiceDAO;
import com.dimbo.model.Service;
import com.dimbo.model.Subscriber;

import java.sql.Connection;

public class ServiceService {
    
    Connection connection;
    
    public ServiceService() {
        connection = ConnectionPool.conn();
    }
    
    public Service createService(Service service) {
        ServiceDAO serviceDAO = FactoryGenerator.getFactory().makeServiceDAO(connection);
        
        try {
            return serviceDAO.create(service);
        } catch (DAOException ex) {
            return null;
        }
    }
    
    public void returnConnection() {
        ConnectionPool.returnConn(connection);
    }
}
