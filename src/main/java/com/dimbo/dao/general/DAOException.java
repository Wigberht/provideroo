package com.dimbo.dao.general;

public class DAOException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
