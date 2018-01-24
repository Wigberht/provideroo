package sandbox;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.helper.service.SubscriberService;

import java.sql.Connection;

public class SubscriptionJobTest {
    public static void main(String[] args) {
//        SubscriberService subscriberService = new SubscriberService();
//
//        System.out.printf("Collected fees: %f",
//                          subscriberService.collectSubscriptionFees());
//
//        subscriberService.returnConnection();
        
        Connection connection = ConnectionPool.conn();
//        FactoryGenerator.getFactory().makeTariffDAO(connection).search("internet");
        ConnectionPool.returnConn(connection);
    }
    
    
}
