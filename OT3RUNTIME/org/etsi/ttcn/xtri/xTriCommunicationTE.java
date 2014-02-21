// TriCommunication
// SA -> TE
package org.etsi.ttcn.xtri;

import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

public interface xTriCommunicationTE {
    // Message based communication operations
    // Ref: TRI-Definition 5.5.3.4
    public void xtriEnqueueMsg(TriPortId tsiPortId,
            Value sutAddress, TriComponentId componentId,
            Object receivedMessage);
    // Procedure based communication operations
    // Ref: TRI-Definition 5.5.4.10
    public void xtriEnqueueCall(TriPortId tsiPortId,
            Object sutAddress, TriComponentId componentId,
            TriSignatureId signatureId, TciParameterList parameterList );
    // Ref: TRI-Definition 5.5.4.11
    public void xtriEnqueueReply(TriPortId tsiPortId, Object sutAddress,
            TriComponentId componentId, TriSignatureId signatureId,
            TciParameterList parameterList, Value returnValue);
    // Ref: TRI-Definition 5.5.4.12
    public void xtriEnqueueException(TriPortId tsiPortId,
            Object sutAddress, TriComponentId componentId,
            TriSignatureId signatureId, Object exc);
    // Miscellaneous operations
    // Ref: TRI-Definition 5.5.3.5
    public Value xtriConvert(/*in*/ Object value, /*in*/ Type typeHypothesis);
}