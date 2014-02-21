package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.HexstringValue;

public class HexstringValueImpl extends ValueImpl implements HexstringValue {
    
    private StringBuffer val;
    
    public HexstringValueImpl() {
        super(TypeImpl.hexstringType, null, null);
        val = new StringBuffer();
    }

    @Override
    public String getString() {
        StringBuffer result = new StringBuffer(val.length());
        for (int i=0; i<val.length(); i++)
            if (val.charAt(i) < 10)
                result.setCharAt(i, (char)(val.charAt(i) + '0'));
            else
                result.setCharAt(i, (char)(val.charAt(i) + 'A' - 10));
        return "'" + result.toString() + "'H'";
    }

    @Override
    public void setString(String value) {
        val = new StringBuffer(value.length()-3);
        val.setLength(value.length()-3);
        for (int i=0; i<value.length()-3; i++) {
           char ch1 = value.charAt(i+1);
           int i1;
           if (ch1>='0' && ch1<='9') i1 = ch1-'0';
           else if (ch1>='A' && ch1<='F') i1 = ch1-'A'+10;
           else i1 = ch1-'a'+10;
           val.setCharAt(i, (char)i1);
        }
        present = true;
    }

    @Override
    public int getHex(int position) {
        return val.charAt(position);
    }

    @Override
    public void setHex(int position, int value) {
        val.setCharAt(position, (char)value);
    }

    @Override
    public int getLength() {
        return val.length();
    }

    @Override
    public void setLength(int len) {
        val.setLength(len);
    }

}
