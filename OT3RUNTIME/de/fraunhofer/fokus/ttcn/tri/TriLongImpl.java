package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriLong;

public class TriLongImpl implements TriLong {
    
    private long val;

    @Override
    public void setLongValue(long value) {
        val = value;
    }

    @Override
    public long getLongValue() {
        return val;
    }

}
