// TCI IDL CharstringValue
package org.etsi.ttcn.tci;
public interface CharstringValue extends Value {
    /**
     * 
     * @return the string of the TTCN-3 charstring. The textual representation of
     * the empty TTCN-3 charstring is "", while its length is zero.
     */
    String getString ();
    /**
     * Sets this CharstringValue to value.
     * @param value to set
     */
    void setString (String value);
    /**
     * 
     * @param position The position of the characte
     * @return the char value of the TTCN-3 charstring at position. position 0
     * denotes the first char of the TTCN-3 charstring. Valid values for position
     * are 0 to length - 1.
     */
    char getChar (int position);
    /**
     * Set the char at position to value. Valid values for position are 0 to
     * length - 1.
     * @param position The position of the character
     * @param value to set
     */
    void setChar (int position, char value);
    /**
     * 
     * @return the length of this CharstringValue in chars, zero if the value of this
     * CharstringValue is omit.
     */
    int getLength ();
    /**
     * Sets the length of this CharstringValue in chars to len.
     * @param len to set
     */
    void setLength (int len);
}

