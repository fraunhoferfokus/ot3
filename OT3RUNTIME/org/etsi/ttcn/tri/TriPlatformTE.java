// TriPlatform
// PA -> TE
package org.etsi.ttcn.tri;
/**
 * Platform interface operations PA -> TE
 *
 */
public interface TriPlatformTE {
    // Ref: TRI-Definition 5.6.2.5
    /**
     * This operation is called by the PA after a timer, which has previously been started using the
     * triStartTimer operation, has expired, i.e. it has reached its maximum duration value.
     * The timeout with the timerId can be added to the timeout list in the TE. The implementation of this
     * operation in the TE has to be done in such a manner that it addresses the different TTCN-3
     * semantics for timers defined in ES 201 873-4 [3].
     * 
     * @param timerId identifier of the timer instance
     */
    public void triTimeout(TriTimerId timerId);
}

