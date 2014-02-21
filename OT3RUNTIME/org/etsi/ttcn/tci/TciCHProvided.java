// TciCHProvided
// TE -> CH
package org.etsi.ttcn.tci;
import org.etsi.ttcn.tri.*;
/**
 * This interface specifies the operations the CH shall provide to the TE.
 *
 */
public interface TciCHProvided {
    /**
     * Sends an asynchronous transmission only to the given receiver component. CH transmits the
     * message to the remote TE on which receiver is being executed and enqueues the data in the
     * remote TE.
     * This operation shall be called by the TE when it executes a TTCN-3 unicast send operation on a
     * component port, which has been connected to another component port.
     * @param sender Port identifier at the sending component via which the message is sent.
     * @param receiver Identifier of the receiving component.
     * @param sendMessage The message to be sent.
     */
    public void tciSendConnected (TriPortId sender, TriComponentId receiver, Value sendMessage) ;
    /**
     * Sends an asynchronous transmission to all components being connected to this port. CH transmits
     * the message to all remote TEs on which receivers are being executed and enqueues the data in
     * the remote TEs.
     * This operation shall be called by the TE when it executes a TTCN-3 broadcast send operation on a
     * component port, which has been connected to other component ports.
     * @param sender Port identifier at the sending component via which the message is sent.
     * @param sendMessage The message to be sent.
     */
    public void tciSendConnectedBC (TriPortId sender, Value sendMessage) ;
    /**
     * Sends an asynchronous transmission to all given receiver components. CH transmits the message
     * to all remote TEs on which receivers are being executed and enqueues the data in the remote TEs.
     * This operation shall be called by the TE when it executes a TTCN-3 multicast send operation on a
component port, which has been connected to other component ports.
     * @param sender Port identifier at the sending component via which the message is sent.
     * @param receivers Identifiers of the receiving components.
     * @param sendMessage The message to be sent.
     */
    public void tciSendConnectedMC (TriPortId sender, TriComponentIdList receivers,
            Value sendMessage) ;
    /**
     * On invocation of this operation the TE can initiate the procedure call corresponding to the signature
     * identifier signature at the called component receiver. The tciCallConnected operation shall
     * return without waiting for the return of the issued procedure call. Note that an optional timeout
     * value, which can be specified in the TTCN-3 ATS for a call operation, is not included in the
     * tciCallConnected operation signature. The TE is responsible to address this issue by starting
     * a timer for the TTCN-3 call operation in the PA with a separate TRI operation call, i.e.
     * triStartTimer. CH transmits the call to the remote TE on which receiver is being executed
     * and enqueues the call in the remote TE.
     * This operation shall be called by the TE when it executes a TTCN-3 unicast call operation on a
     * component port, which has been connected to another component port. All in and inout procedure
     * parameters contain values. All out procedure parameters shall contain the distinct value of null
     * because they are only of relevance in a reply to the procedure call but not in the procedure call
     * itself. The procedure parameters are the parameters specified in the TTCN-3 signature template.
     * @param sender Port identifier at the sending component via which the message is sent.
     * @param receiver Identifier of the receiving component.
     * @param signature Identifier of the signature of the procedure call.
     * @param parameterList A list of value parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration.
     */
    public void tciCallConnected(TriPortId sender,
            TriComponentId receiver,
            TriSignatureId signature,
            TciParameterList parameterList) ;
    /**
     * On invocation of this operation the TE can initiate the procedure call corresponding to the signature
     * identifier signature at the called component receiver. The tciCallConnected operation shall
     * return without waiting for the return of the issued procedure call. Note that an optional timeout value,
     * which can be specified in the TTCN-3 ATS for a call operation, is not included in the
     * tciCallConnected operation signature. The TE is responsible to address this issue by starting a
     * timer for the TTCN-3 call operation in the PA with a separate TRI operation call,
     * i.e. triStartTimer. CH transmits the call to all remote TEs on which a receiver is being
     * executed and enqueues the call in the remote TEs.
     * This operation shall be called by the TE when it executes a TTCN-3 broadcast call operation on a
     * component port, which has been connected to other component ports. All in and inout procedure
     * parameters contain values. All out procedure parameters shall contain the distinct value of null
     * because they are only of relevance in a reply to the procedure call but not in the procedure call itself.
     * The procedure parameters are the parameters specified in the TTCN-3 signature template.
     * @param sender Port identifier at the sending component via which the message is sent.
     * @param signature Identifier of the signature of the procedure call.
     * @param parameterList A list of value parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration.
     */
    public void tciCallConnectedBC(TriPortId sender,
            TriSignatureId signature,
            TciParameterList parameterList) ;
    /**
     * On invocation of this operation the TE can initiate the procedure call corresponding to the signature
     * identifier signature at the called component receiver. The tciCallConnected operation shall
     * return without waiting for the return of the issued procedure call. Note that an optional timeout value,
     * which can be specified in the TTCN-3 ATS for a call operation, is not included in the
     * tciCallConnected operation signature. The TE is responsible to address this issue by starting a
     * timer for the TTCN-3 call operation in the PA with a separate TRI operation call,
     * i.e. triStartTimer. CH transmits the call to all remote TEs on which a receiver is being
     * executed and enqueues the call in the remote TEs.
     * This operation shall be called by the TE when it executes a TTCN-3 multicast call operation on a
     * component port, which has been connected to other component ports. All in and inout procedure
     * parameters contain values. All out procedure parameters shall contain the distinct value of null
     * because they are only of relevance in a reply to the procedure call but not in the procedure call itself.
     * The procedure parameters are the parameters specified in the TTCN-3 signature template.
     * @param sender Port identifier at the sending component via which the message is sent.
     * @param receivers Identifier of the receiving components.
     * @param signature Identifier of the signature of the procedure call.
     * @param parameterList A list of value parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration.
     */
    public void tciCallConnectedMC(TriPortId sender,
            TriComponentIdList receivers,
            TriSignatureId signature,
            TciParameterList parameterList) ;
    /**
     * On invocation of this operation the CH can issue the reply to a procedure call corresponding to the
     * signature identifier signature and component identifier receiver. CH transmits the reply to the
     * remote TE on which receiver is being executed and enqueues the reply in the remote TE.
     * This operation shall be called by the TE when it executes a TTCN-3 unicast reply operation on a
     * component port which has been connected to another component port.
     * All out and inout procedure parameters and the return value contain values. All in procedure
     * parameters shall contain the distinct value of null since they are only of relevance to the procedure
     * call but not in the reply to the call. The parameterList contains procedure call parameters. These
     * parameters are the parameters specified in the TTCN-3 signature template. If no return type has
     * been defined for the procedure signature in the TTCN-3 ATS, the distinct value null shall be
     * passed for the return value.
     * @param sender Identifier of the port sending the reply.
     * @param receiver Identifier of the component receiving the reply.
     * @param signature Identifier of the signature of the procedure call.
     * @param parameterList A list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration.
     * @param returnValue (Optional) return value of the procedure call.
     */
    public void tciReplyConnected(TriPortId sender,
            TriComponentId receiver,
            TriSignatureId signature,
            TciParameterList parameterList,
            Value returnValue) ;
    /**
     * On invocation of this operation the CH can issue the reply to a procedure call corresponding to the
     * signature identifier signature and all components connected to sender. CH transmits the
     * reply to all remote TEs on which receivers are being executed and enqueues the exception in
     * the remote TEs.
     * This operation shall be called by the TE when it executes a TTCN-3 broadcast reply operation on a
     * component port which has been connected to other component ports.
     * All out and inout procedure parameters and the return value contain values. All in procedure
     * parameters shall contain the distinct value of null since they are only of relevance to the
     * procedure call but not in the reply to the call. The parameterList contains procedure call
     * parameters. These parameters are the parameters specified in the TTCN-3 signature template. If
     * no return type has been defined for the procedure signature in the TTCN-3 ATS, the distinct value
     * null shall be passed for the return value.
     * @param sender Identifier of the port sending the reply.
     * @param signature Identifier of the signature of the procedure call.
     * @param parameterList A list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration.
     * @param returnValue (Optional) return value of the procedure call.
     */
    public void tciReplyConnectedBC(TriPortId sender,
            TriSignatureId signature,
            TciParameterList parameterList,
            Value returnValue) ;
    /**
     * On invocation of this operation the CH can issue the reply to a procedure call corresponding to the
     * signature identifier signature and one of the component identifier in receivers. CH transmits
     * the reply to the remote TEs on which receivers are being executed and enqueues the reply in
     * the remote TEs.
     * @param sender Identifier of the port sending the reply.
     * @param receivers Identifier of the components receiving the reply.
     * @param signature Identifier of the signature of the procedure call.
     * @param parameterList A list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration.
     * @param returnValue (Optional) return value of the procedure call.
     */
    public void tciReplyConnectedMC(TriPortId sender,
            TriComponentIdList receivers,
            TriSignatureId signature,
            TciParameterList parameterList,
            Value returnValue) ;
    /**
     * On invocation of this operation the CH can raise an exception to a procedure call corresponding to
     * the signature identifier signature and component identifier receiver.
     * CH transmits the exception to the remote TE on which receiver is being executed and enqueues
     * the exception in the remote TE.
     * This operation shall be called by the TE when it executes a TTCN-3 unicast raise operation on a
     * component port which has been connected to another component port.
     * @param sender Identifier of the port sending the reply.
     * @param receiver Identifier of the component receiving the reply.
     * @param signature Identifier of the signature of the procedure call.
     * @param except The exception value
     */
    public void tciRaiseConnected(TriPortId sender,
            TriComponentId receiver,
            TriSignatureId signature,
            Value except) ;
    /**
     * On invocation of this operation the CH can raise an exception to a procedure call corresponding
     * to the signature identifier signature and all components connected to sender.
     * CH transmits the exception to all remote TEs on which receivers are being executed and
     * enqueues the exception in the remote TEs.
     * This operation shall be called by the TE when it executes a TTCN-3 broadcast raise operation
     * on a component port which has been connected to other component ports.
     * @param sender Identifier of the port sending the reply.
     * @param signature Identifier of the signature of the procedure call.
     * @param except The exception value
     */
    public void tciRaiseConnectedBC(TriPortId sender,
            TriSignatureId signature,
            Value except) ;
    /**
     * On invocation of this operation the CH can raise an exception to a procedure call corresponding to
     * the signature identifier signature and one of the component identifier receivers. CH transmits
     * the exception to all remote TEs on which receivers are being executed and enqueues the
     * exception in the remote TEs.
     * This operation shall be called by the TE when it executes a TTCN-3 multicast raise operation on a
     * component port which has been connected to another component port.
     * @param sender Identifier of the port sending the reply.
     * @param receivers Identifiers of the component receiving the reply.
     * @param signature Identifier of the signature of the procedure call.
     * @param except The exception value
     */
    public void tciRaiseConnectedMC(TriPortId sender,
            TriComponentIdList receivers,
            TriSignatureId signature,
            Value except) ;
    /**
     * CH transmits the component creation request to the remote TE and calls there the
     * tciCreateTestComponent operation to obtain a component identifier for this component.
     * This operation shall be called from the TE when a component has to be created, either explicitly
     * when the TTCN-3 create operation is called or implicitly when the master test component (MTC)
     * or a control component has to be created. name shall be set to the distinct value null if no name
     * is given in the TTCN-3 create statement.
     * @param kind The kind of component that shall be created (any kind except of
     * SYSTEM).
     * @param componentType Identifier of the TTCN-3 component type that shall be created.
     * @param name 
     * @param hostId
     * @return A TriComponentIdType value for the created component.
     */
    public TriComponentId tciCreateTestComponentReq(int kind,
            Type componentType,
            String name,
            Value hostId) ;
    /**
     * CH transmits the start component request to the remote TE and calls there the
     * tciStartTestComponent operation.
     * This operation shall be called by the TE when it executes the TTCN-3 start operation.
     * @param comp Identifier of the component to be started.
     * @param behaviour Identifier of the behaviour to be started on the component.
     * @param parameterList A list of Values where each value defines a parameter from the parameter
     * list as defined in the TTCN-3 function declaration of the function being
     * started. The parameters in parameterList are ordered as they appear in
     * the TTCN-3 signature of the test case. If no parameters have to be passed
     * either the null value or an empty parameterList, i.e. a list of length
     * zero shall be passed.
     */
    public void tciStartTestComponentReq(TriComponentId comp,
            TciBehaviourId behaviour,
            TciParameterList parameterList) ;
    /**
     * CH transmits the stop component request to the remote TE and calls there the
     * tciStopTestComponent operation.
     * This operation shall be called by the TE when it executes the TTCN-3 stop operation.
     * @param comp Identifier of the component to be stopped.
     */
    public void tciStopTestComponentReq(TriComponentId comp) ;
    /**
     * CH transmits the connection request to the remote TE where it calls the tciConnect operation to
     * establish a logical connection between the two indicated ports. Note that both ports can be on
     * remote TEs. In this case, the operation returns only after calling the tciConnect operation on both
     * remote TEs.
     * This operation shall be called by the TE when it executes a TTCN-3 connect operation.
     * @param fromPort Identifier of the test component port to be connected from.
     * @param toPort Identifier of the test component port to be connected to.
     */
    public void tciConnectReq(TriPortId fromPort, TriPortId toPort) ;
    /**
     * CH transmits the disconnect request to the remote TE where it calls the tciDisconnect
     * operation to tear down the logical connection between the two indicated ports. Note that both
     * ports can be on remote TEs. In this case, the operation returns only after calling the
     * tciDisconnect operation on both remote TEs.
     * @param fromPort Identifier of the test component port to be disconnected.
     * @param toPort Identifier of the test component port to be disconnected.
     */
    public void tciDisconnectReq(TriPortId fromPort, TriPortId toPort) ;
    /**
     * The CH is notified of the termination of the indicated test component. Because the out values of
     * inout and out parameters of a function being executed on a test component have no effect on
     * that test component (ES 201 873-1 [1]), the tciTestComponentTerminateReq operation
     * does not have a parameterList parameter. CH communicates the termination of the
     * indicated component to all participating TEs and to the special TE*, which keeps track of the
     * overall verdict.
     * This operation shall be called by the TE when a test component terminates execution, either
     * explicitly with the TTNC-3 stop operation or implicitly, if it has reached the last statement.
     * @param comp Identifier of the component that has terminated.
     * @param verdict Verdict after termination of the component.
     */
    public void tciTestComponentTerminatedReq(TriComponentId comp, VerdictValue verdict) ;
    /**
     * CH transmits the running request to the remote TE having the test component to be checked,
     * where it calls the tciTestComponentRunning operation to check the execution status of the
     * indicated test component.
     * This operation shall be called by the TE when it executes a TTCN-3 running operation.
     * @param comp Identifier of the component to be checked for running.
     * @return true if the indicated component is still executing a behaviour, false otherwise.
     */
    public boolean tciTestComponentRunningReq(TriComponentId comp) ;
    /**
     * The CH determines the component id of the MTC.
     * This operation shall be called by the TE when it executes a TTCN-3 mtc operation.
     * @return true if the indicated component has completed executing its behaviour, false otherwise.
     */
    public TriComponentId tciGetMTCReq() ;
    /**
     * CH transmits the map request to the remote TE where it calls the tciMap operation to
     * establish a logical connection between the two indicated ports.
     * This operation shall be called by the TE when it executes a TTCN-3 map operation.
     * @param fromPort Identifier of the test component port to be mapped from.
     * @param toPort Identifier of the test component port to be mapped to.
     */
    public void tciMapReq(TriPortId fromPort, TriPortId toPort);
    /**
     * CH transmits the map request to the remote TE where it calls the tciMapParam operation to
     * establish a logical connection between the two indicated ports.
     * This operation shall be called by the TE when it executes a TTCN-3 map operation including
     * parameters.
     * @param fromPort Identifier of the test component port to be mapped from.
     * @param toPort Identifier of the test component port to be mapped to.
     * @param parameterList Configuration parameter list.
     */
    public void tciMapParamReq(TriPortId fromPort, TriPortId toPort,
            TciParameterList parameterList);
    /**
     * CH transmits the unmap request to the remote TE where it calls the tciUnmap operation to
     * tear down the logical connection between the two indicated ports.
     * This operation shall be called by the TE when it executes a TTCN-3 unmap operation.
     * @param fromPort Identifier of the test component port to be unmapped.
     * @param toPort Identifier of the test component port to be unmapped.
     */
    public void tciUnmapReq(TriPortId fromPort, TriPortId toPort);
    /**
     * CH transmits the unmap request to the remote TE where it calls the tciUnmapParam
     * operation to teardown the connection between the two indicated ports.
     * This operation shall be called by the TE when it executes a TTCN-3 unmap operation including
     * parameters.
     * @param fromPort Identifier of the test component port to be unmapped.
     * @param toPort Identifier of the test component port to be unmapped.
     * @param parameterList Configuration parameter list.
     */
    public void tciUnmapParamReq(TriPortId fromPort, TriPortId toPort,
            TciParameterList parameterList);
    /**
     * CH transmits the execute test case request to the remote TEs having system ports of the indicated
     * test case. Static connections to the SUT and the initialization of communication means for TSI ports
     * can be set up.
     * This operation can be called by the TE immediately before it starts the test case behaviour on the
     * MTC (in course of a TTCN-3 execute operation).
     * @param testComponentId A test case identifier as defined in the TTCN-3 module.
     * @param tsiPortList tsiPortList contains all ports that have been declared in the definition of
     * the system component for the test case, i.e. the TSI ports. If a system
     * component has not been explicitly defined for the test case, then the
     * tsiPortList contains all communication ports of the MTC. The ports in
     * tsiPortList are ordered as they appear in the respective TTCN-3
     * component type declaration.
     * If no ports have to be passed either the null value or an empty tsiPortList,
     * i.e. a list of length zero shall be passed.
     */
    public void tciExecuteTestCaseReq(TriComponentId testComponentId,
            TriPortIdList tsiPortList);
    /**
     * CH transmits the reset request to all involved TEs.
     * This operation can be called by the TE at any time to reset the test system.
     */
    public void tciResetReq() ;
    /**
     * CH transmits the done request to the remote TE having the test component to be checked, where
     * it calls the tciTestComponentDone operation to check the status of the indicated test
     * component.
     * This operation shall be called by the TE when it executes a TTCN-3 done operation.
     * @param testComponentId Identifier of the component to be checked for done.
     * @return true if the indicated component has completed executing its behaviour, false otherwise.
     */
    public boolean tciTestComponentDoneReq(TriComponentId testComponentId) ;
    /**
     * CH transmits the kill component request to the remote TE and calls there the
     * tciKillTestComponent operation.
     * This operation shall be called by the TE when it executes the TTCN-3 kill operation.
     * @param component Identifier of the component to be killed.
     */
    public void tciKillTestComponentReq(TriComponentId component);
    /**
     * CH transmits the request to the remote TE that created the test component in question, where it
     * calls the tciTestComponentAlive operation to check the status of the indicated test
     * component.
     * This operation shall be called by the TE when it executes the TTCN-3 alive operation.
     * @param component Identifier of the component to be checked for being alive.
     * @return true if the indicated component is alive, false otherwise.
     */
    public boolean tciTestComponentAliveReq (TriComponentId component);
    /**
     * CH transmits the request to the remote TE that created the test component in question, where it calls
     * the tciTestComponentKilled operation to check the status of the indicated test component.
     * This operation shall be called by the TE when it executes the TTCN-3 killed operation.
     * @param component Identifier of the component to be checked for being killed.
     * @return true if the indicated component has been killed, false otherwise.
     */
    public boolean tciTestComponentKilledReq (TriComponentId component);
    //%% Extensions:
    public void tciStaticConnectReq(TriPortId fromPort, TriPortId toPort);
    public void tciStaticMapReq(TriPortId fromPort, TriPortId toPort);
    public TriStatus tciSetStreamValue(TriPortId sender, TriComponentId receiver, Value streamValue);
}

