package com.d_cherkashyn.epam.dao.models.subscriber;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.factory.DAOFactoryGenerator;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SubscriberDAOMySQLTest {
    @Test
    public void testCalculateDebtIsDouble() {
        long subscriberId = 24;
        Connection connection = ConnectionPool.conn();
        
        assertThat(DAOFactoryGenerator.getFactory()
                                      .makeSubscriberDAO()
                                      .calculateDebt(subscriberId),
                   CoreMatchers.instanceOf(Double.class)
        );
        
        ConnectionPool.returnConn(connection);
        
    }
    
    @Test
    public void testCalculateDebtIsZeroOrMore() {
        long subscriberId = 24;
        Connection connection = ConnectionPool.conn();
        
        assertTrue(DAOFactoryGenerator.getFactory()
                                      .makeSubscriberDAO()
                                      .calculateDebt(subscriberId) >= 0);
        
        ConnectionPool.returnConn(connection);
        
    }
}
