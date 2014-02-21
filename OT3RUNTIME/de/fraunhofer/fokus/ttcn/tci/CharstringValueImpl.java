package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.CharstringValue;

public class CharstringValueImpl extends ValueImpl implements CharstringValue {
    
    private StringBuffer val;
    private int length;
    
    public CharstringValueImpl() {
        super(TypeImpl.charstringType, null, null);
    }

    @Override
    public String getString() {
        return val.toString();
    }

    @Override
    public void setString(String value) {
        val = new StringBuffer(value);
        present = true;
    }

    @Override
    public char getChar(int position) {
        return val.charAt(position);
    }

    @Override
    public void setChar(int position, char value) {
        val.setCharAt(position, value);
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void setLength(int len) {
        length = len;
    }

}
