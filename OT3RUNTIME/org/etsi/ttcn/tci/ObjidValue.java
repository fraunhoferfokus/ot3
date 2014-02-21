// TCI IDL ObjidValue
package org.etsi.ttcn.tci;
public interface ObjidValue {
    /**
     * 
     * @return the object id value of the TTCN-3 objid
     */
    TciObjId getObjid ();
    /**
     * Sets this ObjidValue to value
     * @param value to set
     */
    void setObjid (TciObjId value);
}
