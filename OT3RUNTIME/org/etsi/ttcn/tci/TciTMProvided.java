// TCI-TM
// TE -> TM
package org.etsi.ttcn.tci;
import org.etsi.ttcn.tri.*;
/**
 * This interface specifies the operations the TM has to provide to the TE.
 *
 */
public interface TciTMProvided {
    /**
     * tciTestCaseStarted indicates to the TM that a test case with testCaseId has been started.
     * It will not be distinguished whether the test case has been started explicitly using the required
     * operation tciStartTestCase or implicitly while executing the control part.
     * Shall only be called after either the control part of the module or a test case has been started using
     * the required operations tciStartControl or tciStartTestCase
     * @param testCaseId A test case identifier as defined in the TTCN-3 module.
     * @param parameterList A list of values that are part of the test case signature. The parameters in
     * parameterList are ordered as they appear in the TTCN-3 test case
     * declaration.
     * @param timer A float value representing the duration of the test case timer.
     */
    public void tciTestCaseStarted (TciTestCaseId testCaseId, TciParameterList parameterList, Float
            timer);
    /**
     * This operation will be called by the TE to indicate the test management that the test case that has
     * been currently executed on the MTC has terminated and that the final verdict was verdict. On the
     * invocation of a tciTestCaseTerminated operation all out and inout test case parameters
     * contain Values. All in test case parameters contain the distinct value of null because they are
     * only of relevance to the test case start but not in the reply to the call.
     * Shall only be called after either the control part of the module or a test case has been started using
     * the required operations tciStartControl or tciStartTestCase
     * @param verdict The final verdict of the test case
     * @param parameterList A list of values that are part of the test case signature. The parameters in
     * parameterList are ordered as they appear in the TTCN-3 test case
     * declaration.
     */
    public void tciTestCaseTerminated (VerdictValue verdict, TciParameterList parameterList);
    /**
     * This operation will be called by the TE to indicate the test management that the control part of
     * the selected module has just terminated execution.
     * Shall only be called when the module execution has been started using the
     * tciStartControl operation
     */
    public void tciControlTerminated ();
    /**
     * The management provides to the TE a Value for the indicated parameterId. Every call of
     * tciGetModulePar() will return the same value throughout the execution of an explicitly
     * started test case or throughout the execution of a control part If the management cannot
     * provide a TTCN-3 value, the distinct null value shall be returned.
     * This operation shall be called whenever the TE needs to access the value of a module
     * parameter. Every accessed module parameter will be resolved only once between a
     * tciStartTestCase and tciTestCaseTerminated pair if a test case has been started
     * explicitly or between a tciStartControl and tciControlTerminated pair if the control
     * part of a module has been started.
     * @param parameterId The identifier of the module parameter as defined in the TTCN-3 module.
     * @return A value.
     */
    public Value tciGetModulePar (TciModuleParameterId parameterId);
    /**
     * The TM presents testComponentId and message to the user, how this done is not within
     * the scope of the present document.
     * Shall be called by the TE when the TTCN-3 statement log will be executed, either in the control
     * part of a module or within the test case.
     * @param testComponentId Identifier of the component that logs the message.
     * @param message A string value, i.e. the message to be logged.
     */
    public void tciLog (TriComponentId testComponentId, String message);
    /**
     * The TE indicates the occurrence of an unrecoverable error situation. message contains a
     * reason phrase that might be communicated to the test system user. It is up to the test
     * management to terminate execution of test cases or control parts if running. The test
     * management has to take explicit measures to terminate test execution immediately.
     * Can be called at any time by the TE to indicate an unrecoverable error situation. This error
     * situation could either be indicated by the CH or the CD or could occur within the TE.
     * @param message A string value, i.e. the error message.
     */
    public void tciError (String message);
    //%% Extensions ES 202 781 Configuration and Deployment Support
    public void tciConfigStarted(Value ref);
    public void tciConfigKilled(Value ref);
}

