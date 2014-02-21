package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.RecordValue;
import org.etsi.ttcn.tci.Value;
import org.etsi.ttcn.tci.Type;

public class RecordValueImpl extends ValueImpl implements RecordValue {
    
    protected String[] fieldNames;
    public Value[] vals;
    
    public RecordValueImpl(Type tp) {
        super(tp, null, null);
        //fieldNames = names;
        //vals = values;
    }
    public RecordValueImpl(String[] names, Value[] values) {
        super(TypeImpl.recordType, null, null);
        fieldNames = names;
        vals = values;
    }
    
    public RecordValueImpl() {
        super(TypeImpl.recordType, null, null);
    }

    public void assignRecord(RecordValue rv)
    {
        RecordValueImpl rvi = (RecordValueImpl) rv;
        vals = rvi.vals; // prelim
    }

    @Override
    public Value getField(String fieldName) {
        for (int i=0; i<fieldNames.length; i++)
            if (fieldName.equals(fieldNames[i]))
               return vals[i];
        return null;
    }

    @Override
    public void setField(String fieldName, Value value) {
        for (int i=0; i<fieldNames.length; i++)
            if (fieldName.equals(fieldNames[i])) {
               vals[i] = value;
               present = true;
               return;
            }
    }

    @Override
    public String[] getFieldNames() {
        return fieldNames;
    }

    @Override
    public void setFieldOmitted(String fieldName) {
        for (int i=0; i<fieldNames.length; i++)
            if (fieldName.equals(fieldNames[i])) {
               vals[i] = null;
               return;
            }
    }

}
