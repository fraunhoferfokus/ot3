// TCI IDL Type
//%% Additional interface for extension ES 202 785 Behaviour Types
package org.etsi.ttcn.tci;
/**
 * New interface of TTCN-3 Language Extensions: Behaviour Types
 *
 */
public interface BehaviourValue {
    /**
     * 
     * @return the name of the behaviour.
     */
    public String getName ();
    /**
     * 
     * @return the module identifier of the module in which the behaviour is
     * defined. Returns the distinct value null if the behaviour is a predefined
     * function.
     */
    public TciModuleId getDefiningModule ();
}