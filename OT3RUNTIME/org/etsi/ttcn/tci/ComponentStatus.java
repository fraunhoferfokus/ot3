// TCI IDL ComponentStatus
package org.etsi.ttcn.tci;
/**
 * The following constants shall be used for component status
 *
 */
public interface ComponentStatus {
    public static final int INACTIVE_C = 0;
    public static final int RUNNING_C = 1;
    public static final int STOPPED_C = 2;
    public static final int KILLED_C = 3;
    public static final int NULL_C = 4;
    /**
     * 
     * @return the component status as int
     */
    public int getComponentStatus() ;
    /**
     * 
     * @param componentStatus to set
     */
    public void setComponentStatus (int componentStatus) ;
}

