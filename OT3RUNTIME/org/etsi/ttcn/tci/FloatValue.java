// FloatValue
package org.etsi.ttcn.tci;
public interface FloatValue extends Value {
    /**
     * Sets this FloatValue to the float value value.
     * @param value to set
     */
    public void setFloat(float value);
    /**
     * 
     * @return the float value represented by this FloatValue.
     */
    public float getFloat();
}

