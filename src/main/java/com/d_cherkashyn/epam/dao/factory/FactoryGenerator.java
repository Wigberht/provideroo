package com.d_cherkashyn.epam.dao.factory;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.manager.DBResourceManager;

public class FactoryGenerator {
    
    private static DAOAbstractFactory factory;
    
    private static String dbType;
    
    public static DAOAbstractFactory getFactory() {
        if (factory == null) {
            synchronized (FactoryGenerator.class) {
                if (factory == null) {
//                     db_type can be fetched from db parameter file
                    if (dbType == null) {
                        dbType = DBResourceManager.getInstance()
                                                  .getParameter("db_type");
                    }
                    
                    switch (SupportedDB.valueOf(dbType.toUpperCase())) {
                        case MYSQL:
                            factory = new MySQLDAOFactory();
                            break;
                        
                        case ORACLE:
                            factory = new OracleDAOFactory();
                            break;
                        
                        case POSTGRES:
                            factory = new PostgresDAOFactory();
                            break;
                        
                        default:
                            throw new DAOException("Configured Database type is not supported");
                    }
                }
            }
        }
        
        return factory;
    }
    
    public static void setDbType(String _dbType) {
        dbType = _dbType;
    }
    
    private FactoryGenerator() {
    }
}
