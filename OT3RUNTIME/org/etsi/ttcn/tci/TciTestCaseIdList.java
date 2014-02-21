// TCI IDL TciTestCaseIdListType
package org.etsi.ttcn.tci;
/**
 * A value of TciTestCaseIdList Type is a list of
 * TciTestCaseId. This abstract type is used when retrieving the list of
 * test cases in a TTCN-3 module.
 *
 */
public interface TciTestCaseIdList {
    /**
     * 
     * @return the number of test cases in this list.
     */
    public int size() ;
    /**
     * 
     * @return true if this list contains no test cases.
     */
    public boolean isEmpty() ;
    /**
     * 
     * @return an Enumeration over the test cases in the list. The enumeration
     * provides the test cases in the same order as they appear in the list.
     */
    public java.util.Enumeration<TciTestCaseId> tciGetTestCases() ;
    /**
     * 
     * @param index the specified position
     * @return the TciTestCaseId at the specified position.
     */
    public TciTestCaseId get(int index) ;
}

