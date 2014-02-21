// TCI IDL TciModuleIdListType
package org.etsi.ttcn.tci;
/**
 * A value of type TciModuleIdList Type is a list of TciModuleId. This
 * abstract type is used when retrieving the list of modules which are imported by a
 * TTCN-3 module.
 *
 */
public interface TciModuleIdList {
    public int size() ;
    public boolean isEmpty() ;
    public java.util.Enumeration<TciModuleId> tciGetImportedModules() ;
    public TciModuleId get(int index) ;
}

