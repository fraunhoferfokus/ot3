package de.fraunhofer.fokus.ttcn.tri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.etsi.ttcn.tri.TriComponentId;
import org.etsi.ttcn.tri.TriComponentIdList;

public class TriComponentIdListImpl implements TriComponentIdList {
    
    private List<TriComponentId> list = new ArrayList<TriComponentId>();
    
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Enumeration<TriComponentId> getComponents() {
        return Collections.enumeration(list);
    }

    @Override
    public TriComponentId get(int index) {
        return list.get(index);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void add(TriComponentId comp) {
        list.add(comp);
    }

}
