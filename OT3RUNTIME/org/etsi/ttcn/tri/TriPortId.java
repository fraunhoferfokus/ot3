// TRI IDL TriPortIdType
package org.etsi.ttcn.tri;
/**
 * 
 * A value of type TriPortId includes a value of type TriComponentId to
 * represent the component to which the port belongs, a port index (if present), and the
 * port and port type name as specified in the TTCN-3 ATS. The TriPortIdType type is
 * mainly required to pass information about the TSI and connections to the TSI from the
 * TE to the SA.
 * 
 */
public interface TriPortId {
    /**
     * 
     * @return the port name as defined in the TTCN-3 specification
     */
    public String getPortName();
    /**
     * 
     * @return the port type name as defined in the TTCN-3 specification
     */
    public String getPortTypeName();
    /**
     * 
     * @return the component identifier that this TriPortId belongs to as defined in the TTCN-3 specification
     */
    public TriComponentId getComponent();
    /**
     * 
     * @return true if this port is part of a port array, false otherwise
     */
    public boolean isArray();
    /**
     * 
     * @return the port index if this port is part of a port array starting at zero. If the port is not part of a port array,
     * then -1 is returned
     */
    public int getPortIndex();
}

