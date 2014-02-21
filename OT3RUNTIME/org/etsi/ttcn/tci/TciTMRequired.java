// TCI-TM
// TM -> TE
package org.etsi.ttcn.tci;
import org.etsi.ttcn.tri.*;
/**
 * This interface specifies the operations the TM requires from the TE. In addition to the operations specified in this interface, a
 * test management requires the operations as required at the TCI-CD interface.
 *
 */
public interface TciTMRequired {
    /**
     * tciRootModule selects the indicated module for execution through a subsequent call using
     * tciStartTestCase or tciStartControl. A tciError will be issued by the TE if no such
     * module exists.
     * @param moduleName The moduleName denotes the module identifiers as defined in the TTCN-3
     * module.
     * Shall be used only if neither the control part nor a test case is currently being executed.
     */
    public void tciRootModule (TciModuleId moduleName) ;
    /**
     * The TE provides to the management a list of imported modules of the root module. If no
     * imported module exists, an empty module list is returned. If the TE cannot provide a list, the
     * distinct null value shall be returned.
     * Shall be used only if a root module has been set before.
     * @return A list of all imported modules of the root module. The modules are ordered as they appear in
     * the TTCN-3 module. If no imported modules exist, an empty module list is returned.
     */
    public TciModuleIdList tciGetImportedModules ();
    /**
     * The TE provides to the management a list of module parameters of the identified module. If no
     * module parameters exist, an empty module parameter list is returned. If the TE cannot provide a list,
     * the distinct null value shall be returned.
     * Shall be used only if a root module has been set before.
     * @param moduleId The moduleName denotes the module identifiers for which the module
     * parameters should be retrieved.
     * @return A list of all module parameters of the identified module. The parameters are ordered as they appear
     * in the TTCN-3 module. If no parameters exist, an empty module parameter list is returned.
     */
    public TciModuleParameterList tciGetModuleParameters (TciModuleId moduleId);
    /**
     * The TE provides to the management a list of test cases. If no test cases exist, an empty test
     * case list is returned. If the TE cannot provide a list, the distinct null value shall be returned.
     * Shall be used only if a root module has been set before.
     * @return A list of all test cases that are either defined in or imported into the root module.
     */
    public TciTestCaseIdList tciGetTestCases ();
    /**
     * The TE provides to the management a list of parameter types of the given test case. If no test case
     * parameters exist, an empty parameter type list is returned. If the TE cannot provide a list, the distinct
     * null value shall be returned.
     * Shall be used only if a root module has been set before.
     * @param TestCaseId A test case identifier as defined in the TTCN-3 module.
     * @return A list of all parameter types of the given test case. The parameter types are ordered as they appear
     * in the TTCN-3 signature of the test case. If no parameters exist, an empty parameter type list is
     * returned.
     */
    public TciParameterTypeList tciGetTestCaseParameters (TciTestCaseId TestCaseId);
    /**
     * The TE provides to the management a list of system ports of the given test case. If no system ports
     * exist, an empty port list is returned. If the TE cannot provide a list, the distinct null value shall be
     * returned.
     * Shall be used only if a root module has been set before.
     * @param testCaseId A test case identifier as defined in the TTCN-3 module
     * @return A list of all system ports of the given test case that have been declared in the definition of the system
     * component for the test case, i.e. the TSI ports. If a system component has not been explicitly defined
     * for the test case, then the list contains all communication ports of the MTC test component. The ports
     * are ordered as they appear in the respective TTCN-3 component type declaration. If no system ports
     * exist, an empty list, i.e. a list of length zero is returned.
     */
    public TriPortIdList tciGetTestCaseTSI (TciTestCaseId testCaseId);
    /**
     * tciStartTestCase starts a testcase in the currently selected module with the given parameters.
     * A tciError will be issued by the TE if no such test case exists.
     * All in and inout test case parameters in parameterList contain Value. All out test case
     * parameters in parameterList shall contain the distinct value of null since they are only of
     * relevance when the test case terminates.
     * Shall be called only if a module has been selected before. Only testCaseIds for test cases that
     * are declared in the currently selected TTCN-3 module shall be passed. Test cases that are
     * imported in a referenced module cannot be started. To start imported test cases the referenced
     * (imported) module must be selected first using the tciRootModule operation.
     * @param testCaseId A test case identifier as defined in the TTCN-3 module
     * @param parameterList A list of Values where each value defines a parameter from the parameter list
     * as defined in the TTCN-3 test case definition. The parameters in
     * parameterList are ordered as they appear in the TTCN-3 signature of the
     * test case. If no parameters have to be passed either the null value or an
     * empty parameterList, i.e. a list of length zero shall be passed.
     * 
     */
    public void tciStartTestCase
    (String testCaseId, TciParameterList parameterList ) ;
    /**
     * tciStopTestCase stops the testcase currently being executed. If the TE is not executing a test case,
     * the operation will be ignored. If the control part is being executed, tciStopTestCase will stop
     * execution of the currently executed test case, i.e. the execution of the test case that has recently
     * been indicated using the provided operation tciTestCaseStarted. A possible executing control
     * part will continue execution as if the test case has stopped normally and returned with verdict
     * ERROR.
     * Shall be called only if a module has been selected before
     */
    public void tciStopTestCase () ;
    /**
     * Starts the control part of the selected module. The control part will start TTCN-3 test cases as
     * described in TTCN-3. While executing the control part the TE will call the provided operation
     * tciTestCaseStarted and tciTestCaseTerminated for every test case that has been
     * started and that has terminated. After termination of the control part the TE will call the provided
     * operation tciControlPartTerminated.
     * Shall be called only if a module has been selected before.
     * @return A TriComponentId that represents the test component the module control part is executed on. If
     * the TE cannot start control part of the selected module the distinct value null will be returned.
     */
    public TriComponentId tciStartControl () ;
    /**
     * tciStopControl stops execution of the control part. If no control part is currently being executed
     * the operation will be ignored. If a test case has been started directly this will stop execution of the
     * current test case as if tciStopTestCase has been called.
     * Shall only be called if a module has been selected before.
     */
    public void tciStopControl () ;
    //%% Extensions ES 202 781 Configuration and Deployment Support
    public void tciStartConfig
    (TciBehaviourId configId, TciParameterList parameterList);
    public void tciKillConfig(Value ref);
}

