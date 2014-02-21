// TCI IDL BitstringValue
package org.etsi.ttcn.tci;
public interface BitstringValue extends Value {
    /**
     * 
     * @return the textual representation of this BitstringValue, as defined in
     * TTCN-3. E.g. the textual representation of 0101 is '0101'B. The textual
     * representation of the empty TTCN-3 bitstring is ''B, while its length is
     * zero.
     */
    String getString ();
    /**
     * Sets the value of this BitstringValue according to the textual
     * representation as defined by value. E.g. The value of this BitstringValue
     * will be 0101 if the textual representation in value is '0101'B.
     * @param value
     */
    void setString (String value);
    /**
     *
     * @param position The position of the bit
     * @return the value (0 | 1) at position of this TTCN-3 bitstring. position 0
     * denotes the first bit of the TTCN-3 bitstring. Valid values for position are 0 to
     * length - 1.
     */
    int getBit (int position);
    /**
     * Set the bit at position to value (0 | 1). position 0 denotes the first bit in this
     * BitstringValue. Valid values for position are 0 to length - 1.
     * @param position The position of the bit
     * @param value to set
     */
    void setBit (int position, int value);
    /**
     * 
     * @return the length of this BitstringValue in bits, zero if the value of this
     * BitstringValue is omit.
     */
    int getLength ();
    /**
     * Sets the length of this BitstringValue in bits to len.
     * @param len to set
     */
    void setLength (int len);
}

