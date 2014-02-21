package org.etsi.ttcn.tci;
/**
 * A TciObjIdElement represent a single object element within a TTCN-3 ObjId value. It can be set using different
 * representations like the ASCII representation or as integer.
 *
 */
public interface TciObjIdElement {
    /**
     * Sets the internal representation of this ObjIdElement
     * to string value element
     * @param element
     */
    public void setElementAsAscii(String element) ;
    /**
     * Set this the internal representation of this ObjIdElement to
     * the integer value element.
     * @param element
     */
    public void setElementAsNumber(int element) ;
    /**
     * 
     * @return the internal representation of this ObjIdElement as
     * string.
     */
    public String getElementAsAscii() ;
    /**
     * 
     * @return the internal representation of this ObjIdElement as
     * integer.
     */
    public int getElementAsNumber() ;
}
