package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriTimerId;

public class TriTimerIdImpl implements TriTimerId {
    
    private String name;
    
    public TriTimerIdImpl(String timerId) {
        name = timerId;
    }

    @Override
    public String getTimerName() {
        return name;
    }

    @Override
    public boolean equals(TriTimerId timer) {
        return name == timer.getTimerName();
    }

}
