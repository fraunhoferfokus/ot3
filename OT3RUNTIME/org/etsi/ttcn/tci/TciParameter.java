// TCI IDL TciParameterType
package org.etsi.ttcn.tci;
/**
 * A value of type TciParameter Type includes a TTCN-3 Value, which can be
 * absent, and a value of TciParameterPassingMode Type to represent the
 * parameter passing mode specified for the parameter in the TTCN-3 ATS.
 *
 */
public interface TciParameter {
    public String getParameterName();
    public void setParameterName(String name);
    public int getParameterPassingMode();
    public void setParameterPassingMode(/*TciParameterPassingMode*/int mode);
    public Value getParameter();
    public void setParameter(Value parameter);
}
