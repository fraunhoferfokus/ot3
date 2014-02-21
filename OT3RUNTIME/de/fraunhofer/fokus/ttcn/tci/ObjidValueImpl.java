package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.ObjidValue;
import org.etsi.ttcn.tci.TciObjId;

public class ObjidValueImpl extends ValueImpl implements ObjidValue {
    
    private TciObjId val;

    public ObjidValueImpl() {
        super(TypeImpl.objidType, null, null);
    }
    
    @Override
    public TciObjId getObjid() {
        return val;
    }

    @Override
    public void setObjid(TciObjId value) {
        val = value;
        present = true;
    }

}
