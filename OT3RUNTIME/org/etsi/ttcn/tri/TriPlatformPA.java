// TriPlatform
// TE -> PA
package org.etsi.ttcn.tri;
/**
 * Platform interface operations TE -> PA
 *
 */
public interface TriPlatformPA {
    // Ref: TRI-Definition 5.6.1
    /**
     * The PA shall reset all timing activities which it is currently performing, e.g. stop all running timers,
     * discard any pending timeouts of expired timers.
     * This operation can be called by the TE at any time to reset the PA.
     * 
     * @return The return status of the triPAReset operation. The return status indicates the local success
     * (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triPAReset();
    // Timer handling operations
    // Ref: TRI-Definition 5.6.2.1
    /**
     * On invocation of this operation the PA shall start the indicated timer with the indicated duration. The
     * timer runs from the value zero (0.0) up to the maximum specified by timerDuration. Should the timer
     * indicated by timerId already be running it is to be restarted. When the timer expires the PA will call
     * the triTimeout() operation with timerId.
     * This operation is called by the TE when a timer needs to be started.
     * 
     * @param timerId identifier of the timer instance
     * @param timerDuration duration of the timer in seconds
     * @return The return status of the triStartTimer operation. The return status indicates the local success
     * (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triStartTimer(TriTimerId timerId,
            TriTimerDuration timerDuration);
    // Ref: TRI-Definition 5.6.2.2
    /**
     * This operation is called by the TE when a timer is to be stopped.
     * On invocation of this operation the PA shall use the timerId to stop the indicated timer instance.
     * The stopping of an inactive timer, i.e. a timer which has not been started or has already expired,
     * should have no effect.
     * The triStopTimer operation returns TRI_OK if the operation has been performed successfully,
     * TRI_Error otherwise. Notice that stopping an inactive timer is a valid operation. In this case TRI_OK
     * shall be returned.
     * 
     * @param timerId identifier of the timer instance
     * @return The return status of the triStopTimer operation. The return status indicates the local success
     * (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triStopTimer(TriTimerId timerId);
    // Ref: TRI-Definition 5.6.2.3
    /**
     * This operation may be called by the TE when a TTCN-3 read timer operation is to be executed on the
     * indicated timer.
     * On invocation of this operation the PA shall use the timerId to access the time that elapsed since
     * this timer was started. The return value elapsedTime shall be provided in seconds. The reading of
     * an inactive timer, i.e. a timer which has not been started or already expired, shall return an elapsed
     * time value of zero.
     * @param timerId identifier of the timer instance
     * @param elapsedTime value of the time elapsed since the timer has been started in seconds
     * @return The return status of the triReadTimer operation. The return status indicates the local success
     * (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triReadTimer(TriTimerId timerId,
            TriTimerDuration elapsedTime);
    // Ref: TRI-Definition 5.6.2.4
    /**
     * This operation may be called by the TE when a TTCN-3 running timer operation is to be executed on
     * the indicated timer.
     * On invocation of this operation the PA shall use the timerId to access the status of the timer. The
     * operation sets running to the boolean value true if and only if the timer is currently running.
     * 
     * @param timerId identifier of the timer instance
     * @param running status of the timer
     * @return The return status of the triTimerRunning operation. The return status indicates the local success
     * (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triTimerRunning(TriTimerId timerId,
            TriBoolean running);
    // Miscellaneous operations
    // Ref: TRI-Definition 5.6.3.1
    /**
     * This operation is called by the TE when it executes a function which is defined to be TTCN-3 external
     * (i.e. all non-external functions are implemented within the TE).
     * In the invocation of a triExternalFunction operation by the TE all in and inout function
     * parameters contain encoded values. No error shall be indicated by the PA in case the value of any
     * out parameter is non-null.
     * For each external function specified in the TTCN-3 ATS the PA shall implement the behaviour. On
     * invocation of this operation the PA shall invoke the function indicated by the identifier functionId.
     * It shall access the specified in and inout function parameters in parameterList, evaluate the
     * external function using the values of these parameters, and compute values for inout and out
     * parameters in parameterList. The operation shall then return encoded values for all inout and out
     * function parameters and the encoded return value of the external function.
     * If no return type has been defined for this external function in the TTCN-3 ATS, the distinct value
     * null shall be used for the latter.
     * The triExternalFunction operation returns TRI_OK if the PA completes the evaluation of the
     * external function successfully, TRI_Error otherwise.
     * Note that whereas all other TRI operations are considered to be non-blocking, the
     * triExternalFunction operation is considered to be blocking. That means that the operation shall
     * not return before the indicated external function has been fully evaluated. External functions have to
     * be implemented carefully as they could cause deadlock of test component execution or even the
     * entire test system implementation.
     * 
     * @param functionId identifier of the external function
     * @param parameterList a list of encoded parameters for the indicated function. The parameters in
     * parameterList are ordered as they appear in the TTCN-3 function
     * declaration.
     * @param returnValue (optional) encoded return value
     * @return The return status of the triExternalFunction operation. The return status indicates the local
     * success (TRI_OK) or failure (TRI_Error) of the operation.
     */
    public TriStatus triExternalFunction(TriFunctionId functionId,
            TriParameterList parameterList, TriParameter returnValue);
}

