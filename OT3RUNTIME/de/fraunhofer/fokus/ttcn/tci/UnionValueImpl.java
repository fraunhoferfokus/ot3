package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.UnionValue;
import org.etsi.ttcn.tci.Value;

public class UnionValueImpl extends ValueImpl implements UnionValue {
    
    protected String[] fieldNames;
    protected int tag = -1;
    protected Value val;
    
    public UnionValueImpl() {
        super(TypeImpl.unionType, null, null);
    }
    
    public UnionValueImpl(String[] names, int t, Value value) {
        super(TypeImpl.unionType, null, null);
        fieldNames = names;
        tag = t;
        val = value;
    }

    @Override
    public Value getVariant(String variantName) {
        for (int i=0; i<fieldNames.length; i++)
            if (variantName==fieldNames[i])
               if (i==tag) return val;
               else return null;
        return null;
    }

    @Override
    public void setVariant(String variantName, Value value) {
        for (int i=0; i<fieldNames.length; i++)
            if (variantName.equals(fieldNames[i])) {
               tag = i;
               val = value;
               present = true;
               return;
            }
    }

    @Override
    public String getPresentVariantName() {
        if (tag>=0) return fieldNames[tag];
        else return null;
    }

    @Override
    public String[] getVariantNames() {
        return fieldNames;
    }

}
