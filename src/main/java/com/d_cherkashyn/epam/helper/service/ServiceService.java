package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.factory.DAOFactory;
import com.d_cherkashyn.epam.dao.models.service.ServiceDAO;
import com.d_cherkashyn.epam.helper.SortOrders;
import com.d_cherkashyn.epam.model.Service;
import com.d_cherkashyn.epam.model.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ServiceService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceService.class);
    
    public static Service createService(Service service) {
        Service createdService = null;
        ServiceDAO serviceDAO = DAOFactory.getFactory()
                                          .makeServiceDAO();
        try {
            createdService = serviceDAO.create(service);
        } catch (DAOException ex) {
            LOGGER.info("Unable to create service", ex);
        }
        
        return createdService;
    }
    
    public static List<Service> getAllServices() {
        List<Service> services = DAOFactory.getFactory()
                                           .makeServiceDAO()
                                           .all();
        for (Service service : services) {
            List<Tariff> tariffs = DAOFactory.getFactory()
                                             .makeTariffDAO()
                                             .findByService(service.getId());
            service.setTariffs(tariffs);
        }
        
        return services;
    }
    
    public static List<Service> getAllServices(String sortField, String sortOrder) {
        List<Service> services = DAOFactory.getFactory()
                                           .makeServiceDAO()
                                           .all();
        for (Service service : services) {
            List<Tariff> tariffs = DAOFactory.getFactory()
                                             .makeTariffDAO()
                                             .findByServiceSorted(service.getId(),
                                                                  sortField,
                                                                  sortOrder);
            service.setTariffs(tariffs);
        }
        
        return services;
    }
    
    public static List<Service> getSortedServices(String field, String order) {
        boolean fieldValid = false;
        boolean orderValid = false;
        for (ServiceSortFields sortFields : ServiceSortFields.values()) {
            if (sortFields.name().equalsIgnoreCase(field)) fieldValid = true;
        }
        
        for (SortOrders sortOrders : SortOrders.values()) {
            if (sortOrders.name().equalsIgnoreCase(order)) orderValid = true;
        }
        
        /*return standard set of services in case of invalid sorting request parameters*/
        if (!(orderValid && fieldValid)) {
            return getAllServices();
        } else {
            return getAllServices(field, order);
        }
    }
}
