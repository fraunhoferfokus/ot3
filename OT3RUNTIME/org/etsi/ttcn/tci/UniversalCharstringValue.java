// TCI IDL UniversalCharstringValue
package org.etsi.ttcn.tci;
public interface UniversalCharstringValue extends Value {
    /**
     * 
     * @return the textual representation of this UniversalCharstringValue, as
     * defined in TTCN-3.
     */
    String getString ();
    /**
     * Sets the value of this UniversalCharstringValue according to the
     * textual representation as defined by value.
     * @param value
     */
    void setString (String value);
    /**
     * 
     * @param position
     * @return the UniversalChar value of the TTCN-3 universal
     * charstring at position. position 0 denotes the first UniversalChar of the
     * TTCN-3 universal charstring. Valid values for position are 0 to
     * length - 1.
     */
    int getChar (int position);
    /**
     * Set the UniversalChar at position to value. Valid values for
     * position are 0 to length - 1.
     * @param position
     * @param value
     */
    void setChar (int position, int value);
    /**
     * 
     * @return the length of this UniversalCharstringValue in
     * UniversalChars, zero if the value of this
     * UniversalCharstringValue is omit.
     */
    int getLength ();
    /**
     * Sets the length of this UniversalCharstringValue in
     * UniversalChars to len.
     * @param len length to set
     */
    void setLength (int len);
}

