package org.etsi.ttcn.tci;
public interface TciObjId {
    /**
     * 
     * @return the size of this Object Id in TciObjIdElements
     */
    public int size() ;
    /**
     * Sets this ObjId to the list of objElements
     * @param objElemens
     */
    public void setObjElement(TciObjIdElement[] objElemens) ;
    /**
     * 
     * @param index
     * @return the TciObjIdElement at position index.
     */
    public TciObjIdElement getObjElement(int index) ;
}