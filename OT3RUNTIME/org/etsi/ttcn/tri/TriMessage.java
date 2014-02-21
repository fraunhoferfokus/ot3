// TRI IDL TriMessageType
package org.etsi.ttcn.tri;
/**
 * A value of type TriMessage is encoded test data that either is to be sent to
 * the SUT or has been received from the SUT. The order of bits in a value of type
 * TriMessage corresponds to the order of bits according to the encoding. If the
 * encoded message consists of the bits b0 .. b9, where b0 is the first bit, then the
 * value of type triMessage contains "b0 b1 b2 b3 b4 b5 b6 b7 b8 b9" in this
 * order. If padding bits are needed then these are added to the right.
 *
 */
public interface TriMessage {
    /**
     * 
     * @return the message encoded according the coding rules defined in the TTCN-3 specification.
     */
    public byte[] getEncodedMessage();
    /**
     * Sets the encoded message representation of this TriMessage to message
     * @param message to set
     */
    public void setEncodedMessage(byte[] message);
    /**
     * 
     * @return the amount of bits of the message.
     */
    public int getNumberOfBits();
    /**
     * Sets the amount of bits in the message.
     * @param amount to set
     */
    public void setNumberOfBits(int amount);
    /**
     * Compares message with this TriMessage for equality
     * @param message to compare
     * @return true if and only if both messages
     * have the same encoded representation, false otherwise
     */
    public boolean equals(TriMessage message);
}

