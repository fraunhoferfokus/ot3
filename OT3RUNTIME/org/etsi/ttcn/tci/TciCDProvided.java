// TCI-CD
// TE -> CD
package org.etsi.ttcn.tci;
import org.etsi.ttcn.tri.TriMessage;
/**
 * 
 * The TM shall provide this operations to the TE (codec).
 * 
 */
public interface TciCDProvided {
    /**
     * This operation decodes message according to the encoding rules and returns a TTCN-3 value. The
     * decodingHypothesis shall be used to determine whether the encoded value can be decoded. If
     * an encoding rule is not self-sufficient, i.e. if the encoded message does not inherently contain its
     * type decodingHypothesis shall be used. If the encoded value can be decoded without the
     * decoding hypothesis, the distinct null value shall be returned if the type determined from the
     * encoded message is not compatible with the decoding hypothesis.
     * This operation shall be called whenever the TE has to decode an encoded value. The TE might
     * decode immediately after reception of an encoded value, or might for performance considerations
     * postpone the decoding until the actual access of the encoded value.
     * 
     * @param message The encoded message to be decoded.
     * @param decodingHypothesis The hypothesis the decoding can be based on.
     * @return the decoded value, if the value is of a compatible type as the decodingHypothesis, else
     * the distinct value null.
     */
    public Value decode (TriMessage message, Type decodingHypothesis );
    /**
     * Returns an encoded TriMessage according to the encoding rules.
     * This operation shall be called whenever the TE has to encode a Value.
     * 
     * @param value The value to be encoded.
     * @return an encoded TriMessage for the specified encoding rule.
     */
    public TriMessage encode (Value value);
}

