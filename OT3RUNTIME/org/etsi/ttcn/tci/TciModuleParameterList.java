// TCI IDL TciModuleParameterListType
package org.etsi.ttcn.tci;
/**
 * A value of type TciModuleParameterList Type is a list of
 * TciModuleParameter. This abstract type is used when retrieving the
 * module parameters of a TTCN-3 module.
 *
 */
public interface TciModuleParameterList {
    public int size() ;
    public boolean isEmpty() ;
    public java.util.Enumeration<TciModuleParameter> getModuleParameters() ;
    public TciModuleParameter get(int index) ;
}

