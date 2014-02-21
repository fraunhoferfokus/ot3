// TriStatusType
package org.etsi.ttcn.tri;
/**
 * 
 * A value of type TriStatus is either TRI_OK or TRI_Error indicating the
 * success or failure of a TRI operation.
 */
public interface TriStatus {
    public final static int TRI_OK = 0;
    public final static int TRI_ERROR = -1;
    /**
     * 
     * @return string representation of the status
     */
    public String toString();
    /**
     * 
     * @return status of TriStatus
     */
    public int getStatus();
    /**
     * 
     * @param status set the status
     */
    public void setStatus(int status);
    /**
     * Compares status with this TriStatus for equality.
     * @param status to compare
     * @return true if and only if they have the same status,
     * false otherwise.
     */
    public boolean equals(TriStatus status);
}

