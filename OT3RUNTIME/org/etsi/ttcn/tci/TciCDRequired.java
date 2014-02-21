// TCI-CD
// CD -> TE
package org.etsi.ttcn.tci;
/**
 * The interface TciCDRequired provides methods that return instances of the classes described above. It also provides an error message method. 
 *
 */
public interface TciCDRequired {
    /**
     * Returns a type representing a TTCN-3 type. Predefined TTCN-3 types can be retrieved from the TE
     * by using the TTCN-3 keywords for the predefined types. In this case typeName denotes to the
     * basic TTCN-3 type like "charstring", "bitstring", etc.
     * Returns the distinct value null if the requested type cannot be returned. Note that the anytype
     * and address cannot be obtained with module set to null. Although they are predefined types
     * they might be distinct between modules. For example, address can either be the unmodified
     * predefined type, or a user-defined type in a module. Other predefined types cannot be redefined.
     * @param typeName The TTCN-3 name of the type as defined in the TTCN-3 module. The following
     * are reserved type names and will return a predefined type:
     * "integer"
     * "float"
     * "bitstring"
     * "hexstring"
     * "octetstring"
     * "charstring"
     * "universal charstring"
     * "boolean"
     * "verdicttype"
     * typeName has to be the fully qualified type name, i.e. module.typeName
     * @return A type representing the requested TTCN-3 type.
     */
    public Type getTypeForName (String typeName);
    /**
     * Constructs and returns a basic TTCN-3 integer type.
     * @return An instance of Type representing a TTCN-3 integer type.
     */
    public Type getInteger ();
    /**
     * Constructs and returns a basic TTCN-3 float type.
     * @return An instance of Type representing a TTCN-3 float type.
     */
    public Type getFloat ();
    /**
     * Constructs and returns a basic TTCN-3 boolean type.
     * @return An instance of Type representing a TTCN-3 boolean type.
     */
    public Type getBoolean ();
    /**
     * Constructs and returns a basic TTCN-3 charstringtype.
     * @return An instance of Type representing a TTCN-3 charstring type.
     */
    public Type getCharstring ();
    /**
     * Constructs and returns a basic TTCN-3 universal charstring type.
     * @return An instance of Type representing a TTCN-3 universal charstring type.
     */
    public Type getUniversalCharstring ();
    /**
     * Constructs and returns a basic TTCN-3 hexstring type.
     * @return An instance of Type representing a TTCN-3 hexstring type.
     */
    public Type getHexstring ();
    /**
     * Constructs and returns a basic TTCN-3 bitstring type.
     * @return An instance of Type representing a TTCN-3 bitstring type.
     */
    public Type getBitstring ();
    /**
     * Constructs and returns a basic TTCN-3 octetstring type.
     * @return An instance of Type representing a TTCN-3 octetstring type.
     */
    public Type getOctetstring ();
    /**
     * Constructs and returns a basic TTCN-3 verdict type.
     * @return An instance of Type representing a TTCN-3 verdict type.
     */
    public Type getVerdict ();
    /**
     * The TE will be notified about an unrecoverable error situation within the CD and forward the
     * error indication to the test management.
     * Shall be called whenever an error situation has occurred.
     * @param message A string value, i.e. the error phrase describing the problem.
     */
    public void tciErrorReq (String message);
}

