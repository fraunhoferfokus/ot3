// TCI IDL TciParameterListType
package org.etsi.ttcn.tci;
/**
 * A value of type TciParameterList Type is a list of TciParameter.
 * This abstract type is used when starting a test case or when the termination of a test
 * case is indicated.
 *
 */
public interface TciParameterList {
    public int size() ;
    public boolean isEmpty() ;
    public java.util.Enumeration<TciParameter> getParameters() ;
    public TciParameter get(int index) ;
    public void clear() ;
    public void add(TciParameter parameter) ;
    public void setParameters(TciParameter[] parameters) ;
}

