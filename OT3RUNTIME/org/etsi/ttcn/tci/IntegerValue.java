// IntegerValue
package org.etsi.ttcn.tci;
public interface IntegerValue extends Value {
    /**
     * Sets this IntegerValue to the int value value.
     * @param value to set
     */
    public void setInteger(int value);
    /**
     * 
     * @return the int value represented by this IntegerValue.
     */
    public int getInteger();
}

