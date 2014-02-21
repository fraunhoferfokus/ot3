// TRI IDL TriParameterType
package org.etsi.ttcn.tri;
/**
 * A value of type TriParameter includes an encoded parameter and a value of
 * TriParameterPassingMode to represent the passing mode specified for the
 * parameter in the TTCN-3 ATS.
 *
 */
public interface TriParameter {
    /**
     * 
     * @return the parameter name as defined in the TTCN-3 specification
     */
    public String getParameterName();
    /**
     * Sets the name of this TriParameter parameter to name
     * @param name to set
     */
    public void setParameterName(String name);
    /**
     * 
     * @return the amount of bits of the parameter
     */
    public int getNumberOfBits();
    /**
     * Sets the amount of bits in the parameter
     * @param amount to set
     */
    public void setNumberOfBits(int amount);
    /**
     * 
     * @return the parameter passing mode of this parameter
     */
    public int getParameterPassingMode();
    //%%old: public void setParameterPassingMode(in mode);
    /**
     * Sets the parameter mode of this TriParameter parameter to mode.
     * @param mode to set
     */
    public void setParameterPassingMode(int mode);
    /**
     * 
     * @return the encoded parameter representation of this TriParameter, or the null object if the parameter
     * contains the distinct value null
     */
    public byte[] getEncodedParameter();
    /**
     * Sets the encoded parameter representation of this TriParameter to parameter. If the distinct value null shall
     * be set to indicate that this parameter holds no value, the Java null shall be passed as parameter
     * @param parameter to set
     */
    public void setEncodedParameter(byte[] parameter);
}

