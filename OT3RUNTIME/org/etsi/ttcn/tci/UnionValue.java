// TCI IDL UnionValue
package org.etsi.ttcn.tci;
/**
 * The abstract data type UnionValue is based on the abstract data type Value. It specifies how to get and set variants
 * in a TTCN-3 union type. The TTCN-3 anytype is represented by a UnionValue where the type class of the type
 * obtained by getType() is ANYTYPE.
 *
 */
public interface UnionValue extends Value {
    /**
     * 
     * @param variantName
     * @return the value of the TTCN-3 union variantName, if variantName
     * equals the result of getPresentVariantName, the distinct value null
     * otherwise. variantName denotes the name of the union variant as defined in
     * the TTCN-3 module.
     */
    Value getVariant (String variantName);
    /**
     * Sets variantName of the union to value. If variantName is not defined for
     * this union this operation will be ignored. If another variant was selected the new
     * variant will be selected instead.
     * @param variantName
     * @param value to set
     */
    void setVariant (String variantName, Value value);
    /**
     * 
     * @return the variant name that has a value in this union set as a String. The
     * distinct value null will be returned if no variant is selected.
     */
    String getPresentVariantName ();
    /**
     * 
     * @return an array of String of variant names, the empty sequence, if the union
     * has no fields. If the UnionValue represents the TTCN-3 anytype, i.e. the
     * type class of the type obtained by getType() is ANYTYPE, all predefined and
     * user-defined TTCN-3 types will be returned.
     */
    String[] getVariantNames ();
}

