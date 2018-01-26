package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.factory.FactoryGenerator;
import com.d_cherkashyn.epam.dao.models.service.ServiceDAO;
import com.d_cherkashyn.epam.model.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ServiceService extends ServiceHelper {
    
    public ServiceService() {
        super();
    }
    
    public ServiceService(Connection connection) {
        super(connection);
    }
    
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
        return FactoryGenerator.getFactory()
                               .makeServiceDAO(connection)
                               .all();
        
    }
    
    public List<Service> getAllServices(String sort, boolean ascOrder) {
        if (sort == null) {
            return getAllServices();
        } else {
            return getSortedServices(sort, ascOrder);
        }
    }
    
    public String getSortType(HttpServletRequest request) {
        HttpSession s = request.getSession();
        
        if (request.getParameter("sort") != null) {
            s.setAttribute("sort", request.getParameter("sort"));
        }
        
        return s.getAttribute("sort") == null
            ? ""
            : s.getAttribute("sort").toString();
    }
    
    private List<Service> getSortedServices(String sort, boolean ascOrder) {
        ServiceDAO serviceDAO = FactoryGenerator.getFactory()
                                                .makeServiceDAO(connection);
        List<Service> sortedServices = new ArrayList<>();
        
        for (Service service : serviceDAO.all()) {
            service.getTariffs().sort((t1, t2) -> {
                switch (sort) {
                    case "title":
                        return ascOrder
                            ? t1.getTitle().compareTo(t2.getTitle())
                            : t2.getTitle().compareTo(t1.getTitle());
                    case "price":
                        return ascOrder
                            ? (int) t1.getCost() - (int) t2.getCost()
                            : (int) t2.getCost() - (int) t1.getCost();
                    
                    default:
                        return 0;
                }
            });
            sortedServices.add(service);
        }
        
        return sortedServices;
    }
}
