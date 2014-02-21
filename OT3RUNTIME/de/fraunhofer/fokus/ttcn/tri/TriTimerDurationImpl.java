package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriTimerDuration;

public class TriTimerDurationImpl implements TriTimerDuration {
    
    private double dur;

    @Override
    public double getDuration() {
        return dur;
    }

    @Override
    public void setDuration(double duration) {
        dur = duration;
    }

    @Override
    public boolean equals(TriTimerDuration duration) {
        return dur == duration.getDuration();
    }

}
