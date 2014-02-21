// TRI IDL TriParameterListType
package org.etsi.ttcn.tri;
/**
 * A value of type TriParameterList is a list of TriParameter. This
 * abstract type is used in procedure based TRI communication operations and for
 * external function calls.
 *
 */
public interface TriParameterList {
    /**
     * 
     * @return the number of parameters in this list.
     */
    public int size();
    /**
     * 
     * @return true if this list contains no parameters.
     */
    public boolean isEmpty();
    /**
     * 
     * @return an Enumeration over the parameters in the list. The enumeration provides the parameters in the same
     * order as they appear in the list
     */
    public java.util.Enumeration<TriParameter> getParameters();
    /**
     * 
     * @param index position of the TriParameter
     * @return the TriParameter at the specified position
     */
    public TriParameter get(int index);
    /**
     * Removes all parameters from this TriParameterList
     */
    public void clear();
    /**
     * Adds parameter to the end of this TriParameterList
     * @param parameter
     */
    public void add(TriParameter parameter);
}

