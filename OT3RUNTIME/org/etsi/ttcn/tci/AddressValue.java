package org.etsi.ttcn.tci;
public interface AddressValue extends Value {
    /**
     * 
     * @return the value represented by this AddressValue.
     */
    public Value getAddress() ;
    /**
     * Sets this AddressValue to the value value.
     * @param value to set
     */
    public void setAddress(Value value) ;
}
