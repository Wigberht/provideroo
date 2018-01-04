package com.dimbo;

import com.dimbo.managers.ResourceManagerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;


/**
 * The Class ConnectionPool.
 */
public class ConnectionPool {

    /**
     * The Constant LOGGER.
     */
    static final Logger LOGGER = Logger.getLogger(String.valueOf(ConnectionPool.class));

    /**
     * The default pool size.
     */
    private final int DEFAULT_POOL_SIZE = 10;

    /**
     * The instance.
     */
    private static ConnectionPool instance = null;

    /**
     * The lock.
     */
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * The pool of connections.
     */
    private BlockingQueue<Connection> pool;

    /**
     * pool can not grant connections if false.
     */
    private static boolean available;

    /**
     * Instantiates a new connection pool.
     */
    private ConnectionPool() {
    }

    /**
     * Gets the single instance of ConnectionPool.
     *
     * @return single instance of ConnectionPool
     */
    public static ConnectionPool getInstance() {

        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    instance.initPool();
                }
            } finally {
                lock.unlock();
            }

        }
        return instance;
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
        }
    }

    /**
     * Inits the pool of connections.
     */
    private void initPool() {

        String url = ResourceManagerFactory.INSTANCE.dbManager().getParameter("url");
        String user = ResourceManagerFactory.INSTANCE.dbManager().getParameter("user");
        String pass = ResourceManagerFactory.INSTANCE.dbManager().getParameter("password");
        String driver = ResourceManagerFactory.INSTANCE.dbManager().getParameter("driver");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unknown driver", e);
        }

        int connectionsAmount = Integer.parseInt(ResourceManagerFactory.INSTANCE.dbManager().getParameter("pool_size"));

        if (connectionsAmount > 0) {
            pool = new ArrayBlockingQueue<>(connectionsAmount);
        } else {
            connectionsAmount = DEFAULT_POOL_SIZE;
            pool = new ArrayBlockingQueue<>(connectionsAmount);
        }

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
                pool.take().close();
            } catch (InterruptedException | SQLException e) {

            }

            if (connection != null) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {

                }
            }
        }
    }
}

