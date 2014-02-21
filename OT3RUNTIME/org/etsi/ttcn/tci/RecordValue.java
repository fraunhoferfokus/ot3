// TCI IDL RecordValue
package org.etsi.ttcn.tci;
public interface RecordValue extends Value {
    /**
     * 
     * @param fieldName Name of the field
     * @return the value of the field named fieldName. The return value is the
     * common abstract base type Value, as a record field can have any type defined
     * in TTCN-3.If the field cannot be obtained from the record the distinct value
     * null will be returned.
     */
    public Value getField(String fieldName) ;
    /**
     * Set the field named fieldName of the record to value. No assumption shall
     * be made on how a field is stored in a record. An internal implementation might
     * choose to store a reference to this value or to copy the value. It is safe to assume
     * that the value will be copied. Therefore it should be assumed that subsequent
     * modifications of value will not be considered in the record.
     * @param fieldName Name of the field
     * @param value to set
     */
    public void setField(String fieldName, Value value) ;
    /**
     * 
     * @return an array of String of field names, the empty sequence, if the record has
     * no fields.
     */
    public String[] getFieldNames() ;
    /**
     * Set the field named fieldName of the record to omit.
     * @param fieldName Name of the field
     */
    public void setFieldOmitted(String fieldName);
}

