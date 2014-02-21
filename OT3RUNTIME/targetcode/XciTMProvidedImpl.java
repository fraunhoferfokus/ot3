// UserTM
// provided by user, called by system

//package de.fraunhofer.fokus.ttcn.tci;
package targetcode;

import org.etsi.ttcn.tci.TciModuleParameterId;
import org.etsi.ttcn.tci.TciParameterList;
import org.etsi.ttcn.tci.TciTMProvided;
import org.etsi.ttcn.tci.TciTestCaseId;
import org.etsi.ttcn.tci.Value;
import org.etsi.ttcn.tci.VerdictValue;
import org.etsi.ttcn.tri.TriComponentId;

public class XciTMProvidedImpl implements TciTMProvided {

    @Override
    public void tciTestCaseStarted(TciTestCaseId testCaseId,
            TciParameterList parameterList, Float timer) {
        // TODO Auto-generated method stub
        Trace.print("TestCaseStarted");

    }

    @Override
    public void tciTestCaseTerminated(VerdictValue verdict,
            TciParameterList parameterList) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tciControlTerminated() {
        // TODO Auto-generated method stub

    }

    @Override
    public Value tciGetModulePar(TciModuleParameterId parameterId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void tciLog(TriComponentId testComponentId, String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tciError(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tciConfigStarted(Value ref) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tciConfigKilled(Value ref) {
        // TODO Auto-generated method stub

    }

}
