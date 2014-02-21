// TriCommunication
// SA -> TE
package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.*;
public class TriCommunicationTEImpl implements TriCommunicationTE {
    // Message based communication operations
    // Ref: TRI-Definition 5.5.3.4
    public void triEnqueueMsg(TriPortId tsiPortId,
            TriAddress sutAddress, TriComponentId componentId,
            TriMessage receivedMessage) {}
    // Procedure based communication operations
    // Ref: TRI-Definition 5.5.4.10
    public void triEnqueueCall(TriPortId tsiPortId,
            TriAddress sutAddress, TriComponentId componentId,
            TriSignatureId signatureId, TriParameterList parameterList ) {}
    // Ref: TRI-Definition 5.5.4.11
    public void triEnqueueReply(TriPortId tsiPortId, TriAddress sutAddress,
            TriComponentId componentId, TriSignatureId signatureId,
            TriParameterList parameterList, TriParameter returnValue) {}
    // Ref: TRI-Definition 5.5.4.12
    public void triEnqueueException(TriPortId tsiPortId,
            TriAddress sutAddress, TriComponentId componentId,
            TriSignatureId signatureId, TriException exc) {}
}

