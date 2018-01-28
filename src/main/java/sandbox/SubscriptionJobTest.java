package sandbox;

import com.d_cherkashyn.epam.manager.MessagesResourceManager;
import com.d_cherkashyn.epam.manager.ResourceTypes;

import java.util.Locale;
import java.util.ResourceBundle;

public class SubscriptionJobTest {
    public static void main(String[] args) {
        String lang = "ru_RU";
        
        ResourceBundle rb = ResourceBundle.getBundle(ResourceTypes.messages.name(),
                                                     Locale.forLanguageTag(lang));
        System.out.println(rb.getString("login"));
    }
    
}
