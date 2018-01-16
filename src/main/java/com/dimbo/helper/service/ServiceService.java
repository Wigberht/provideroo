package com.dimbo.helper.service;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.service.ServiceDAO;
import com.dimbo.model.Service;

import java.util.List;

public class ServiceService extends ServiceHelper {
    
    public Service createService(Service service) {
        ServiceDAO serviceDAO = FactoryGenerator.getFactory()
                                                .makeServiceDAO(connection);
        try {
            return serviceDAO.create(service);
        } catch (DAOException ex) {
            return null;
        }
    }
    
    public List<Service> getAllServices() {
        ServiceDAO serviceDAO = FactoryGenerator.getFactory()
                                                .makeServiceDAO(connection);
        return serviceDAO.all();
    }
}
