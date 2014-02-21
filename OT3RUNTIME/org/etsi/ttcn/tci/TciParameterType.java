// TCI IDL TciParameterTypeType
package org.etsi.ttcn.tci;
/**
 * A value of type TciParameterType Type is a structure of Type and
 * TciParameterPassingMode. This abstract type is used to represent the
 * type and the parameter passing mode of a test case parameter.
 *
 */
public interface TciParameterType {
    /**
     * 
     * @return the Type of the parameter
     */
    public Type getParameterType() ;
    /**
     * 
     * @return the parameter passing mode of this parameter.
     */
    public int getParameterPassingMode();
}

