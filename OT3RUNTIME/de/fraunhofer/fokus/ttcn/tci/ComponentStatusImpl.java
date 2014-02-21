package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.ComponentStatus;

public class ComponentStatusImpl implements ComponentStatus {
    
    private int status;

    @Override
    public int getComponentStatus() {
        return status;
    }

    @Override
    public void setComponentStatus(int componentStatus) {
        status = componentStatus;
    }

}
