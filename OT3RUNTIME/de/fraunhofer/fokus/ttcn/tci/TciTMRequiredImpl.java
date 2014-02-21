package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciBehaviourId;
import org.etsi.ttcn.tci.TciModuleId;
import org.etsi.ttcn.tci.TciModuleIdList;
import org.etsi.ttcn.tci.TciModuleParameterList;
import org.etsi.ttcn.tci.TciParameterList;
import org.etsi.ttcn.tci.TciParameterTypeList;
import org.etsi.ttcn.tci.TciTMRequired;
import org.etsi.ttcn.tci.TciTestCaseId;
import org.etsi.ttcn.tci.TciTestCaseIdList;
import org.etsi.ttcn.tci.Value;
import org.etsi.ttcn.tri.TriComponentId;
import org.etsi.ttcn.tri.TriPortIdList;

public class TciTMRequiredImpl implements TciTMRequired {

    @Override
    public void tciRootModule(TciModuleId moduleName) {
        // TODO Auto-generated method stub

    }

    @Override
    public TciModuleIdList tciGetImportedModules() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TciModuleParameterList tciGetModuleParameters(TciModuleId moduleId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TciTestCaseIdList tciGetTestCases() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TciParameterTypeList tciGetTestCaseParameters(
            TciTestCaseId TestCaseId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TriPortIdList tciGetTestCaseTSI(TciTestCaseId testCaseId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void tciStartTestCase(String testCaseId,
            TciParameterList parameterList) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tciStopTestCase() {
        // TODO Auto-generated method stub

    }

    @Override
    public TriComponentId tciStartControl() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void tciStopControl() {
        // TODO Auto-generated method stub

    }

    @Override
    public void tciStartConfig(TciBehaviourId configId,
            TciParameterList parameterList) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tciKillConfig(Value ref) {
        // TODO Auto-generated method stub

    }

}
