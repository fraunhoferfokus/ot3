// TCI IDL TciStatus
package org.etsi.ttcn.tci;
/**
 * The following constants shall be used for TCI status
 *
 */
public interface TciStatus {
    public static final int TCI_OK = 0;
    public static final int TCI_ERROR = -1;
    /**
     * 
     * @return TciStatus as int
     */
    public int getTciStatus() ;
    /**
     * 
     * @param tciStatus to set
     */
    public void setTciStatus (int tciStatus) ;
}

