package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.VerdictValue;

public class VerdictValueImpl extends ValueImpl implements VerdictValue {
    
    private int verd;
    
    public VerdictValueImpl() {
        super(TypeImpl.verdictType, null, null);
    }

    @Override
    public int getVerdict() {
        return verd;
    }

    @Override
    public void setVerdict(int verdict) {
        verd = verdict;
        present = true;
    }

}
