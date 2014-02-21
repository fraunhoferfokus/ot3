// TRI IDL TriExceptionType
package org.etsi.ttcn.tri;
/**
 * A value of type TriException is an encoded type and value of an exception
 * that either is to be sent to the SUT or has been received from the SUT. This
 * abstract type is used in procedure based TRI communication operations.
 *
 */
public interface TriException {
    /**
     * 
     * @return the exception encoded according to the coding rules defined in the TTCN-3 specification.
     */
    public byte[] getEncodedException();
    /**
     * Sets the encoded exception representation of this TriException to message
     * @param message to set
     */
    public void setEncodedException(byte[] message);
    /**
     * 
     * @return the amount of bits of the exception.
     */
    public int getNumberOfBits();
    /**
     * Sets the amount of bits in the exception
     * @param amount to set
     */
    public void setNumberOfBits(int amount);
    /**
     * Compares exc with this TriException for equality
     * @param exc to compare
     * @return true if and only if both exceptions have the same
     * encoded representation, false otherwise.
     */
    public boolean equals(TriException exc);
}

