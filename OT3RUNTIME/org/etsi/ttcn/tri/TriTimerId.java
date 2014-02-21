// TRI IDL TriTimerIdType
package org.etsi.ttcn.tri;
/**
 * A value of type TriTimerId specifies an identifier for a timer. This abstract
 * type is required for all TRI timer operations.
 *
 */
public interface TriTimerId {
    /**
     * 
     * @return the name of this timer identifier as defined in the TTCN-3 specification. In case of implicit timers the
     * result is implementation dependent
     */
    public String getTimerName();
    /**
     * Compares timer with this TriTimerId for equality.
     * @param timer to compare
     * @return true if and only if both timers identifiers
     * represent the same timer, false otherwise
     */
    public boolean equals(TriTimerId timer);
}

