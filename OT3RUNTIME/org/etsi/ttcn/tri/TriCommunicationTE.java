// TriCommunication
// SA -> TE
package org.etsi.ttcn.tri;
/**
 * TriCommunication interface SA -> TE
 *
 */
public interface TriCommunicationTE {
    // Message based communication operations
    // Ref: TRI-Definition 5.5.3.4
    /**
     * This operation shall pass the message to the TE indicating the component componentId to which the
     * TSI port tsiPortId is mapped.
     * The decoding of receivedMessage has to be done in the TE.
     * This operation can be called by the SA after it has received a message from the SUT. It can
     * only be used when tsiPortId has been either previously mapped to a port of componentId or
     * referenced in the previous triExecuteTestCase statement.
     * In the invocation of a triEnqueueMsg operation receivedMessage shall contain an encoded
     * value.
     * 
     * @param tsiPortId identifier of the test system interface port via which the message is enqueued
     * by the SUT Adaptor
     * @param sutAddress (optional) source address within the SUT
     * @param componentId identifier of the receiving test component
     * @param receivedMessage the encoded received message
     */
    public void triEnqueueMsg(TriPortId tsiPortId,
            TriAddress sutAddress, TriComponentId componentId,
            TriMessage receivedMessage);
    // Procedure based communication operations
    // Ref: TRI-Definition 5.5.4.10
    /**
     * The TE can enqueue this procedure call with the signature identifier signatureId at the port of the
     * component componentId to which the TSI port tsiPortId is mapped. The decoding of procedure
     * parameters has to be done in the TE.
     * The TE shall indicate no error in case the value of any out parameter is different from null.
     * This operation can be called by the SA after it has received a procedure call from the SUT. It can
     * only be used when tsiPortId has been either previously mapped to a port of componentId or
     * referenced in the previous triExecuteTestCase statement.
     * In the invocation of a triEnqueueCall operation all in and inout procedure parameters contain
     * encoded values.
     * 
     * @param tsiPortId identifier of the test system interface port via which the procedure call is
     * enqueued by the SUT Adaptor
     * @param sutAddress (optional) source address within the SUT
     * @param componentId identifier of the receiving test component
     * @param signatureId identifier of the signature of the procedure call
     * @param parameterList a list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration. Description of data passed as parameters to the
     * operation from the calling entity to the called entity
     */
    public void triEnqueueCall(TriPortId tsiPortId,
            TriAddress sutAddress, TriComponentId componentId,
            TriSignatureId signatureId, TriParameterList parameterList );
    // Ref: TRI-Definition 5.5.4.11
    /**
     * The TE can enqueue this reply to the procedure call with the signature identifier signatureId at
     * the port of the component componentId to which the TSI port tsiPortId is mapped. The
     * decoding of the procedure parameters has to be done within the TE.
     * The TE shall indicate no error in case the value of any in parameter or an undefined return value is
     * different from null.
     * This operation can be called by the SA after it has received a reply from the SUT. It can only be used
     * when tsiPortId has been either previously mapped to a port of componentId or referenced in the
     * previous triExecuteTestCase statement.
     * In the invocation of a triEnqueueReply operation all out and inout procedure parameters and the
     * return value contain encoded values.
     * If no return type has been defined for the procedure signature in the TTCN-3 ATS, the distinct value
     * null shall be used for the return value.
     * 
     * @param tsiPortId identifier of the test system interface port via which the reply is enqueued by the
      SUT Adaptor
     * @param sutAddress (optional) source address within the SUT
     * @param componentId identifier of the receiving test component
     * @param signatureId identifier of the signature of the procedure call
     * @param parameterList a list of encoded parameters which are part of the indicated signature. The
     * parameters in parameterList are ordered as they appear in the TTCN-3
     * signature declaration
     * @param returnValue (optional) encoded return value of the procedure call
     */
    public void triEnqueueReply(TriPortId tsiPortId, TriAddress sutAddress,
            TriComponentId componentId, TriSignatureId signatureId,
            TriParameterList parameterList, TriParameter returnValue);
    // Ref: TRI-Definition 5.5.4.12
    /**
     * The TE can enqueue this exception for the procedure call with the signature identifier signatureId
     * at the port of the component componentId to which the TSI port tsiPortId is mapped.
     * The decoding of the exception has to be done within the TE.
     * This operation can be called by the SA after it has received a reply from the SUT. It can only be
     * used when tsiPortId has been either previously mapped to a port of componentId or
     * referenced in the previous triExecuteTestCase statement.
     * In the invocation of a triEnqueueException operation exception shall contain an encoded
     * value.
     * 
     * @param tsiPortId identifier for the test system interface port via which the exception is
     * enqueued by the SUT Adaptor
     * @param sutAddress (optional) source address within the SUT
     * @param componentId identifier of the receiving test component
     * @param signatureId identifier of the signature of the procedure call which the exception
     * is associated with
     * @param exc the encoded exception
     */
    public void triEnqueueException(TriPortId tsiPortId,
            TriAddress sutAddress, TriComponentId componentId,
            TriSignatureId signatureId, TriException exc);
}

