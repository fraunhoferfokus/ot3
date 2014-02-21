// TRI IDL TriPortIdListType
package org.etsi.ttcn.tri;
/**
 * A value of type TriPortIdList is a list of TriPortId. This abstract type is
 * used for initialization purposes after the invocation of a TTCN-3 test case.
 *
 */
public interface TriPortIdList {
    /**
     * 
     * @return the number of ports in the list
     */
    public int size();
    /**
     * 
     * @return true if the list contains no ports
     */
    public boolean isEmpty();
    /**
     * 
     * @return an Enumeration over the ports in the list. The enumeration provides the ports in the same
     * order as they appear in the list
     */
    public java.util.Enumeration<TriPortId> getPortIds();
    /**
     * 
     * @param index position of the TriPortId
     * @return the TriPortId at the specified position
     */
    public TriPortId get(int index);
}

