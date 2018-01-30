package com.d_cherkashyn.epam.dao.factory;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.manager.DBResourceManager;

/**
 * Generates a factory of DAO. Singleton
 */
public class FactoryGenerator {
    
    private static DAOAbstractFactory factory;
    
    private static String dbType;
    
    /**
     * Gets a factory
     *
     * @return instance of factory for database currently in use by system
     */
    public static DAOAbstractFactory getFactory() {
        if (factory == null) {
            synchronized (FactoryGenerator.class) {
                if (factory == null) {
                    if (dbType == null) {
                        dbType = DBResourceManager.getInstance()
                                                  .getParameter("db_type");
                    }
                    
                    switch (SupportedDB.valueOf(dbType.toUpperCase())) {
                        case MYSQL:
                            factory = new MySQLDAOFactory();
                            break;
                        
                        case POSTGRES:
                            factory = new PostgresDAOFactory();
                            break;
                        
                        default:
                            throw new DAOException(
                                "Configured Database type is not supported");
                    }
                }
            }
        }
        
        return factory;
    }
    
    /**
     * Setups the database type for continuous usage by system
     */
    public static void setDbType(String _dbType) {
        dbType = _dbType;
    }
    
    /**
     * private constructor to stay in shape of a singleton
     */
    private FactoryGenerator() { }
}
