package com.aadhil.central.message;

import com.aadhil.central.remote.DataPersister;
import com.aadhil.dto.TrafficIntersection;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "tmsTrafficQueue")
        }
)
public class TrafficIntersectionDataReceiver implements MessageListener {

    @EJB
    DataPersister dataPersister;

    @Override
    public void onMessage(Message message) {
        try {
            TrafficIntersection trafficIntersection = (TrafficIntersection) ((ObjectMessage) message).getObject();

            dataPersister.save(trafficIntersection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
