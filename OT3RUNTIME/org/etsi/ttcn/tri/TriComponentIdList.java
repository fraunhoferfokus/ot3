// TRI IDL TriComponentIdListType
package org.etsi.ttcn.tri;
// renamed from TriComponentIdListType
/**
 * A value of type TriComponentIdList is a list of TriComponentId. This
 * abstract type is used for multicast communication in TCI.
 *
 */
public interface TriComponentIdList {
    public int size();
    public boolean isEmpty();
    public java.util.Enumeration<TriComponentId> getComponents();
    public TriComponentId get(int index);
    public void clear();
    public void add(TriComponentId comp);
}

