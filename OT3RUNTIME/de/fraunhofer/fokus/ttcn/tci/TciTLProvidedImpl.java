package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.ComponentStatus;
import org.etsi.ttcn.tci.TciBehaviourId;
import org.etsi.ttcn.tci.TciNonValueTemplate;
import org.etsi.ttcn.tci.TciParameterList;
import org.etsi.ttcn.tci.TciStatus;
import org.etsi.ttcn.tci.TciTLProvided;
import org.etsi.ttcn.tci.TciTestCaseId;
import org.etsi.ttcn.tci.TciValueDifferenceList;
import org.etsi.ttcn.tci.TciValueList;
import org.etsi.ttcn.tci.TciValueTemplate;
import org.etsi.ttcn.tci.TimerStatus;
import org.etsi.ttcn.tci.Value;
import org.etsi.ttcn.tci.VerdictValue;
import org.etsi.ttcn.tri.QualifiedName;
import org.etsi.ttcn.tri.TriAddress;
import org.etsi.ttcn.tri.TriAddressList;
import org.etsi.ttcn.tri.TriComponentId;
import org.etsi.ttcn.tri.TriException;
import org.etsi.ttcn.tri.TriMessage;
import org.etsi.ttcn.tri.TriParameter;
import org.etsi.ttcn.tri.TriParameterList;
import org.etsi.ttcn.tri.TriPortId;
import org.etsi.ttcn.tri.TriPortIdList;
import org.etsi.ttcn.tri.TriSignatureId;
import org.etsi.ttcn.tri.TriStatus;
import org.etsi.ttcn.tri.TriTimerDuration;
import org.etsi.ttcn.tri.TriTimerId;

public class TciTLProvidedImpl implements TciTLProvided {

    @Override
    public void tliTcExecute(String am, int ts, String src, int line,
            TriComponentId c, TciTestCaseId tcId, TriParameterList triPars,
            TriTimerDuration dur) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTcStart(String am, int ts, String src, int line,
            TriComponentId c, TciTestCaseId tcId, TciParameterList tciPars,
            TriTimerDuration dur) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTcStop(String am, int ts, String src, int line,
            TriComponentId c, String reason) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTcStarted(String am, int ts, String src, int line,
            TriComponentId c, TciTestCaseId tcId, TciParameterList tciPars,
            TriTimerDuration dur) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTcTerminated(String am, int ts, String src, int line,
            TriComponentId c, TciTestCaseId tcId, TciParameterList tciPars,
            VerdictValue verdict, String reason) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCtrlStart(String am, int ts, String src, int line,
            TriComponentId c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCtrlStop(String am, int ts, String src, int line,
            TriComponentId c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCtrlTerminated(String am, int ts, String src, int line,
            TriComponentId c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMSend_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to, Value msgValue,
            Value addrValue, TciStatus encoderFailure, TriMessage msg,
            TriAddress address, TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMSend_m_BC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to, Value msgValue,
            TciStatus encoderFailure, TriMessage msg,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMSend_m_MC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to, Value msgValue,
            TciValueList addrValues, TciStatus encoderFailure, TriMessage msg,
            TriAddressList addresses, TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMSend_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to, Value msgValue,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMSend_c_BC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortIdList to, Value msgValue,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMSend_c_MC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortIdList to, Value msgValue,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMDetected_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId from, TriMessage msg,
            TriAddress address) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMDetected_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId from, Value msgValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMMismatch_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, Value msgValue,
            TciValueTemplate msgTmpl, TciValueDifferenceList diffs,
            Value addrValue, TciValueTemplate addressTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMMismatch_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, Value msgValue,
            TciValueTemplate msgTmpl, TciValueDifferenceList diffs,
            TriComponentId from, TciNonValueTemplate fromTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMReceive_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, Value msgValue,
            TciValueTemplate msgTmpl, Value addrValue,
            TciValueTemplate addressTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMReceive_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, Value msgValue,
            TciValueTemplate msgTmpl, TriComponentId from,
            TciNonValueTemplate fromTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCall_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars,
            Value addrValue, TciStatus encoderFailure,
            TriParameterList triPars, TriAddress address,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCall_m_BC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars,
            TciStatus encoderFailure, TriParameterList triPars,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCall_m_MC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars,
            TciValueList addrValues, TciStatus encoderFailure,
            TriParameterList triPars, TriAddressList addresses,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCall_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCall_c_BC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortIdList to,
            TriSignatureId signature, TciParameterList tciPars,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCall_c_MC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortIdList to,
            TriSignatureId signature, TciParameterList tciPars,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetCallDetected_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId from,
            TriSignatureId signature, TriParameterList triPars,
            TriAddress address) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetCallDetected_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId from,
            TriSignatureId signature, TciParameterList tciPars) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetCallMismatch_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl,
            TciValueDifferenceList diffs, Value addrValue,
            TciValueTemplate addressTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetCallMismatch_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl,
            TciValueDifferenceList diffs, TriComponentId from,
            TciNonValueTemplate fromTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetCall_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl,
            Value addrValue, TciValueTemplate addressTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetCall_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl,
            TriComponentId from, TciNonValueTemplate fromTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrReply_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars,
            Value replValue, Value addrValue, TciStatus encoderFailure,
            TriParameterList triPars, TriParameter repl, TriAddress address,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrReply_m_BC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars,
            Value replValue, TciStatus encoderFailure,
            TriParameterList triPars, TriParameter repl,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrReply_m_MC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars,
            Value replValue, TciValueList addrValues, TciStatus encoderFailure,
            TriParameterList triPars, TriParameter repl,
            TriAddressList addresses, TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrReply_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars,
            Value replValue, TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrReply_c_BC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortIdList to,
            TriSignatureId signature, TciParameterList tciPars,
            Value replValue, TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrReply_c_MC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortIdList to,
            TriSignatureId signature, TciParameterList tciPars,
            Value replValue, TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetReplyDetected_m(String am, int ts, String src,
            int line, TriComponentId c, TriPortId at, TriPortId from,
            TriSignatureId signature, TriParameterList triPars,
            TriParameter repl, TriAddress address) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetReplyDetected_c(String am, int ts, String src,
            int line, TriComponentId c, TriPortId at, TriPortId from,
            TriSignatureId signature, TciParameterList tciPars, Value replValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetReplyMismatch_m(String am, int ts, String src,
            int line, TriComponentId c, TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl,
            Value replValue, TciValueTemplate replyTmpl,
            TciValueDifferenceList diffs, Value addrValue,
            TciValueTemplate addressTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetReplyMismatch_c(String am, int ts, String src,
            int line, TriComponentId c, TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl,
            Value replValue, TciValueTemplate replyTmpl,
            TciValueDifferenceList diffs, TriComponentId from,
            TciNonValueTemplate fromTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetReply_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl,
            Value replValue, TciValueTemplate replyTmpl, Value addrValue,
            TciValueTemplate addressTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrGetReply_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl,
            Value replValue, TciValueTemplate replyTmpl, TriComponentId from,
            TciNonValueTemplate fromTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrRaise_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars, Value excValue,
            Value addrValue, TciStatus encoderFailure, TriException exc,
            TriAddress address, TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrRaise_m_BC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars, Value excValue,
            TciStatus encoderFailure, TriException exc,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrRaise_m_MC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars, Value excValue,
            TciValueList addrValues, TciStatus encoderFailure,
            TriException exc, TriAddressList addresses,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrRaise_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars, Value excValue,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrRaise_c_BC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortIdList to,
            TriSignatureId signature, TciParameterList tciPars, Value excValue,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrRaise_c_MC(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortIdList to,
            TriSignatureId signature, TciParameterList tciPars, Value excValue,
            TriStatus transmissionFailure) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCatchDetected_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId from,
            TriSignatureId signature, TriException exc, TriAddress address) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCatchDetected_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriPortId from,
            TriSignatureId signature, Value excValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCatchMismatch_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature,
            Value excValue, TciValueTemplate excTmpl,
            TciValueDifferenceList diffs, Value addrValue,
            TciValueTemplate addressTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCatchMismatch_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature,
            Value excValue, TciValueTemplate excTmpl,
            TciValueDifferenceList diffs, TriComponentId from,
            TciNonValueTemplate fromTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCatch_m(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature,
            Value excValue, TciValueTemplate excTmpl, Value addrValue,
            TciValueTemplate addressTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCatch_c(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature,
            Value excValue, TciValueTemplate excTmpl, TriComponentId from,
            TciNonValueTemplate fromTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCatchTimeoutDetected(String am, int ts, String src,
            int line, TriComponentId c, TriPortId at, TriSignatureId signature) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPrCatchTimeout(String am, int ts, String src, int line,
            TriComponentId c, TriPortId at, TriSignatureId signature) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCCreate(String am, int ts, String src, int line,
            TriComponentId c, TriComponentId comp, String name, Boolean alive) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCStart(String am, int ts, String src, int line,
            TriComponentId c, TriComponentId comp, TciBehaviourId name,
            TciParameterList tciPars) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCRunning(String am, int ts, String src, int line,
            TriComponentId c, TriComponentId comp, ComponentStatus status) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCAlive(String am, int ts, String src, int line,
            TriComponentId c, TriComponentId comp, ComponentStatus status) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCStop(String am, int ts, String src, int line,
            TriComponentId c, TriComponentId comp) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCKill(String am, int ts, String src, int line,
            TriComponentId c, TriComponentId comp) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCDoneMismatch(String am, int ts, String src, int line,
            TriComponentId c, TriComponentId comp, TciNonValueTemplate compTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCDone(String am, int ts, String src, int line,
            TriComponentId c, TciNonValueTemplate compTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCKilledMismatch(String am, int ts, String src, int line,
            TriComponentId c, TriComponentId comp, TciNonValueTemplate compTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCKilled(String am, int ts, String src, int line,
            TriComponentId c, TciNonValueTemplate compTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCTerminated(String am, int ts, String src, int line,
            TriComponentId c, VerdictValue verdict, String reason) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPConnect(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port1, TriPortId port2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPDisconnect(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port1, TriPortId port2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPMap(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port1, TriPortId port2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPMapParam(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port1, TriPortId port2,
            TciParameterList tciPars, TciStatus encoderFailure,
            TriParameterList triPars) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPUnmap(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port1, TriPortId port2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPUnmapParam(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port1, TriPortId port2,
            TciParameterList tciPars, TciStatus encoderFailure,
            TriParameterList triPars) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPClear(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPStart(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPStop(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPHalt(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliEncode(String am, int ts, String src, int line,
            TriComponentId c, Value val, TciStatus encoderFailure,
            TriMessage msg, String codec) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliDecode(String am, int ts, String src, int line,
            TriComponentId c, TriMessage msg, TciStatus decoderFailure,
            Value val, String codec) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTTimeoutDetected(String am, int ts, String src, int line,
            TriComponentId c, TriTimerId timer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTTimeoutMismatch(String am, int ts, String src, int line,
            TriComponentId c, TriTimerId timer, TciNonValueTemplate timerTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTTimeout(String am, int ts, String src, int line,
            TriComponentId c, TriTimerId timer, TciNonValueTemplate timerTmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTStart(String am, int ts, String src, int line,
            TriComponentId c, TriTimerId timer, TriTimerDuration dur) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTStop(String am, int ts, String src, int line,
            TriComponentId c, TriTimerId timer, TriTimerDuration dur) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTRead(String am, int ts, String src, int line,
            TriComponentId c, TriTimerId timer, TriTimerDuration elapsed) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliTRunning(String am, int ts, String src, int line,
            TriComponentId c, TriTimerId timer, TimerStatus status) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliSEnter(String am, int ts, String src, int line,
            TriComponentId c, QualifiedName name, TciParameterList tciPars,
            String kind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliSLeave(String am, int ts, String src, int line,
            TriComponentId c, QualifiedName name, TciParameterList tciPars,
            Value returnValue, String kind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliVar(String am, int ts, String src, int line,
            TriComponentId c, QualifiedName name, Value varValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliModulePar(String am, int ts, String src, int line,
            TriComponentId c, QualifiedName name, Value parValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliGetVerdict(String am, int ts, String src, int line,
            TriComponentId c, VerdictValue verdict) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliSetVerdict(String am, int ts, String src, int line,
            TriComponentId c, VerdictValue verdict, String reason) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliLog(String am, int ts, String src, int line,
            TriComponentId c, String log) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliAEnter(String am, int ts, String src, int line,
            TriComponentId c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliALeave(String am, int ts, String src, int line,
            TriComponentId c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliADefaults(String am, int ts, String src, int line,
            TriComponentId c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliAActivate(String am, int ts, String src, int line,
            TriComponentId c, QualifiedName name, TciParameterList tciPars,
            Value ref) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliADeactivate(String am, int ts, String src, int line,
            TriComponentId c, Value ref) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliANomatch(String am, int ts, String src, int line,
            TriComponentId c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliARepeat(String am, int ts, String src, int line,
            TriComponentId c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliAWait(String am, int ts, String src, int line,
            TriComponentId c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliAction(String am, int ts, String src, int line,
            TriComponentId c, String action) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMatch(String am, int ts, String src, int line,
            TriComponentId c, Value expr, TciValueTemplate tmpl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliMatchMismatch(String am, int ts, String src, int line,
            TriComponentId c, Value expr, TciValueTemplate tmpl,
            TciValueDifferenceList diffs) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliInfo(String am, int ts, String src, int line,
            TriComponentId c, int level, String info) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliCStaticCreate(String am, int ts, String src, int line,
            TriComponentId c, TriComponentId comp, String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPStaticConnect(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port1, TriPortId port2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliPStaticMap(String am, int ts, String src, int line,
            TriComponentId c, TriPortId port1, TriPortId port2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliConfigStarted(String am, int ts, String src, int line,
            TriComponentId c, TciBehaviourId configId,
            TciParameterList tciPars, Value ref) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tliConfigKilled(String am, int ts, String src, int line,
            TriComponentId c, Value ref) {
        // TODO Auto-generated method stub

    }

}
