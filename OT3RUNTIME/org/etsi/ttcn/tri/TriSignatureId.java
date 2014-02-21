// TRI IDL TriSignatureIdType
package org.etsi.ttcn.tri;
/**
 * A value of type TriSignatureId is the name of a procedure signature as
 * specified in the TTCN-3 ATS. This abstract type is used in procedure based TRI
 * communication operations.
 *
 */
public interface TriSignatureId {
    /**
     * 
     * @return the signature identifier as defined in the TTCN-3 specification
     */
    public String getSignatureName();
    /**
     * Sets the signature identifier of this TriSignatureId to sigName.
     * @param sigName to set
     */
    public void setSignatureName(String sigName);
    /**
     * Compares sig with this TriSignatureId for equality
     * @param sig to compare
     * @return true if and only if both signatures have the
     * same signature identifier, false otherwise
     */
    public boolean equals(TriSignatureId sig);
}

