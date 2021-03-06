package com.d_cherkashyn.epam;

import com.d_cherkashyn.epam.manager.DBResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that holds functionality to provide a persistent pool of connections to
 * prevent creating new connection on each request. Greatly increases the speed of
 * continuous DB requests
 */
public class ConnectionPool {
    
    static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    private static ConnectionPool instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static boolean available;
    private final int DEFAULT_POOL_SIZE = 10;
    private BlockingQueue<Connection> pool;
    
    private ConnectionPool() {
    }
    
    public static ConnectionPool getInstance() {
        
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
                instance.initPool();
            }
            lock.unlock();
        }
        return instance;
    }
    
    /**
     * Shortcut for ConnectionPool.getInstance().getConnection()
     *
     * @return Connection
     */
    public static Connection conn() {
        return ConnectionPool.getInstance()
                             .getConnection();
    }
    
    /**
     * Shortcut for getInstance().returnConnection(connection)
     *
     * @param connection Connection to return
     */
    public static void returnConn(Connection connection) {
        getInstance().returnConnection(connection);
    }
    
    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        Connection connection = null;
        
        
        if (available) {
            try {
                connection = pool.take();
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                LOGGER.info("Connection taken. Remaining: {}, {}",
                            pool.size() - pool.remainingCapacity(), stackTrace[3]);
            } catch (InterruptedException e) {
            
            }
        }
        return connection;
    }
    
    /**
     * Returns connection.
     *
     * @param connection the connection to return
     */
    public void returnConnection(Connection connection) {
        if (connection != null) {
            pool.offer(connection);
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            LOGGER.info("Connection returned. Remaining: {}, {}",
                        pool.size() - pool.remainingCapacity(), stackTrace[3]);
        }
    }
    
    /**
     * Inits the pool of connections.
     */
    private void initPool() {
        
        String url = DBResourceManager.getInstance()
                                      .getParameter("url");
        String user = DBResourceManager.getInstance()
                                       .getParameter("user");
        String pass = DBResourceManager.getInstance()
                                       .getParameter("password");
        String driver = DBResourceManager.getInstance()
                                         .getParameter("driver");
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unknown driver", e);
        }
        
        int connectionsAmount = Integer
            .parseInt(DBResourceManager.getInstance()
                                       .getParameter("pool_size"));
        
        if (connectionsAmount > 0) {
            pool = new ArrayBlockingQueue<>(connectionsAmount);
        } else {
            connectionsAmount = DEFAULT_POOL_SIZE;
            pool = new ArrayBlockingQueue<>(connectionsAmount);
        }
        
        LOGGER.info(
            "Connections amount on ConnectionPool.initPool(): " + connectionsAmount);
        
        for (int i = 0; i < connectionsAmount; i++) {
            try {
                pool.add(DriverManager.getConnection(url, user, pass));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        available = true;
    }
    
    /**
     * Returns all connections.
     */
    public void returnAllConnections() {
        
        available = false;
        Connection connection = null;
        
        for (int i = 0; i < pool.size(); i++) {
            try {
                connection = pool.take();
                connection.close();
            } catch (InterruptedException | SQLException e) {
                LOGGER.error("Connection was unable to close");
            }
            
            if (connection != null) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    LOGGER.error("Connection was unable to close");
                }
            }
        }
    }
}