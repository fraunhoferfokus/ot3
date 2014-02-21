// TRI IDL TriAddressType
package org.etsi.ttcn.tri;
/**
 * A value of type TriAddress indicates a source or destination address within
 * the SUT. This abstract type can be used in TRI communication operations and is
 * an open type, which is opaque to the TE.
 *
 */
public interface TriAddress {
    /**
     * 
     * @return encoded address
     */
    public byte[] getEncodedAddress();
    /**
     * Sets the encoded address of this TriAddress to address.
     * @param address encoded address
     */
    public void setEncodedAddress(byte[] address);
    /**
     * 
     * @return the amount of bits of the address.
     */
    public int getNumberOfBits();
    /**
     * 
     * @param amount the amount of bits in the address
     */
    public void setNumberOfBits(int amount);
    /**
     * Compares address with this TriAddress for equality
     * @param address to compare
     * @return true if and only if both addresses have
     * the same encoded representation, false otherwise.
     */
    public boolean equals(TriAddress address);
}

