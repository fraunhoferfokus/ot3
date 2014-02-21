package de.fraunhofer.fokus.ttcn.tri;

import java.util.Collections;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;

import org.etsi.ttcn.tri.TriAddress;
import org.etsi.ttcn.tri.TriAddressList;

public class TriAddressListImpl implements TriAddressList {
    
    private List<TriAddress> list = new ArrayList<TriAddress>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Enumeration<TriAddress> getAddresses() {
        return Collections.enumeration(list);
    }

    @Override
    public TriAddress get(int index) {
        return list.get(index);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void add(TriAddress addr) {
        list.add(addr);
    }

}
