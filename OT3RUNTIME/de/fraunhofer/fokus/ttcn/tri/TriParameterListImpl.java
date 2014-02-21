package de.fraunhofer.fokus.ttcn.tri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.etsi.ttcn.tri.TriParameter;
import org.etsi.ttcn.tri.TriParameterList;

public class TriParameterListImpl implements TriParameterList {
    
    private List<TriParameter> list = new ArrayList<TriParameter>();
    
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Enumeration<TriParameter> getParameters() {
        return Collections.enumeration(list);
    }

    @Override
    public TriParameter get(int index) {
        return list.get(index);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void add(TriParameter parameter) {
        list.add(parameter);
    }

}
