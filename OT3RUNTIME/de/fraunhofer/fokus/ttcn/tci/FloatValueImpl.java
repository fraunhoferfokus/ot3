package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.FloatValue;

public class FloatValueImpl extends ValueImpl implements FloatValue {

    private float val;
    
    public FloatValueImpl() {
        super(TypeImpl.floatType, null, null);
    }

    @Override
    public void setFloat(float value) {
        val = value;
        present = true;
    }

    @Override
    public float getFloat() {
        return val;
    }

}
