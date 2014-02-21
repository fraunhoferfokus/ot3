// TCI IDL TciValueList
package org.etsi.ttcn.tci;
public interface TciValueList{
    /**
     * 
     * @return the number of values in this list.
     */
    public int size() ;
    /**
     * 
     * @return true if this list contains no values.
     */
    public boolean isEmpty() ;
    /**
     * 
     * @param index
     * @return the Value at the specified position.
     */
    public Value get(int index) ;
}

