// TCI IDL RecordOfValue
package org.etsi.ttcn.tci;
/**
 * The abstract data type RecordOfValue is based on the abstract data type Value. It specifies how to get and set
 * elements in TTCN-3 record of types. The same abstract data type applies for value whose type class is ARRAY or
 * SET_OF. The distinction between record of, set of, and array is only relevant at matching time.
 *
 */
public interface RecordOfValue extends Value {
    /**
     * 
     * @param position position of the record
     * @return the value of the record of at position if position is between
     * zero and length - 1, the distinct value null otherwise. The return value is
     * the common abstract base type Value, as a record of can have fields of
     * any type defined in TTCN-3.
     */
    public Value getField(int position) ;
    /**
     * Sets the field at position to value. If position is greater than (
     * length - 1) the record of will be extended to have the length
     * (position + 1). The record of elements between the original position
     * at length and position - 1 will be set to OMIT. No assumption shall be
     * made on how a field is stored in a record of. An internal implementation
     * might choose to store a reference to this value or to copy the value. It is safe to
     * assume that the value will be copied. Therefore it should be assumed that
     * subsequent modifications of value will not be considered in the record of.
     * @param position position of the record
     * @param value to set
     */
    public void setField(int position, Value value) ;
    /**
     * Appends the value at the end of the record of, i.e. at position length. No
     * assumption shall be made on how a field is stored in a record of. An internal
     * implementation might choose to store a reference to this value or to copy the
     * value. It is safe to assume that the value will be copied. Therefore it should be
     * assumed that subsequent modifications of value will not be considered in the
     * record of.
     * @param value to append
     */
    public void appendField(Value value) ;
    /**
     * 
     * @return the Type of the elements of this record of.
     */
    public Type getElementType() ;
    /**
     * 
     * @return the actual length of the record of value, zero if the record of
     * value is OMIT.
     */
    public int getLength() ;
    /**
     * Set the length of the record of to len. If len is greater than the original
     * length, newly created elements have the value OMIT. If len is less or equal
     * than the original length this operation will be ignored.
     * @param len length to set
     */
    public void setLength(int len) ;
    /**
     * 
     * @return the lowest possible index. For a record of or set of value this is
     * always 0. For an array value, this is the lower index bound used in the type
     * definition.
     */
    public int getOffset() ;
}

