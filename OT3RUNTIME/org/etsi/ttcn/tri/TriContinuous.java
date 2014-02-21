//%% New interface TriContinuous for the extension
//%% ES 202 786 Support of interfaces with continuous signals
package org.etsi.ttcn.tri;
/**
 * New interface TriContinuous for the extension
 * ES 202 786 Support of interfaces with continuous signals
 *
 */
public interface TriContinuous {
    /**
     * The operation starts the test system clock with a given precision. The
     * precision is defined by the in parameter ticksPerSecond. The
     * parameter specifies the number of time units (ticks) that characterizes
     * a second.
     * @param ticksPerSecond the precision of the clock given in ticks per second
     * @return The return status of the operation. The return status indicates the
     * success (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triStartClock(long ticksPerSecond);
    /**
     * The operation yields the actual clock value. The clock value is given
     * by the out parameter timepoint, which represents the number of time
     * units (ticks) that has elapsed since the start of the clock (see
     * triStartClock).
     * There was a preceding invocation of
     * triStartClock(int lont ticksPerSecond).
     * @param timepoint out current time
     * @return The return status of the operation. The return status indicates the
     * success (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triReadClock(TriLong timepoint);
    /**
     * The operation signals that the next sample step for a given port shall
     * start at the specified point of time timepoint.
     * At this point in time the PA will issue a
     * triProcessStep(in TriPortIDListType ports)
     * operation to inform the TE which ports shall be sampled next.
     * The paremeter timepoint is expressed as the number of time units
     * (ticks), that has elapsed since the start of the clock (see triStartClock).
     * A call to this operation returns immediately. The operation merely
     * triggers the corresponding triProcessStep operation.
     * If timepoint represent a point of time in the past then the operation
     * returns a TRI_Error value and has no other effect.
     * There was a preceding invocation of
     * triStartClock(int lont ticksPerSecond).
     * @param timepoint point in time when the execution of the next sample step for
     * a given stream port shall be started
     * @param port the stream port the sample step is requested for
     * @return The return status of the operation. The return status indicates the
     * success (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triNextSampling(long timepoint, TriPortId port);
    /**
     * The operation signals that the execution of component component
     * should be suspended until the specified point of time timepoint.
     * At this point in time the PA will issue a
     * triEndWait(component)
     * operation.
     * timepoint is expressed as the number of time units (ticks), that has
     * elapsed since the start of the clock (see triStartClock).
     * A call to this operation returns immediately. The operation merely
     * triggers the corresponding triEndWait operation, it does not schedule
     * the execution of the component.
     * If timepoint represent a point of time in the past then the operation
     * returns a TRI_Error value and has no other effect.
     * There was a preceding invocation of
     * triStartClock(int lont ticksPerSecond).
     * @param timepoint point in time until execution of a component should be
     * suspended
     * @param component component whose execution should be suspended
     * @return The return status of the operation. The return status indicates the
     * success (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triBeginWait(long timepoint, TriComponentId component);
    /**
     * The operation signals that the point in time timepoint that was
     * specified in the corresponding
     * triNextSampling(timepoint, port)
     * has been reached.
     * There was a preceding invocation of
     * triNextSampling(timepoint, port
     * @param ports a list of ports that shall be sampled at the operation call
     */
    public void triProcessStep(TriPortIdList ports);
    /**
     * The operation signals that the point in time timepoint that was
     * specified in the corresponding
     * triBeginWait(timepoint, component)
     * has been reached.
     * There was a preceding invocation of
     * triBeginWait(timepoint, component).
     * @param component component of the corresponding triBeginWait operation.
     */
    public void triEndWait(TriComponentId component);
    /**
     * The SA can update the message to the SUT.
     * The triSetStreamValue operation returns TRI_OK in case it has
     * been completed successfully. Otherwise TRI_Error shall be returned.
     * Notice that the return value TRI_OK does not imply that the SUT has
     * received streamValue.
     * The TE calls this operation when it executes a new sampling step on
     * a sampled output stream port, which has been mapped to a TSI port.
     * The TE calls the operation for all sampling steps of all outgoing
     * stream ports if no system component has been specified for a test
     * case, i.e. only a MTC test component is created for a test case.
     * The encoding of streamValue has to be done in the TE prior to this
     * TRI operation call.
     * @param componentId identifier of the sending test component
     * @param tsiPortId identifier of the test system interface port via which the
     * message is sent to the SUT Adapter
     * @param SUTaddress (optional) destination address within the SUT
     * @param streamValue the encoded stream value (message) to be sent
     * @return The return status of the triSetStreamValue operation. The return
     * status indicates the local success (TRI_OK) or failure (TRI_Error) of
     * the operation.
     */
    public TriStatus triSetStreamValue(TriComponentId componentId, TriPortId tsiPortId, TriAddress SUTaddress, TriMessage streamValue);
    /**
     * The SA can update the stream value at the input port.
     * The triGetStreamValue operation returns TRI_OK in case it has
     * been completed successfully. Otherwise TRI_Error shall be returned.
     * The TE calls this operation when it executes a new sampling step on
     * a sampled input stream port, which has been mapped to a TSI port.
     * The TE calls the operation for all sampling steps of all outgoing
     * stream ports if no system component has been specified for a test
     * case, i.e. only a MTC test component is created for a test case.
     * The decoding of streamValue has to be done in the TE after to this
     * TRI operation call.
     * @param componentId identifier of the sending test component
     * @param tsiPortId identifier of the test system interface port via which the
     * message is sent to the SUT Adapter
     * @param SUTaddress (optional) destination address within the SUT
     * @param streamValue out the encoded stream value (message) that has been
     * received from the SUT.
     * @return The return status of the triGetStreamValue operation. The return
     * status indicates the local success (TRI_OK) or failure (TRI_Error) of
     * the operation.
     */
    public TriStatus triGetStreamValue(TriComponentId componentId, TriPortId tsiPortId, TriAddress SUTaddress, TriMessage streamValue);
    
}
