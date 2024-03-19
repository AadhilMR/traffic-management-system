package com.aadhil.central.message;

import com.aadhil.central.remote.DataPersister;
import com.aadhil.dto.Vehicle;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "tmsVehicleQueue")
        }
)
public class VehicleDataReceiver implements MessageListener {

    @EJB
    DataPersister dataPersister;

    @Override
    public void onMessage(Message message) {
        try {
            Vehicle vehicle = (Vehicle) ((ObjectMessage) message).getObject();

            dataPersister.save(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
