// TRI IDL TriFunctionIdType
package org.etsi.ttcn.tri;
/**
 * A value of type TriFunctionId is the name of an external function as
 * specified in the TTCN-3 ATS.
 *
 */
public interface TriFunctionId {
    /**
     * 
     * @return the string representation of the function as defined in TTCN-3 specification
     */
    public String toString();
    /**
     * 
     * @return the function identifier as defined in the TTCN-3 specification
     */
    public String getFunctionName();
    /**
     * Compares fun with this TriFunctionId for equality
     * @param fun to compare
     * @return Returns true if and only if both functions have the same
     * function identifier, false otherwise
     */
    public boolean equals(TriFunctionId fun);
}

