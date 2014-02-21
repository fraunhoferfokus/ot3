package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.IntegerValue;

public class IntegerValueImpl extends ValueImpl implements IntegerValue {
    
    private int val;

    public IntegerValueImpl() {
        super(TypeImpl.integerType, null, null);
    }
    
    @Override
    public void setInteger(int value) {
        val = value;
        present = true;
    }

    @Override
    public int getInteger() {
        return val;
    }

}
