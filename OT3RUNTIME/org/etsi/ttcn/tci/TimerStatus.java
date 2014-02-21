// TCI IDL TimerStatus
package org.etsi.ttcn.tci;
/**
 * The following constants shall be used for timer status
 *
 */
public interface TimerStatus {
    public static final int RUNNING_T = 0;
    public static final int INACTIVE_T = 1;
    public static final int EXPIRED_T = 2;
    public static final int NULL_T = 3;
    /**
     * 
     * @return TimerStatus as int
     */
    public int getTimerStatus() ;
    /**
     * 
     * @param timerStatus to set
     */
    public void setTimerStatus (int timerStatus) ;
}

