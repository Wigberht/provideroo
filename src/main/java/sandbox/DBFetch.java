package sandbox;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.message.MessageDAO;
import com.dimbo.model.Message;

import java.sql.Connection;

public class DBFetch {
    public static void main(String[] args) {
        Connection connection = ConnectionPool.conn();
        
        MessageDAO messageDAO = FactoryGenerator.getFactory().makeMessageDAO(connection);
        for (Message message : messageDAO.getMessages(1)) {
            System.out.println(message.getMessage());
        }
    }
}
