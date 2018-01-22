package sandbox;

import com.dimbo.helper.service.SubscriberService;

public class SubscriptionJobTest {
    public static void main(String[] args) {
        SubscriberService subscriberService = new SubscriberService();
        
        System.out.printf("Collected fees: %f",
                          subscriberService.collectSubscriptionFees());
        
        subscriberService.returnConnection();
    }
}
