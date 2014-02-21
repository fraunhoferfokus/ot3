package de.fraunhofer.fokus.ttcn.tri;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.etsi.ttcn.tri.TriPortId;
import org.etsi.ttcn.tri.TriPortIdList;

public class TriPortIdListImpl implements TriPortIdList {
    
    private List<TriPortId> list;

    public TriPortIdListImpl(List <TriPortId> triPortIdList) {
        list = triPortIdList;
    }
    
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Enumeration<TriPortId> getPortIds() {
        return Collections.enumeration(list);
    }

    @Override
    public TriPortId get(int index) {
        return list.get(index);
    }

}
