package sandbox;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.factory.FactoryGenerator;
import com.d_cherkashyn.epam.manager.MessagesResourceManager;
import com.d_cherkashyn.epam.manager.ResourceTypes;
import com.d_cherkashyn.epam.model.Subscriber;
import com.d_cherkashyn.epam.model.Tariff;

import java.sql.Connection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
        List<Subscriber> subscribers = FactoryGenerator.getFactory()
                                                       .makeSubscriberDAO()
                                                       .all();
        System.out.println(subscribers.size());
        
    }
    
}
