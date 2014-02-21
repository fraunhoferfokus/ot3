// TRI IDL TriTimerDurationType
package org.etsi.ttcn.tri;
public interface TriTimerDuration {
    /**
     * 
     * @return the duration of a timer as double
     */
    public double getDuration();
    /**
     * Sets the duration of this TriTimerDuration to duration
     * @param duration to set
     */
    public void setDuration(double duration);
    /**
     * Compares duration with this TriTimerDuration for equality.
     * @param duration to compare
     * @return true if and only if both have the
     * same duration, false otherwise.
     */
    public boolean equals(TriTimerDuration duration);
}

