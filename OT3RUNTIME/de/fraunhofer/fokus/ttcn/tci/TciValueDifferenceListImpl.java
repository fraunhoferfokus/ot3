package de.fraunhofer.fokus.ttcn.tci;

import java.util.List;

import org.etsi.ttcn.tci.TciValueDifference;
import org.etsi.ttcn.tci.TciValueDifferenceList;

public class TciValueDifferenceListImpl implements TciValueDifferenceList {

    private List<TciValueDifference> valueList;
    
    public TciValueDifferenceListImpl(List<TciValueDifference> vallist) {
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
    public TciValueDifference get(int index) {
        return valueList.get(index);
    }

}
