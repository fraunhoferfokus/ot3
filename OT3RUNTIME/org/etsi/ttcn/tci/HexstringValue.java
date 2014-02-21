// TCI IDL HexstringValue
package org.etsi.ttcn.tci;
public interface HexstringValue extends Value {
    /**
     * 
     * @return the textual representation of this HextstringValue, as defined in
     * TTCN-3. E.g. the textual representation of 0xAFFEE is 'AFFEE'H. The
     * textual representation of the empty TTCN-3 hexstring is ''H, while its
     * length is zero.
     */
    String getString ();
    /**
     * Sets the value of this HexstringValue according to the textual
     * representation as defined by value. E.g. the value of this HexstringValue
     * will be 0xAFFEE if the textual representation in value is 'AFFEE'H.
     * @param value to set
     */
    void setString (String value);
    /**
     * 
     * @param position The position of the hex digit
     * @return the value (0...15) at position of this TTCN-3 hexstring. position
     * 0 denotes the first hex digits of the TTCN-3 hexstring. Valid values for position
     * are 0 to length - 1.
     */
    int getHex (int position);
    /**
     * Set the hex digit at position to value (0...16). position 0 denotes the first
     * octet in the hexstring. Valid values for position are 0 to length - 1.
     * @param position The position of the hex digit
     * @param value hex digit to set
     */
    void setHex (int position, int value);
    /**
     * 
     * @return the length of this HexstringValue in octets, zero if the value of this
     * HexstringValue is omit.
     */
    int getLength ();
    /**
     * Sets the length of this HexstringValue in hex digits to len.
     * @param len to set
     */
    void setLength (int len);
}

