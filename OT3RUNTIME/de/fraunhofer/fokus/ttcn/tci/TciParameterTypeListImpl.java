package de.fraunhofer.fokus.ttcn.tci;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.etsi.ttcn.tci.TciParameterType;
import org.etsi.ttcn.tci.TciParameterTypeList;

public class TciParameterTypeListImpl implements TciParameterTypeList {
    
    private List<TciParameterType> parTypeList;
    
    public TciParameterTypeListImpl(List<TciParameterType> moduleIdList) {
        parTypeList = moduleIdList;
    }

    @Override
    public int size() {
        return parTypeList.size();
    }

    @Override
    public boolean isEmpty() {
        return parTypeList.isEmpty();
    }

    @Override
    public Enumeration<TciParameterType> getParameterTypes() {
        return Collections.enumeration(parTypeList);
    }

    @Override
    public TciParameterType get(int index) {
        return parTypeList.get(index);
    }

}
