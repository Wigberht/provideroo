package com.d_cherkashyn.epam.dao;

/**
 * Exception that DAO current DAO implementation can throw
 */
public class DAOException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public DAOException(String message) {
        super(message);
    }
    
    public DAOException(Throwable cause) {
        super(cause);
    }
    
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
