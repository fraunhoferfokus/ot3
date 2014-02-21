package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciStatus;

public class TciStatusImpl implements TciStatus {
    
    private int status;

    @Override
    public int getTciStatus() {
        return status;
    }

    @Override
    public void setTciStatus(int tciStatus) {
        status = tciStatus;
    }

}
