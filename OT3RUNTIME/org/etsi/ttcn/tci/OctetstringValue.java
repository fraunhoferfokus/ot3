// TCI IDL OctetstringValue
package org.etsi.ttcn.tci;
public interface OctetstringValue extends Value {
    /**
     * 
     * @return the textual representation of this OctetstringValue, as defined in
     * TTCN-3. E.g. the textual representation of 0xCAFFEE is 'CAFFEE'O. The
     * textual representation of the empty TTCN-3 octetstring is ''O, while its
     * length is zero.
     */
    String getString ();
    /**
     * Sets the value of this OctetstringValue according to the textual
     * representation as defined by value. E.g. the value of this
     * OctetstringValue will be 0xCAFFEE if the textual representation in
     * value is 'CAFFEE'O.
     * @param value
     */
    void setString (String value);
    /**
     * 
     * @param position
     * @return the value (0..255) at position of this TTCN-3 octetstring. position 0
     * denotes the first octet of the TTCN-3 octetstring. Valid values for position are 0
     * to length - 1.
     */
    int getOctet (int position);
    /**
     * Set the octet at position to value (0..255). position 0 denotes the first octet in
     * the octetstring. Valid values for position are 0 to length - 1.
     * @param position
     * @param value
     */
    void setOctet (int position, int value);
    /**
     * 
     * @return the length of this OctetstringValue in octets, zero if the value of
     * this OctetstringValue is omit.
     */
    int getLength ();
    /**
     * Sets the length of this OctetstringValue in octets to len.
     * @param len
     */
    void setLength (int len);
}

