// TCI IDL TciValueDifferenceList
package org.etsi.ttcn.tci;
/**
 * A value of TciValueDifferenceList is a sequence of value
 * differences.
 *
 */
public interface TciValueDifferenceList{
    /**
     * 
     * @return the number of values in the list.
     */
    public int size() ;
    /**
     * 
     * @return true if the list contains no values.
     */
    public boolean isEmpty() ;
    /**
     * 
     * @param index the specified position
     * @return the TciValueDifference at the specified position.
     */
    public TciValueDifference get(int index) ;
}

