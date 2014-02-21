// TriCommunication
// TE -> SA
package org.etsi.ttcn.tri;
/**
 * 
 * TriCommunication interface TE -> SA
 * 
 */
public interface TriCommunicationSA {
    // Reset Operation
    // Ref: TRI-Definition 5.5.1
    /**
     * The SA shall reset all communication means that it is maintaining, e.g. reset static connections to the
     * SUT, close dynamic connections to the SUT, discard any pending messages or procedure calls.
     * This operation can be called by the TE at any time to reset the SA.
     * 
     * @return The return status of the triSAReset operation. The return status indicates the local success
     * (TRI_OK) or failure (TRI_Error) of the operation
     */
    public TriStatus triSAReset();
    // Connection handling operations
    // Ref: TRI-Definition 5.5.2.1
    /**
     * The SA can set up any static connections to the SUT and initialize any communication means for
     * TSI ports.
     * This operation is called by the TE immediately before the execution of any test case. The test case
     * that is going to be executed is indicated by the testCaseId. tsiPortList contains all ports that
     * have been declared in the definition of the system component for the test case, i.e. the TSI ports. If a
     * system component has not been explicitly defined for the test case in the TTCN-3 ATS then the
     * tsiPortList contains all communication ports of the MTC test component. The ports in
     * tsiPortList are ordered as they appear in the respective TTCN-3 component declaration
     *
     * @param testCaseId identifier of the test case that is going to be executed
     * @param tsiPorts a list of test system interface ports defined for the test system
     * @return The return status of the triExecuteTestCase operation. The return status indicates the local
     * success (TRI_OK) or failure (TRI_Error) of the operation
     */
    public TriStatus triExecuteTestCase(TriTestCaseId
            testCaseId,TriPortIdList tsiPorts);
    // Ref: TRI-Definition 5.5.2.2
    /**
     * The SA can establish a dynamic connection to the SUT for the referenced TSI port.
     * The triMap operation returns TRI_Error in case a connection could not be established
     * successfully, TRI_OK otherwise. The operation should return TRI_OK in case no dynamic
     * connection needs to be established by the test system.
     * This operation is called by the TE when it executes a TTCN-3 map operation
     * 
     * @param compPortId identifier of the test component port to be mapped
     * @param tsiPortId identifier of the test system interface port to be mapped
     * @return The return status of the triMap operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation.
     */
    public TriStatus triMap(TriPortId compPortId, TriPortId tsiPortId);
    // Ref: TRI-Definition 5.5.2.3
    /**
     * The SA can establish a dynamic connection to the SUT for the referenced TSI port.
     * The triMapParam operation returns TRI_Error in case a connection could not be established
     * successfully, TRI_OK otherwise. The operation should return TRI_OK in case no dynamic
     * connection needs to be established by the test system. The configuration parameter paramList can
     * be used for setting connection establishment specific parameters.
     * This operation is called by the TE when it executes a TTCN-3 map operation including parameters.
     * 
     * @param compPortId identifier of the test component port to be mapped
     * @param tsiPortId identifier of the test system interface port to be mapped
     * @param paramList configuration parameter list
     * @return The return status of the triMapParam operation. The return status indicates the local success
     * (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triMapParam(TriPortId compPortId, TriPortId tsiPortId,
            TriParameterList paramList);
    // Ref: TRI-Definition 5.5.2.4
    /**
     * The SA shall close a dynamic connection to the SUT for the referenced TSI port.
     * The triUnmap operation returns TRI_Error in case a connection could not be closed successfully or
     * no such connection has been established previously, TRI_OK otherwise. The operation should return
     * TRI_OK in case no dynamic connections have to be closed by the test system
     * This operation is called by the TE when it executes any TTCN-3 unmap operation.
     * 
     * @param compPortId identifier of the test component port to be unmapped
     * @param tsiPortId identifier of the test system interface port to be unmapped
     * @return The return status of the triUnmap operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation
     */
    public TriStatus triUnmap(TriPortId compPortId, TriPortId tsiPortId);
    // Ref: TRI-Definition 5.5.2.5
    /**
     * The SA shall close a dynamic connection to the SUT for the referenced TSI port.
     * The triUnmapParam operation returns TRI_Error in case a connection could not be closed
     * successfully or no such connection has been established previously, TRI_OK otherwise. The
     * operation should return TRI_OK in case no dynamic connections have to be established by the test
     * system. The configuration parameter paramList can be used for setting connection teardown specific
     * parameters.
     * This operation is called by the TE when it executes any TTCN-3 unmap operation including param.
     * 
     * @param compPortId identifier of the test component port to be unmapped
     * @param tsiPortId identifier of the test system interface port to be unmapped
     * @param paramList configuration parameter list
     * @return The return status of the triUnmapParam operation. The return status indicates the local success
     * (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triUnmapParam(TriPortId compPortId, TriPortId tsiPortId,
            TriParameterList paramList);
    // Ref: TRI-Definition 5.5.2.5 %% Extension ES 202 781 Configuration and Deopoyment Support
    /**
     * The SA can establish a static connection to the SUT for the referenced TSI port.
     * The triStaticMap operation returns TRI_Error in case a connection could not be established
     * successfully, TRI_OK otherwise. The operation should return TRI_OK in case no static connection
     * needs to be established by the test system.
     * This operation is called by the TE when it executes a TTCN-3 static map operation.
     * 
     * @param compPortId identifier of the test component port to be mapped in a static connection
     * @param tsiPortId identifier of the test system interface port to be mapped in a static connection
     * @return The return status of the triStaticMap operation. The return status indicates the local success
     * (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triStaticMap(TriPortId compPortId, TriPortId tsiPortId);
    // Ref: TRI-Definition 5.5.2.6
    /**
     * The SA can free resources, cease communication at system ports and to test components.
     * This operation is called by the TE immediately after the execution of any test case.
     * 
     * @return The return status of the triEndTestCase operation. The return status indicates the local success
     * (TRI_OK) or failure (TRI_Error) of the operation
     */
    public TriStatus triEndTestCase();
    // Message based communication operations
    // Ref: TRI-Definition 5.5.3.1
    /**
     * The SA can send the message to the SUT.
     * The triSend operation returns TRI_OK in case it has been completed successfully. Otherwise
     * TRI_Error shall be returned. Notice that the return value TRI_OK does not imply that the SUT has
     * received sendMessage.
     * This operation is called by the TE when it executes a TTCN-3 unicast send operation on a component
     * port, which has been mapped to a TSI port. This operation is called by the TE for all TTCN-3 send
     * operations if no system component has been specified for a test case, i.e. only a MTC test component
     * is created for a test case.
     * The encoding of sendMessage has to be done in the TE prior to this TRI operation call.
     *
     * @param componentId identifier of the sending test component
     * @param tsiPortId identifier of the test system interface port via which the message is sent to the SUT
     * Adaptor
     * @param sutAddress (optional) destination address within the SUT
     * @param sendMessage the encoded message to be sent
     * @return The return status of the triSend operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation
     */
    public TriStatus triSend(TriComponentId componentId, TriPortId tsiPortId,
            TriAddress sutAddress, TriMessage sendMessage);
    // Ref: TRI-Definition 5.5.3.2
    /**
     * The SA can broadcast the message to the SUT.
     * The triSendBC operation returns TRI_OK in case it has been completed successfully. Otherwise
     * TRI_Error shall be returned. Notice that the return value TRI_OK does not imply that the SUT has
     * received sendMessage.
     * This operation is called by the TE when it executes a TTCN-3 broadcast send operation on a
     * component port, which has been mapped to a TSI port. This operation is called by the TE for all
     * TTCN-3 send operations if no system component has been specified for a test case, i.e. only a MTC
     * test component is created for a test case.
     * The encoding of sendMessage has to be done in the TE prior to this TRI operation call.
     * @param componentId identifier of the sending test component
     * @param tsiPortId identifier of the test system interface port via which the message is sent to the SUT
     * Adaptor
     * @param sendMessage the encoded message to be sent
     * @return The return status of the triSendBC operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation
     */
    public TriStatus triSendBC(TriComponentId componentId, TriPortId tsiPortId,
            TriMessage sendMessage);
    // Ref: TRI-Definition 5.5.3.3
    /**
     * The SA can multicast the message to the SUT.
     * The triSendMC operation returns TRI_OK in case it has been completed successfully. Otherwise
     * TRI_Error shall be returned. Notice that the return value TRI_OK does not imply that the SUT has
     * received sendMessage.
     * This operation is called by the TE when it executes a TTCN-3 multicast send operation on a
     * component port, which has been mapped to a TSI port. This operation is called by the TE for all
     * TTCN-3 send operations if no system component has been specified for a test case, i.e. only a MTC
     * test component is created for a test case.
     * The encoding of sendMessage has to be done in the TE prior to this TRI operation call.
     * 
     * @param componentId identifier of the sending test component
     * @param tsiPortId identifier of the test system interface port via which the message is sent to the SUT
     * Adaptor
     * @param sutAddress (optional) destination address within the SUT
     * @param sendMessage the encoded message to be sent
     * @return The return status of the triSendMC operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation 
     */
    public TriStatus triSendMC(TriComponentId componentId, TriPortId tsiPortId,
            TriAddressList sutAddresses, TriMessage sendMessage);
    // Procedure based communication operations
    // Ref: TRI-Definition 5.5.4.1
    /**
     * On invocation of this operation the SA can initiate the procedure call corresponding to the signature
     * identifier signatureId and the TSI port tsiPortId.
     * The triCall operation shall return without waiting for the return of the issued procedure call (see
     * note). This TRI operation returns TRI_OK on successful initiation of the procedure call, TRI_Error
     * otherwise. No error shall be indicated by the SA in case the value of any out parameter is non-null.
     * Notice that the return value of this TRI operation does not make any statement about the success or
     * failure of the procedure call.
     * Note that an optional timeout value, which can be specified in the TTCN-3 ATS for a call operation, is
     * not included in the triCall operation signature. The TE is responsible to address this issue by
     * starting a timer for the TTCN-3 call operation in the PA with a separate TRI operation call,
     * i.e. triStartTimer.
     * This operation is called by the TE when it executes a TTCN-3 unicast call operation on a component
     * port, which has been mapped to a TSI port. This operation is called by the TE for all TTCN-3 call
     * operations if no system component has been specified for a test case, i.e. only a MTC test
     * component is created for a test case.
     * All in and inout procedure parameters contain encoded values.
     * The procedure parameters are the parameters specified in the TTCN-3 signature template. Their
     * encoding has to be done in the TE prior to this TRI operation call.
     * 
     * @param componentId identifier of the test component issuing the procedure call
     * @param tsiPortId identifier of the test system interface port via which the procedure call is sent
     * to the SUT Adaptor
     * @param sutAddress (optional) destination address within the SUT
     * @param signatureId identifier of the signature of the procedure call
     * @param parameterList a list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration
     * @return The return status of the triCall operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation
     * 
     */
    public TriStatus triCall(TriComponentId componentId,
            TriPortId tsiPortId, TriAddress sutAddress,
            TriSignatureId signatureId, TriParameterList parameterList);
    // Ref: TRI-Definition 5.5.4.2
    /**
     * 
     * On invocation of this operation the SA can initiate and broadcast the procedure call corresponding to the signature
     * identifier signatureId and the TSI port tsiPortId.
     * The triCallBC operation shall return without waiting for the return of the issued procedure call (see
     * note). This TRI operation returns TRI_OK on successful initiation of the procedure call, TRI_Error
     * otherwise. No error shall be indicated by the SA in case the value of any out parameter is non-null.
     * Notice that the return value of this TRI operation does not make any statement about the success or
     * failure of the procedure call.
     * Note that an optional timeout value, which can be specified in the TTCN-3 ATS for a call operation, is
     * not included in the triCallBC operation signature. The TE is responsible to address this issue by
     * starting a timer for the TTCN-3 call operation in the PA with a separate TRI operation call,
     * i.e. triStartTimer.
     * This operation is called by the TE when it executes a TTCN-3 broadcast call operation on a component
     * port, which has been mapped to a TSI port. This operation is called by the TE for all TTCN-3 call
     * operations if no system component has been specified for a test case, i.e. only a MTC test
     * component is created for a test case.
     * All in and inout procedure parameters contain encoded values.
     * The procedure parameters are the parameters specified in the TTCN-3 signature template. Their
     * encoding has to be done in the TE prior to this TRI operation call.
     * 
     * @param componentId identifier of the test component issuing the procedure call
     * @param tsiPortId identifier of the test system interface port via which the procedure call is sent
     * to the SUT Adaptor
     * @param signatureId identifier of the signature of the procedure call
     * @param parameterList a list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration
     * @return The return status of the triCallBC operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation
     * 
     */
    public TriStatus triCallBC(TriComponentId componentId,
            TriPortId tsiPortId,
            TriSignatureId signatureId, TriParameterList parameterList);
    // Ref: TRI-Definition 5.5.4.3
    /**
     * On invocation of this operation the SA can initiate the procedure call corresponding to the signature
     * identifier signatureId and the TSI port tsiPortId.
     * The TriCallMC operation shall return without waiting for the return of the issued procedure call (see
     * note). This TRI operation returns TRI_OK on successful initiation of the procedure call, TRI_Error
     * otherwise. No error shall be indicated by the SA in case the value of any out parameter is non-null.
     * Notice that the return value of this TRI operation does not make any statement about the success or
     * failure of the procedure call.
     * Note that an optional timeout value, which can be specified in the TTCN-3 ATS for a call operation, is
     * not included in the TriCallMC operation signature. The TE is responsible to address this issue by
     * starting a timer for the TTCN-3 call operation in the PA with a separate TRI operation call,
     * i.e. triStartTimer.
     * This operation is called by the TE when it executes a TTCN-3 multicast call operation on a component
     * port, which has been mapped to a TSI port. This operation is called by the TE for all TTCN-3 call
     * operations if no system component has been specified for a test case, i.e. only a MTC test
     * component is created for a test case.
     * All in and inout procedure parameters contain encoded values.
     * The procedure parameters are the parameters specified in the TTCN-3 signature template. Their
     * encoding has to be done in the TE prior to this TRI operation call.
     * 
     * @param componentId identifier of the test component issuing the procedure call
     * @param tsiPortId identifier of the test system interface port via which the procedure call is sent
     * to the SUT Adaptor
     * @param sutAddress (optional) destination address within the SUT
     * @param signatureId identifier of the signature of the procedure call
     * @param parameterList a list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration
     * @return The return status of the TriCallMC operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation
     * 
     */
    public TriStatus triCallMC(TriComponentId componentId,
            TriPortId tsiPortId, TriAddressList sutAddresses,
            TriSignatureId signatureId, TriParameterList parameterList);
    // Ref: TRI-Definition 5.5.4.4
    /**
     * On invocation of this operation the SA can issue the reply to a procedure call corresponding to the
     * signature identifier signatureId and the TSI port tsiPortId.
     * The triReply operation will return TRI_OK on successful execution of this operation, TRI_Error
     * otherwise. The SA shall indicate no error in case the value of any in parameter or an undefined return
     * value is different from null.
     * This operation is called by the TE when it executes a TTCN-3 unicast reply operation on a
     * component port that has been mapped to a TSI port. This operation is called by the TE for all
     * TTCN-3 reply operations if no system component has been specified for a test case, i.e. only a MTC
     * test component is created for a test case.
     * All out and inout procedure parameters and the return value contain encoded values.
     * The parameterList contains procedure call parameters. These parameters are the parameters
     * specified in the TTCN-3 signature template. Their encoding has to be done in the TE prior to this TRI
     * operation call.
     * If no return type has been defined for the procedure signature in the TTCN-3 ATS, the distinct value
     * null shall be passed for the return value.
     * 
     * @param componentId identifier of the replying test component
     * @param tsiPortId identifier of the test system interface port via which the reply is sent to the
     * SUT Adaptor
     * @param sutAddress (optional) destination address within the SUT
     * @param signatureId identifier of the signature of the procedure call
     * @param parameterList a list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration
     * @param returnValue (optional) encoded return value of the procedure call
     * @return The return status of the triReply operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation.
     */
    public TriStatus triReply(TriComponentId componentId,
            TriPortId tsiPortId, TriAddress sutAddress,
            TriSignatureId signatureId, TriParameterList parameterList,
            TriParameter returnValue);
    // Ref: TRI-Definition 5.5.4.5
    /**
     * On invocation of this operation the SA can issue the reply to a procedure call corresponding to the
     * signature identifier signatureId and the TSI port tsiPortId.
     * The TriReplyBC operation will return TRI_OK on successful execution of this operation, TRI_Error
     * otherwise. The SA shall indicate no error in case the value of any in parameter or an undefined return
     * value is different from null.
     * This operation is called by the TE when it executes a TTCN-3 broadcast reply operation on a
     * component port that has been mapped to a TSI port. This operation is called by the TE for all
     * TTCN-3 reply operations if no system component has been specified for a test case, i.e. only a MTC
     * test component is created for a test case.
     * All out and inout procedure parameters and the return value contain encoded values.
     * The parameterList contains procedure call parameters. These parameters are the parameters
     * specified in the TTCN-3 signature template. Their encoding has to be done in the TE prior to this TRI
     * operation call.
     * If no return type has been defined for the procedure signature in the TTCN-3 ATS, the distinct value
     * null shall be passed for the return value.
     * 
     * @param componentId identifier of the replying test component
     * @param tsiPortId identifier of the test system interface port via which the reply is sent to the
     * SUT Adaptor
     * @param signatureId identifier of the signature of the procedure call
     * @param parameterList a list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration
     * @param returnValue (optional) encoded return value of the procedure call
     * @return The return status of the TriReplyBC operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation.
     */
    public TriStatus triReplyBC(TriComponentId componentId,
            TriPortId tsiPortId,
            TriSignatureId signatureId, TriParameterList parameterList,
            TriParameter returnValue);
    // Ref: TRI-Definition 5.5.4.6
    /**
     * On invocation of this operation the SA can issue the reply to a procedure call corresponding to the
     * signature identifier signatureId and the TSI port tsiPortId.
     * The TriReplyMC operation will return TRI_OK on successful execution of this operation, TRI_Error
     * otherwise. The SA shall indicate no error in case the value of any in parameter or an undefined return
     * value is different from null.
     * This operation is called by the TE when it executes a TTCN-3 multicast reply operation on a
     * component port that has been mapped to a TSI port. This operation is called by the TE for all
     * TTCN-3 reply operations if no system component has been specified for a test case, i.e. only a MTC
     * test component is created for a test case.
     * All out and inout procedure parameters and the return value contain encoded values.
     * The parameterList contains procedure call parameters. These parameters are the parameters
     * specified in the TTCN-3 signature template. Their encoding has to be done in the TE prior to this TRI
     * operation call.
     * If no return type has been defined for the procedure signature in the TTCN-3 ATS, the distinct value
     * null shall be passed for the return value.
     * 
     * @param componentId identifier of the replying test component
     * @param tsiPortId identifier of the test system interface port via which the reply is sent to the
     * SUT Adaptor
     * @param sutAddress (optional) destination address within the SUT
     * @param signatureId identifier of the signature of the procedure call
     * @param parameterList a list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration
     * @param returnValue (optional) encoded return value of the procedure call
     * @return The return status of the TriReplyMC operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation.
     */
    public TriStatus triReplyMC(TriComponentId componentId,
            TriPortId tsiPortId, TriAddressList sutAddresses,
            TriSignatureId signatureId, TriParameterList parameterList,
            TriParameter returnValue);
    // Ref: TRI-Definition 5.5.4.7
    /**
     * On invocation of this operation the SA can raise an exception to a procedure call corresponding to
     * the signature identifier signatureId and the TSI port tsiPortId.
     * This operation is called by the TE when it executes a TTCN-3 unicast raise operation on a
     * component port that has been mapped to a TSI port. This operation is called by the TE for all
     * TTCN-3 raise operations if no system component has been specified for a test case, i.e. only a MTC
     * test component is created for a test case.
     * The encoding of the exception has to be done in the TE prior to this TRI operation call.
     * 
     * @param componentId identifier of the test component raising the exception
     * @param tsitPortId identifier of the test system interface port via which the exception is sent to
     * the SUT Adaptor
     * @param sutAddress (optional) destination address within the SUT
     * @param signatureId identifier of the signature of the procedure call which the exception is
     * associated with
     * @param exc the encoded exception
     * @return The return status of the triRaise operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation.
     */
    public TriStatus triRaise(TriComponentId componentId, TriPortId tsitPortId,
            TriAddress sutAddress,
            TriSignatureId signatureId,
            TriException exc);
    // Ref: TRI-Definition 5.5.4.8
    /**
     * On invocation of this operation the SA can raise an exception to a procedure call corresponding to
     * the signature identifier signatureId and the TSI port tsiPortId.
     * This operation is called by the TE when it executes a TTCN-3 broadcast raise operation on a
     * component port that has been mapped to a TSI port. This operation is called by the TE for all
     * TTCN-3 raise operations if no system component has been specified for a test case, i.e. only a MTC
     * test component is created for a test case.
     * The encoding of the exception has to be done in the TE prior to this TRI operation call.
     * 
     * @param componentId identifier of the test component raising the exception
     * @param tsitPortId identifier of the test system interface port via which the exception is sent to
     * the SUT Adaptor
     * @param signatureId identifier of the signature of the procedure call which the exception is
     * associated with
     * @param exc the encoded exception
     * @return The return status of the triRaiseBC operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation.
     */
    public TriStatus triRaiseBC(TriComponentId componentId,
            TriPortId tsitPortId,
            TriSignatureId signatureId,
            TriException exc);
    // Ref: TRI-Definition 5.5.4.9
    /**
     * On invocation of this operation the SA can raise an exception to a procedure call corresponding to
     * the signature identifier signatureId and the TSI port tsiPortId.
     * This operation is called by the TE when it executes a TTCN-3 multicast raise operation on a
     * component port that has been mapped to a TSI port. This operation is called by the TE for all
     * TTCN-3 raise operations if no system component has been specified for a test case, i.e. only a MTC
     * test component is created for a test case.
     * The encoding of the exception has to be done in the TE prior to this TRI operation call.
     * 
     * @param componentId identifier of the test component raising the exception
     * @param tsitPortId identifier of the test system interface port via which the exception is sent to
     * the SUT Adaptor
     * @param sutAddress (optional) destination address within the SUT
     * @param signatureId identifier of the signature of the procedure call which the exception is
     * associated with
     * @param exc the encoded exception
     * @return The return status of the TriRaiseMC operation. The return status indicates the local success (TRI_OK)
     * or failure (TRI_Error) of the operation.
     */
    public TriStatus triRaiseMC(TriComponentId componentId, TriPortId tsitPortId,
            //%% TriAddresses sutAddresses,
            TriAddressList sutAddresses,
            TriSignatureId signatureId,
            TriException exc);
    // Miscellaneous operations
    // Ref: TRI-Definition 5.5.5.1
    /**
     * On invocation of this operation the SA shall initiate the described actions to be taken on the SUT, e.g.
     * turn on, initialize, or send a message to the SUT.
     * The triSUTactionInformal operation returns TRI_OK on successful execution of the operation,
     * TRI_Error otherwise. Notice that the return value of this TRI operation does not make any statement
     * about the success or failure of the actions to be taken on the SUT.
     * This operation is called by the TE when it executes a TTCN-3 SUT action operation, which only
     * contains a string.
     * @param description an informal description of an action to be taken on the SUT
     * @return The return status of the triSUTactionInformal operation. The return status indicates the local
     * success (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triSutActionInformal(String description);
}

