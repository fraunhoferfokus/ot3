package de.fraunhofer.fokus.ttcn.tci;

import java.util.ArrayList;
import org.etsi.ttcn.tci.RecordOfValue;
import org.etsi.ttcn.tci.Type;
import org.etsi.ttcn.tci.Value;

public class RecordOfValueImpl extends ValueImpl implements RecordOfValue, Cloneable {
	
	protected ArrayList<Value> vals;
	protected Type elementType;
	
	public RecordOfValueImpl(ArrayList<Value> values, Type elemType) {
		super(TypeImpl.recordOfType, null, null);
		vals = values; elementType = elemType;
	}
    
    public RecordOfValueImpl() {
        super(TypeImpl.recordOfType, null, null);
    }
    
    public Object clone() {
    	try { return super.clone(); } catch (CloneNotSupportedException e) {}
    	return null;
    } 

    @Override
    public Value getField(int position) {
    	if (position>=0 && position<vals.size())
    		return vals.get(position);
    	return null;
    }

    @Override
    public void setField(int position, Value value) {
    	vals.set(position, value);
    	present = true;
    }

    @Override
    public void appendField(Value value) {
    	vals.add(value);
        present = true;
    }

    @Override
    public Type getElementType() {
        return elementType;
    }

    @Override
    public int getLength() {
        return vals.size();
    }

    @Override
    public void setLength(int len) {
    	int oldlen = getLength();
    	Value omit = elementType.newInstance();
    	if (len>oldlen) {
    		for (int i=len-1; i>=oldlen; i--)
    			setField(i, omit);
    	}
    }

    @Override
    public int getOffset() {
        return 0;
    }

}
