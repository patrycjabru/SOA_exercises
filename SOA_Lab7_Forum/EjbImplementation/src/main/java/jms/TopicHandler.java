package jms;

import ejb.interfaces.NotificationManager;
import ejb.interfaces.SubscriptionManager;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/topic/SOA_Test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class TopicHandler implements MessageListener{
    @EJB
    private SubscriptionManager subscriptionManagerBean;
    @EJB
    private NotificationManager notificationManagerBean;

    public void onMessage(Message message) {
        //TODO implement
    }
}
