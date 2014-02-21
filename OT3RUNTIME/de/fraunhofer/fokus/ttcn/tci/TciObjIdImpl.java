package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciObjId;
import org.etsi.ttcn.tci.TciObjIdElement;


public class TciObjIdImpl implements TciObjId {
    
    private TciObjIdElement[] elements;

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public void setObjElement(TciObjIdElement[] objElemens) {
        elements = objElemens;
    }

    @Override
    public TciObjIdElement getObjElement(int index) {
        return elements[index];
    }

}
