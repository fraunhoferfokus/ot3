package de.fraunhofer.fokus.ttcn.tci;

import java.util.BitSet;
import org.etsi.ttcn.tci.BitstringValue;

public class BitstringValueImpl extends ValueImpl implements BitstringValue {
    
    private BitSet val;
    private int length = 0;
    
    public BitstringValueImpl() {
        super(TypeImpl.bitstringType, null, null);
        val = new BitSet();
    }
    
    @Override
    public String getString() {
        StringBuffer result = new StringBuffer(val.size());
        for (int i=0; i<val.size(); i++)
            if (val.get(i))
                result.setCharAt(i, '1');
            else
                result.setCharAt(i, '0');
        return "'" + result.toString() + "'B";
    }

    @Override
    public void setString(String value) {
        val = new BitSet(value.length()-3);
        for (int i=0; i<value.length()-3; i++)
           val.set(i, value.charAt(i+1)=='1');
        present = true;

    }

    @Override
    public int getBit(int position) {
        return val.get(position)?1:0;
    }

    @Override
    public void setBit(int position, int value) {
        val.set(position, value!=0);
        present = true;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void setLength(int len) {
    	length = len;
        val = new BitSet(len);
    }

}
