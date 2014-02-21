// TriCommunication
// TE -> SA
package org.etsi.ttcn.xtri;

import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;
public interface xTriCommunicationSA {
    public TriStatus xtriMapParam(TriPortId compPortId, TriPortId tsiPortId,
            /*in*/ TciParameterList/*Type*/ paramList);
    // Ref: TRI-Definition 5.5.2.3
    public TriStatus xtriUnmapParam(TriPortId compPortId, TriPortId tsiPortId,
            /*in*/ TciParameterList/*Type*/ paramList);
    // Ref: TRI-Definition 5.5.2.4
    // Message based communication operations
    // Ref: TRI-Definition 5.5.3.1
    public TriStatus xtriSend(TriComponentId componentId, TriPortId tsiPortId,
            Value sutAddress, Value sendMessage);
    // Ref: TRI-Definition 5.5.3.2
    public TriStatus xtriSendBC(TriComponentId componentId, TriPortId tsiPortId,
            Value sendMessage);
    // Ref: TRI-Definition 5.5.3.3
    public TriStatus xtriSendMC(TriComponentId componentId, TriPortId tsiPortId,
            TciValueList sutAddresses, Value sendMessage);
    // Procedure based communication operations
    // Ref: TRI-Definition 5.5.4.1
    public TriStatus xtriCall(TriComponentId componentId,
            TriPortId tsiPortId, Value sutAddress,
            TriSignatureId signatureId, TciParameterList parameterList);
    // Ref: TRI-Definition 5.5.4.2
    public TriStatus xtriCallBC(TriComponentId componentId,
            TriPortId tsiPortId,
            TriSignatureId signatureId, TciParameterList parameterList);
    // Ref: TRI-Definition 5.5.4.3
    public TriStatus xtriCallMC(TriComponentId componentId,
            TriPortId tsiPortId, TciValueList sutAddresses,
            TriSignatureId signatureId, TciParameterList parameterList);
    // Ref: TRI-Definition 5.5.4.4
    public TriStatus xtriReply(TriComponentId componentId,
            TriPortId tsiPortId, Value sutAddress,
            TriSignatureId signatureId, TciParameterList parameterList,
            Value returnValue);
    // Ref: TRI-Definition 5.5.4.5
    public TriStatus xtriReplyBC(TriComponentId componentId,
            TriPortId tsiPortId,
            TriSignatureId signatureId, TciParameterList parameterList,
            Value returnValue);
    // Ref: TRI-Definition 5.5.4.6
    public TriStatus xtriReplyMC(TriComponentId componentId,
            TriPortId tsiPortId, TciValueList sutAddresses,
            TriSignatureId signatureId, TciParameterList parameterList,
            Value returnValue);
    // Ref: TRI-Definition 5.5.4.7
    public TriStatus xtriRaise(TriComponentId componentId, TriPortId tsitPortId,
            Value sutAddress,
            TriSignatureId signatureId,
            Value exc);
    // Ref: TRI-Definition 5.5.4.8
    public TriStatus xtriRaiseBC(TriComponentId componentId,
            TriPortId tsitPortId,
            TriSignatureId signatureId,
            Value exc);
    // Ref: TRI-Definition 5.5.4.9
    // ETSI
    // 21 ETSI ES 202 789 V1.1.1 (2012-04)
    public TriStatus xtriRaiseMC(TriComponentId componentId, TriPortId tsitPortId,
            TciValueList sutAddresses,
            TriSignatureId signatureId,
            Value exc);
}