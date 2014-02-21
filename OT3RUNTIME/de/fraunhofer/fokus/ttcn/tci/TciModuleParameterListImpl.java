package de.fraunhofer.fokus.ttcn.tci;

import java.util.List;
import java.util.Collections;
import java.util.Enumeration;

import org.etsi.ttcn.tci.TciModuleParameter;
import org.etsi.ttcn.tci.TciModuleParameterList;

public class TciModuleParameterListImpl implements TciModuleParameterList {
    
    private List<TciModuleParameter> parlist;
    
    public TciModuleParameterListImpl(List<TciModuleParameter> moduleParameterList) {
        parlist = moduleParameterList;
    }

    @Override
    public int size() {
        return parlist.size();
    }

    @Override
    public boolean isEmpty() {
        return parlist.isEmpty();
    }

    @Override
    public Enumeration<TciModuleParameter> getModuleParameters() {
        return Collections.enumeration(parlist);
    }

    @Override
    public TciModuleParameter get(int index) {
        return parlist.get(index);
    }

}
