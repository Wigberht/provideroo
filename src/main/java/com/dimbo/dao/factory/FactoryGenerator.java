package com.dimbo.dao.factory;

import com.dimbo.dao.DAOException;
import com.dimbo.managers.DBResourceManager;

public class FactoryGenerator {
    private static DAOAbstractFactory factory;
    
    public static DAOAbstractFactory getFactory() {
        if (factory == null) {
            synchronized (FactoryGenerator.class) {
                if (factory == null) {
                    String dbType = DBResourceManager.getInstance().getParameter("db_type");
                    
                    switch (SupportedDB.valueOf(dbType.toUpperCase())) {
                        case MYSQL:
                            factory = new MySQLDAOFactory();
                            break;
                        
//                        case ORACLE:
//                            factory = new OracleDAOFactory();
//                            break;
                        
                        default:
                            throw new DAOException("Configured Database type is not supported");
                    }
                }
            }
        }
        
        return factory;
    }
    
    private FactoryGenerator() { }
}
