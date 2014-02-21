// TCI IDL TciBehaviourIdType
package org.etsi.ttcn.tci;
import org.etsi.ttcn.tri.*;
/**
 * A value of type TciBehaviourId Type identifies a TTCN-3 behaviour
 * functions.
 *
 */
public interface TciBehaviourId extends QualifiedName {
    //%% Extension ES 202 784 Advanced Parameterization
    /**
     * 
     * @return the list of the actual type parameters of this behaviour
     */
    public TciParameterTypeList getTypeParameters();
}

