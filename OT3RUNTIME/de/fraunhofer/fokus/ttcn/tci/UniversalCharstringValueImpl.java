package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.UniversalCharstringValue;

public class UniversalCharstringValueImpl extends ValueImpl implements
        UniversalCharstringValue {
	
	private int val[];
	private int length = 0;
    
    public UniversalCharstringValueImpl() {
        super(TypeImpl.universalCharstringType, null, null);
    }

    @Override
    public String getString() {
    	char[] stri = new char[length];
    	for (int i=0; i<length; i++)
    		stri[i] = (char)val[i];
        return new String(stri);
    }

    @Override
    public void setString(String value) {
    	setLength( value.length() );
    	for (int i=0; i<length; i++)
    		val[i] = (int)value.charAt(i);
    }

    @Override
    public int getChar(int position) {
        return val[position];
    }

    @Override
    public void setChar(int position, int value) {
    	val[position] = value;
    	present = true;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void setLength(int len) {
    	val = new int[len];
    	length = len;
    	present = true;
    }

}
