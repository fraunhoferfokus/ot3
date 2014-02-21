// TRI IDL TriTestCaseIdType
package org.etsi.ttcn.tri;
/**
 * A value of type TriTestCaseId is the name of a test case as specified in the
 * TTCN-3 ATS.
 *
 */
public interface TriTestCaseId {
    /**
     * 
     * @return the string representation of the test case as defined in TTCN-3 specification.
     */
    public String toString();
    /**
     * 
     * @return the test case identifier as defined in the TTCN-3 specification
     */
    public String getTestCaseName();
    /**
     * Compares tc with this TriTestCaseId for equality
     * @param tc to compare
     * @return true if and only if both test cases have the same
     * test case identifier, false otherwise.
     */
    public boolean equals(TriTestCaseId tc);
}

