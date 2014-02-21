// BooleanValue
package org.etsi.ttcn.tci;
public interface BooleanValue extends Value {
    /**
     * Sets this BooleanValue to the boolean value value.
     * @param value to set
     */
    public void setBoolean(boolean value);
    /**
     * 
     * @return the boolean value represented by this BooleanValue.
     */
    public boolean getBoolean();
}

