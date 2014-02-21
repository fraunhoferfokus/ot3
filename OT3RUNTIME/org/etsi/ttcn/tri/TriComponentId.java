// TRI IDL TriComponentIdType
package org.etsi.ttcn.tri;
/**
 * A value of type TriComponentId includes an identifier, a name and the component
 * type. The distinct value of the latter is the component type name as specified in the
 * TTCN-3 ATS. This abstract type is mainly used to resolve TRI communication
 * operations on TSI ports that have mappings to many test component ports. It is also
 * used to resolve TCI component handling.
 * 
 */
public interface TriComponentId {
    public String getComponentId();
    public String getComponentName();
    public String getComponentTypeName();
    public TriPortIdList getPortList();
    public boolean equals(TriComponentId component);
}

