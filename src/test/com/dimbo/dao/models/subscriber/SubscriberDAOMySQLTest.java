package com.dimbo.dao.models.subscriber;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
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
        
        assertThat(FactoryGenerator.getFactory()
                                   .makeSubscriberDAO(connection)
                                   .calculateDebt(subscriberId),
                   CoreMatchers.instanceOf(Double.class)
        );
        
        ConnectionPool.returnConn(connection);
        
    }
    
    @Test
    public void testCalculateDebtIsZeroOrMore() {
        long subscriberId = 24;
        Connection connection = ConnectionPool.conn();
        
        assertTrue(FactoryGenerator.getFactory()
                                   .makeSubscriberDAO(connection)
                                   .calculateDebt(subscriberId) >= 0);
        
        ConnectionPool.returnConn(connection);
        
    }
}
