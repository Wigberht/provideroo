package sandbox;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.factory.FactoryGenerator;
import com.d_cherkashyn.epam.manager.MessagesResourceManager;
import com.d_cherkashyn.epam.manager.ResourceTypes;
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
        
        Connection connection = ConnectionPool.conn();
        List<Tariff> tariffList = FactoryGenerator.getFactory()
                                                  .makeTariffDAO(connection)
                                                  .findByServiceSorted(15, "title",
                                                                       "DESC");
        
        for (Tariff tariff : tariffList) {
            System.out.println(tariff);
        }
    }
    
}
