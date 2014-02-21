package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.EnumeratedValue;

public class EnumeratedValueImpl extends ValueImpl implements EnumeratedValue {
    
    private String val;
    private int intval;

    public EnumeratedValueImpl() {
        super(TypeImpl.enumeratedType, null, null);
    }

    @Override
    public String getEnum() {
        return val;
    }

    @Override
    public void setEnum(String enumValue) {
        val = enumValue;
        present = true;
    }

    @Override
    public int getInt() {
        return intval;
    }

    @Override
    public void setInt(int intValue) {
        intval = intValue;
        present = true;
    }

}
