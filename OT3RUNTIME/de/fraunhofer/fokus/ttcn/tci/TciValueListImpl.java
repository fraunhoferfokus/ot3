package de.fraunhofer.fokus.ttcn.tci;

import java.util.List;

import org.etsi.ttcn.tci.TciValueList;
import org.etsi.ttcn.tci.Value;

public class TciValueListImpl implements TciValueList {
    
    private List<Value> valueList;
    
    public TciValueListImpl(List<Value> vallist) {
        valueList = vallist;
    }

    @Override
    public int size() {
        return valueList.size();
    }

    @Override
    public boolean isEmpty() {
        return valueList.isEmpty();
    }

    @Override
    public Value get(int index) {
        return valueList.get(index);
    }

}
