package org.etsi.ttcn.tci;

public class TimerStatusImpl implements TimerStatus {
    
    private int status;

    @Override
    public int getTimerStatus() {
        return status;
    }

    @Override
    public void setTimerStatus(int timerStatus) {
        status = timerStatus;
    }

}
