// TCI IDL TciParameterTypeListType
package org.etsi.ttcn.tci;
/**
 * A value of type TciParameterTypeList Type is a list of
 * TciParameterType. This abstract type is used to represent the list of
 * parameters of a test case.
 *
 */
public interface TciParameterTypeList {
    public int size() ;
    public boolean isEmpty() ;
    public java.util.Enumeration<TciParameterType> getParameterTypes() ;
    public TciParameterType get(int index) ;
}

