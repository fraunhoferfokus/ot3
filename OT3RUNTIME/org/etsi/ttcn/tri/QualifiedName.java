// added to Tri interface
package org.etsi.ttcn.tri;
/**
 * This interface defines a TTCN-3 identifier: moduleName + objectName
 *
 */
public interface QualifiedName {
    /**
     * 
     * @return the module name as String
     */
    public String getModuleName();
    /**
     * 
     * @return the base name as String
     */
    public String getBaseName();
    /**
     * Compares a qualified name with this QualifiedName for equality
     * @param name
     * @return true if and only if the
     * module and base name of both instances are equal, false otherwise.
     */
    public boolean equals(QualifiedName name);
}
