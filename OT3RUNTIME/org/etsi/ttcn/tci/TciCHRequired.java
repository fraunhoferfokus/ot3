// TciCHRequired
// CH -> TE
package org.etsi.ttcn.tci;
import org.etsi.ttcn.tri.*;
/**
 * This interface specifies the operations the CH requires from the TE. In addition to the operations specified in this clause,
 * all required operations of the TCI-CD interface are also required
 *
 */
public interface TciCHRequired extends TciCDRequired {
    /**
     * The TE enqueues the received value into the local port queue of the indicated receiver component.
     * This operation shall be called by the CH at the local TE when at remote TE a provided
     * tciSendConnected has been called.
     * @param sender Port identifier at the sending component via which the message is sent.
     * @param receiver Identifier of the receiving component.
     * @param receivedMessage The value to be enqueued.
     */
    public void tciEnqueueMsgConnected(TriPortId sender,
            TriComponentId receiver,
            Value receivedMessage) ;
    /**
     * The TE enqueues the calls at the local port queue of the indicated receiver component.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciCallConnected has been called. All in and inout procedure parameters contain values. All
     * out procedure parameters shall contain the distinct value of null because they are only of
     * relevance in a reply to the procedure call but not in the procedure call itself. The procedure
     * parameters are the parameters specified in the TTCN-3 signature template.
     * @param sender Port identifier at the sending component via which the message is sent.
     * @param receiver Identifier of the receiving component.
     * @param signature Identifier of the signature of the procedure call.
     * @param parameterList A list of value parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration.
     */
    public void tciEnqueueCallConnected(TriPortId sender,
            TriComponentId receiver,
            TriSignatureId signature,
            TciParameterList parameterList) ;
    /**
     * The TE enqueues the reply at the local port queue of the indicated receiver component.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciReplyConnected has been called. All out and inout procedure parameters and the return
     * value contain values. All in procedure parameters shall contain the distinct value of null since they
     * are only of relevance to the procedure call but not in the reply to the call. The parameterList
     * contains procedure call parameters. These parameters are the parameters specified in the TTCN-3
     * signature template. If no return type has been defined for the procedure signature in the TTCN-3
     * ATS, the distinct value null shall be passed for the returnValue.
     * @param sender Identifier of the port sending the reply.
     * @param receiver Identifier of the component receiving the reply.
     * @param signature Identifier of the signature of the procedure call.
     * @param parameterList A list of value parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration.
     * @param returnValue (Optional) return value of the procedure call.
     */
    public void tciEnqueueReplyConnected(TriPortId sender,
            TriComponentId receiver,
            TriSignatureId signature,
            TciParameterList parameterList,
            Value returnValue) ;
    /**
     * The TE enqueues the exception at the local port queue of the indicated receiver component.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciRaiseConnected has been called.
     * @param sender Identifier of the port sending the reply.
     * @param receiver Identifier of the component receiving the reply.
     * @param signature Identifier of the signature of the procedure call.
     * @param except The exception.
     */
    public void tciEnqueueRaiseConnected(TriPortId sender,
            TriComponentId receiver,
            TriSignatureId signature,
            Value except) ;
    /**
     * The TE creates a TTCN-3 test component of the componentType and passes a
     * TriComponentIdType reference back to the CH. The CH communicates the reference back to
     * the remote TE.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciCreateTestComponentReq has been called. componentType shall be set to the distinct value
     * null if a test component of kind control shall be created. name shall be set to the distinct
     * value null if no name is given in the TTCN-3 create statement. If a non-null hostId is given, this
     * hostId should be used to identify the remote TE in which to call tciCreateTestComponent.
     * @param kind The kind of component that shall be created (any kind except of
     * SYSTEM).
     * @param componentType Identifier of the TTCN-3 component type that shall be created.
     * @param name Name of the component that shall be created.
     * @return A TriComponentId value for the created component.
     */
    public TriComponentId tciCreateTestComponent(int kind, Type componentType, String name) ;
    //%% missing parameter Value hostId
    /**
     * The TE shall start the indicated behaviour on the indicated component.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciStartTestComponentReq has been called.
     * @param comp Identifier of the component to be started. Refers to an identifier
     * previously created by a call of tciCreateTestComponent.
     * @param behaviour Identifier of the behaviour to be started on the component.
     * @param parameterList A list of Values where each value defines a parameter from the
     * parameter list as defined in the TTCN-3 function declaration of
     * the function being started. The parameters in parameterList
     * are ordered as they appear in the TTCN-3 signature of the test
     * case. If no parameters have to be passed either the null value
     * or an empty parameterList, i.e. a list of length zero shall be
     * passed.
     */
    public void tciStartTestComponent(TriComponentId comp,
            TciBehaviourId behaviour,
            TciParameterList parameterList) ;
    /**
     * The TE shall stop the indicated behaviour on the indicated component.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciStopTestComponentReq has been called.
     * @param comp Identifier of the component to be stopped.
     */
    public void tciStopTestComponent(TriComponentId comp) ;
    /**
     * The TE shall connect the indicated ports to one another
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciConnectReq has been called.
     * @param fromPort Identifier of the test component port to be connected from.
     * @param toPort Identifier of the test component port to be connected to.
     */
    public void tciConnect(TriPortId fromPort, TriPortId toPort) ;
    /**
     * The TE shall disconnect the indicated ports.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciDisconnectReq has been called.
     * @param fromPort Identifier of the test component port to be disconnected.
     * @param toPort Identifier of the test component port to be disconnected.
     */
    public void tciDisconnect(TriPortId fromPort, TriPortId toPort) ;
    /**
     * The local TE is notified of the termination of the indicated test component on a remote TE.
     * Because the out values of inout and out parameters of a function being executed on a test
     * component have no effect on that test component (ES 201 873-1 [1]), the
     * tciTestComponentTerminated operation does not have a parameterList parameter.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciTestComponentTerminatedReq has been called.
     * @param comp Identifier of the component that has terminated.
     * @param verdict Verdict after termination of the component.
     */
    public void tciTestComponentTerminated(TriComponentId comp, VerdictValue verdict);
    /**
     * The local TE determines whether the indicated component is executing a test behaviour. If the
     * component is executing a behaviour true will be returned. In any other case, e.g. test
     * component has finished execution, or test component has not been started, etc. false will be
     * returned. After the operation returns, the CH will communicate the value back to the remote TE.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciTestComponentRunningReq has been called.
     * @param comp Identifier of the component to be checked for running.
     * @return true if the indicated component is still executing a behaviour, false otherwise.
     */
    public boolean tciTestComponentRunning(TriComponentId comp);
    /**
     * The local TE determines whether the indicated component has completed executing its test
     * behaviour. If the component has completed its behaviour true will be returned. In any other case,
     * e.g. test component has not been started, or test component is still executing, false will be
     * returned. After the operation returns, the CH will communicate the value back to the remote TE.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciTestComponentDoneReq has been called.
     * @param comp Identifier of the component to be checked for done.
     * @return true if the indicated component has completed executing its behaviour, false otherwise.
     */
    public boolean tciTestComponentDone(TriComponentId comp);
    /**
     * The local TE determines whether the MTC is executing on the local TE. If the MTC executes on
     * the local TE the component id of the MTC is being returned. If the MTC is not executed on the
     * local TE the distinct value null will be returned. The operation will have no effect on the execution
     * of the MTC. After the operation returns, the CH will communicate the value back to the remote TE.
     * This operation can be called by the CH at the appropriate local TE when at a remote TE a provided
     * tciGetMTCReq has been called.
     * @return A TriComponentIdType value of the MTC if the MTC executes on the local TE, the distinct value
     * null otherwise.
     */
    public TriComponentId tciGetMTC ();
    /**
     * The local TE determines whether static connections to the SUT and the initialization of
     * communication means for TSI ports should be done.
     * This operation shall be called by the CH at the appropriate local TE when at a remote TE a provided
     * tciExecuteTestCaseReq has been called.
     * @param TestCaseId A test case identifier as defined in the TTCN-3 module.
     * @param tsiPortList Contains all ports that have been declared in the definition of the system
     * component for the test case, i.e. the TSI ports. If a system component has not
     * been explicitly defined for the test case, then the tsiPortList contains all
     * communication ports of the MTC. The ports in tsiPortList are ordered as they
     * appear in the respective TTCN-3 component type declaration. If no ports have to
     * be passed either the null value or an empty tsiPortList, i.e. a list of length
     * zero shall be passed.
     */
    public void tciExecuteTestCase (TciTestCaseId TestCaseId, TriPortIdList tsiPortList);
    /**
     * The TE can decide to take any means to reset the test system locally.
     * This operation shall be called by the CH at appropriate local TEs when at a remote TE a
     * provided tciResetReq has been called.
     */
    public void tciReset ();
    /**
     * The TE shall map the indicated ports to one another.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciMapReq has been called.
     * @param fromPort Identifier of the test component port to be mapped from.
     * @param toPort Identifier of the test component port to be mapped to.
     */
    public void tciMap (TriPortId fromPort, TriPortId toPort);
    /**
     * The TE shall map the indicated ports to one another.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciMapParamReq has been called.
     * @param fromPort Identifier of the test component port to be mapped from.
     * @param toPort Identifier of the test component port to be mapped to.
     * @param parameterList Configuration parameter list.
     */
    public void tciMapParam (TriPortId fromPort, TriPortId toPort,
            TciParameterList parameterList);
    /**
     * The TE shall unmap the indicated ports.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciUnmapReq has been called.
     * @param fromPort Identifier of the test component port to be unmapped.
     * @param toPort Identifier of the test component port to be unmapped.
     */
    public void tciUnmap (TriPortId fromPort, TriPortId toPort);
    /**
     * The TE shall unmap the indicated ports.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciUnmapParamReq has been called.
     * @param fromPort Identifier of the test component port to be unmapped.
     * @param toPort Identifier of the test component port to be unmapped.
     * @param parameterList Configuration parameter.
     */
    public void tciUnmapParam (TriPortId fromPort, TriPortId toPort,
            TciParameterList parameterList);
    /**
     * The TE stops the behaviour on the indicated component if necessary and transfers it into the
     * killed state.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciKillTestComponentReq has been called.
     * @param component Identifier of the component to be killed.
     */
    public void tciKillTestComponent(TriComponentId component);
    /**
     * The local TE determines whether the indicated component is alive. After the operation returns,
     * the CH will communicate the value back to the remote TE.
     * This operation shall be called by the CH at the local TE when at a remote TE a provided
     * tciTestComponentAliveReq has been called.
     * @param component Identifier of the component to be checked for being alive.
     * @return true if the indicated component is alive, false otherwise.
     */
    public boolean tciTestComponentAlive (TriComponentId component);
    /**
     * The local TE determines whether the indicated component is in the killed state. If it is, true
     * will be returned. In any other case, false will be returned. After the operation returns, the CH
     * will communicate the value back to the remote TE.
     * @param component Identifier of the component to be checked for being killed.
     * @return true if the indicated component has been killed, false otherwise.
     */
    public boolean tciTestComponentKilled (TriComponentId component);
    //%% Extensions:
    public void tciStaticConnect(TriPortId fromPort, TriPortId toPort);
    public void tciStaticMap(TriPortId fromPort, TriPortId toPort);
    public TriStatus tciSetStreamValueReq(TriPortId sender, TriComponentId receiver, Value streamValue);
}

