// TRI IDL TriAddressListType
package org.etsi.ttcn.tri;
// renamed from TriAddressListType
/**
 * A value of type TriAddressList is a list of TriAddress. This abstract
 * type is used for multicast communication in TRI.
 *
 */
public interface TriAddressList {
    /**
     * 
     * @return the number of components in this list.
     */
    public int size();
    /**
     * 
     * @return true if this list contains no components
     */
    public boolean isEmpty();
    /**
     * 
     * @return an Enumeration over the components in the list. The enumeration provides the addresses in the same
     * order as they appear in the list
     */
    public java.util.Enumeration<TriAddress> getAddresses();
    /**
     * 
     * @param index
     * @return the TriAddress at the specified position
     */
    public TriAddress get(int index);
    /**
     * Removes all addresses from this TriAddressList
     */
    public void clear();
    /**
     * Adds addr to the end of this TriAddressList
     * @param addr
     */
    public void add(TriAddress addr);
}

