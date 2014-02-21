package org.etsi.ttcn.tri.rt;
import org.etsi.ttcn.tri.*;
public interface TriCommunicationTERT {
// Message based communication operations
// Ref: TRI-Definition 5.5.3.4
public void triEnqueueMsg(TriPortId tsiPortId,
TriAddress sutAddress, TriComponentId componentId,
TriMessage receivedMessage, TriTimerDuration timestamp);
// Procedure based communication operations
// Ref: TRI-Definition 5.5.4.10
public void triEnqueueCall(TriPortId tsiPortId,
TriAddress sutAddress, TriComponentId componentId,
TriSignatureId signatureId, TriParameterList parameterList, TriTimerDuration timestamp);
// Ref: TRI-Definition 5.5.4.11
public void triEnqueueReply(TriPortId tsiPortId, TriAddress sutAddress,
TriComponentId componentId, TriSignatureId signatureId,
TriParameterList parameterList, TriParameter returnValue, TriTimerDuration timestamp);
// Ref: TRI-Definition 5.5.4.12
public void triEnqueueException(TriPortId tsiPortId,
TriAddress sutAddress, TriComponentId componentId,
TriSignatureId signatureId, TriException exc, TriTimerDuration timestamp);
}
