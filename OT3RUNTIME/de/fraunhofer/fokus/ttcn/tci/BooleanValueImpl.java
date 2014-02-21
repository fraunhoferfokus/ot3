package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.BooleanValue;

public class BooleanValueImpl extends ValueImpl implements BooleanValue {
    
    private boolean val;
    
    public BooleanValueImpl() {
        super(TypeImpl.booleanType, null, null);
    }
    
    @Override
    public void setBoolean(boolean value) {
        val = value;
        present = true;
    }

    @Override
    public boolean getBoolean() {
        return val;
    }

}
