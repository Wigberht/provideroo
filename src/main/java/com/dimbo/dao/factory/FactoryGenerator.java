package com.dimbo.dao.factory;

import com.dimbo.dao.DAOException;
import com.dimbo.managers.DBResourceManager;
import javax.servlet.ServletContext;

public class FactoryGenerator {

    private static DAOAbstractFactory factory;

    private static String dbType;

    public static DAOAbstractFactory getFactory() {
        if (factory == null) {
            synchronized (FactoryGenerator.class) {
                if (factory == null) {
//                    String dbType = DBResourceManager.getInstance().getParameter("db_type");
//                    String dbType = new ServletContext().getInitParameter("db_type");

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

    public static void setDbType(String _dbType) {
        dbType = _dbType;
    }

    private FactoryGenerator() {
    }
}
