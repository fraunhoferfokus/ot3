package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.OctetstringValue;

public class OctetstringValueImpl extends ValueImpl implements OctetstringValue {
    
    private StringBuffer val;
    
    public OctetstringValueImpl() {
        super(TypeImpl.octetstringType, null, null);
        val = new StringBuffer();
    }

    @Override
    public String getString() {
        StringBuffer result = new StringBuffer(2*val.length());
        for (int i=0; i<val.length(); i++) {
            result.setCharAt(i+i, (char)((((int)val.charAt(i))/16)+'0'));
            result.setCharAt(i+i+1, (char)((val.charAt(i)&15)+'0'));
            if (result.charAt(i+i)>'9') result.setCharAt(i+i,(char)(result.charAt(i+i)+'A'-10));
            if (result.charAt(i+i+1)>'9') result.setCharAt(i+i+1,(char)(result.charAt(i+i)+'A'-10));
         }
         return "'"+result.toString()+"'O";
    }

    @Override
    public void setString(String value) {
        val.setLength((value.length()-3)/2);
        for (int i=0; i<val.length(); i++) {
           char ch1 = value.charAt(i+i+1);
           char ch2 = value.charAt(i+i+2);
           int i1, i2;
           if (ch1>='0' && ch1<='9') i1 = ch1-'0';
           else if (ch1>='A' && ch1<='F') i1 = ch1-'A'+10;
           else i1 = ch1-'a'+10;
           if (ch2>='0' && ch2<='9') i2 = ch2-'0';
           else if (ch2>='A' && ch2<='F') i2 = ch2-'A'+10;
           else i2 = ch2-'a'+10;
           val.setCharAt(i, (char)(i1*16+i2));
        }
        present = true;
    }

    @Override
    public int getOctet(int position) {
        return val.charAt(position);
    }

    @Override
    public void setOctet(int position, int value) {
        val.setCharAt(position, (char)value);
        present = true;
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
