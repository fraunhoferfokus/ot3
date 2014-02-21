package de.fraunhofer.fokus.ttcn.tci;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.etsi.ttcn.tci.TciParameter;
import org.etsi.ttcn.tci.TciParameterList;

public class TciParameterListImpl implements TciParameterList {
    
    private List<TciParameter> parlist;
    
    public TciParameterListImpl(List<TciParameter> moduleParameterList) {
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
    public Enumeration<TciParameter> getParameters() {
        return Collections.enumeration(parlist);
    }

    @Override
    public TciParameter get(int index) {
        return parlist.get(index);
    }

    @Override
    public void clear() {
        parlist.clear();
    }

    @Override
    public void add(TciParameter parameter) {
        parlist.add(parameter);
    }

    @Override
    public void setParameters(TciParameter[] parameters) {
        parlist = Arrays.asList(parameters);
    }

}
