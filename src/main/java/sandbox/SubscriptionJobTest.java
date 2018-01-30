package sandbox;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.factory.DAOFactory;
import com.d_cherkashyn.epam.model.Subscriber;

import java.sql.Connection;
import java.util.List;

/**
 * Testing class that can be used by developer to oversee the behaviour of functions in
 * created system.
 */
public class SubscriptionJobTest {
    public static void main(String[] args) {
        
        long subs = 2;
        long size = 10;
        
        int pages = (int) Math.ceil((double) subs / size);
        System.out.println(pages);
        
        Connection connection = ConnectionPool.conn();
        List<Subscriber> subscribers = DAOFactory.getFactory()
                                                 .makeSubscriberDAO()
                                                 .all();
        System.out.println(subscribers.size());
        
    }
    
}
