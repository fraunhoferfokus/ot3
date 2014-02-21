// TCI IDL EnumeratedValue
package org.etsi.ttcn.tci;
public interface EnumeratedValue extends Value {
    /**
     * 
     * @return the string identifier of this EnumeratedValue. This identifier equals
     * the identifier in the TTCN-3 specification.
     */
    String getEnum ();
    /**
     * Set the enum to enumValue. If enumValue is not an allowed value for this
     * enumeration the operation will be ignored.
     * @param enumValue to set
     */
    void setEnum (String enumValue);
    /**
     * 
     * @return the integer value of this EnumeratedValue. This integer equals the
     * user-assigned integer value in the TTCN-3 specification or the automatically
     * assigned integer value.
     */
    int getInt();
    /**
     * Sets the integer value of this EnumeratedValue. This integer should equal
     * the user-assigned integer value in the TTCN-3 specification or the automatically
     * assigned integer value. If intValue is not an allowed value for this
     * enumeration the operation is ignored.
     * @param intValue to set
     */
    void setInt(int intValue);
}

