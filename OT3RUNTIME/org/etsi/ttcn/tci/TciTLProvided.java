// TCI-TL
// TE, TM,CH,CD, SA,PA -> TL
package org.etsi.ttcn.tci;
import org.etsi.ttcn.tri.*;
/**
 * This interface specifies the operations the TL shall provide to the TE.
 * The TL presents all the information provided in the parameters of the operations to the user, how
 * this is done is not defined in this interface.
 */
public interface TciTLProvided {
    public void tliTcExecute(String am, int ts, String src, int line, TriComponentId c,
            TciTestCaseId tcId, TriParameterList triPars, TriTimerDuration dur);
    public void tliTcStart(String am, int ts, String src, int line, TriComponentId c,
            TciTestCaseId tcId, TciParameterList tciPars, TriTimerDuration dur);
    public void tliTcStop(String am, int ts, String src, int line, TriComponentId c, String reason);
    public void tliTcStarted(String am, int ts, String src, int line, TriComponentId c,
            TciTestCaseId tcId, TciParameterList tciPars, TriTimerDuration dur);
    public void tliTcTerminated(String am, int ts, String src, int line, TriComponentId c,
            TciTestCaseId tcId, TciParameterList tciPars, VerdictValue verdict, String reason);
    public void tliCtrlStart(String am, int ts, String src, int line, TriComponentId c);
    public void tliCtrlStop(String am, int ts, String src, int line, TriComponentId c);
    public void tliCtrlTerminated(String am, int ts, String src, int line, TriComponentId c);
    public void tliMSend_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, Value msgValue, Value addrValue,
            TciStatus encoderFailure, TriMessage msg, TriAddress address,
            TriStatus transmissionFailure);
    public void tliMSend_m_BC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, Value msgValue, //%% ',' added
            TciStatus encoderFailure, TriMessage msg, TriStatus transmissionFailure) ;
    public void tliMSend_m_MC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, Value msgValue, TciValueList addrValues,
            TciStatus encoderFailure, TriMessage msg, TriAddressList addresses,
            TriStatus transmissionFailure);
    public void tliMSend_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, Value msgValue, TriStatus transmissionFailure);
    public void tliMSend_c_BC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortIdList to, Value msgValue, TriStatus transmissionFailure);
    public void tliMSend_c_MC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortIdList to, Value msgValue, TriStatus transmissionFailure);
    public void tliMDetected_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId from, TriMessage msg, TriAddress address);
    public void tliMDetected_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId from, Value msgValue );
    public void tliMMismatch_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, Value msgValue, TciValueTemplate msgTmpl, TciValueDifferenceList diffs,
            Value addrValue, TciValueTemplate addressTmpl);
    public void tliMMismatch_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, Value msgValue, TciValueTemplate msgTmpl, TciValueDifferenceList diffs,
            TriComponentId from, TciNonValueTemplate fromTmpl);
    public void tliMReceive_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, Value msgValue, TciValueTemplate msgTmpl,
            Value addrValue, TciValueTemplate addressTmpl);
    public void tliMReceive_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, Value msgValue, TciValueTemplate msgTmpl,
            TriComponentId from, TciNonValueTemplate fromTmpl);
    public void tliPrCall_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, TriSignatureId signature, TciParameterList tciPars,
            Value addrValue, TciStatus encoderFailure, TriParameterList triPars,
            TriAddress address, TriStatus transmissionFailure);
    public void tliPrCall_m_BC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, TriSignatureId signature, TciParameterList tciPars,
            TciStatus encoderFailure, TriParameterList triPars,
            TriStatus transmissionFailure);
    public void tliPrCall_m_MC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, TriSignatureId signature, TciParameterList tciPars,
            TciValueList addrValues, TciStatus encoderFailure, TriParameterList triPars,
            TriAddressList addresses, TriStatus transmissionFailure);
    public void tliPrCall_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, TriSignatureId signature, TciParameterList tciPars,
            TriStatus transmissionFailure);
    public void tliPrCall_c_BC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortIdList to, TriSignatureId signature, TciParameterList tciPars,
            TriStatus transmissionFailure);
    public void tliPrCall_c_MC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortIdList to, TriSignatureId signature, TciParameterList tciPars,
            TriStatus transmissionFailure);
    public void tliPrGetCallDetected_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId from, TriSignatureId signature, TriParameterList triPars,
            TriAddress address);
    public void tliPrGetCallDetected_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId from, TriSignatureId signature, TciParameterList tciPars );
    public void tliPrGetCallMismatch_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl, TciValueDifferenceList diffs,
            Value addrValue, TciValueTemplate addressTmpl);
    public void tliPrGetCallMismatch_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl, TciValueDifferenceList diffs,
            TriComponentId from, TciNonValueTemplate fromTmpl);
    public void tliPrGetCall_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl,
            Value addrValue, TciValueTemplate addressTmpl);
    public void tliPrGetCall_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, TciValueTemplate parsTmpl,
            TriComponentId from, TciNonValueTemplate fromTmpl);
    public void tliPrReply_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, TriSignatureId signature, TciParameterList tciPars,
            Value replValue, Value addrValue,
            TciStatus encoderFailure, TriParameterList triPars,
            TriParameter repl, TriAddress address, TriStatus transmissionFailure);
    public void tliPrReply_m_BC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, TriSignatureId signature, TciParameterList tciPars,
            Value replValue, TciStatus encoderFailure,
            TriParameterList triPars, TriParameter repl, TriStatus transmissionFailure);
    public void tliPrReply_m_MC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, TriSignatureId signature, TciParameterList tciPars,
            Value replValue, TciValueList addrValues,
            TciStatus encoderFailure, TriParameterList triPars,
            TriParameter repl, TriAddressList addresses, TriStatus transmissionFailure);
    public void tliPrReply_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, TriSignatureId signature,
            TciParameterList tciPars, Value replValue,
            TriStatus transmissionFailure);
    public void tliPrReply_c_BC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortIdList to, TriSignatureId signature, TciParameterList tciPars,
            Value replValue, TriStatus transmissionFailure);
    public void tliPrReply_c_MC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortIdList to, TriSignatureId signature, TciParameterList tciPars,
            Value replValue, TriStatus transmissionFailure);
    public void tliPrGetReplyDetected_m(String am, int ts, String src, int line, TriComponentId c,
            // TriPortId at, TriPortId from, TriSignatureId signature, in TriParameterListType triPars,
            TriPortId at, TriPortId from, TriSignatureId signature, TriParameterList triPars,
            TriParameter repl, TriAddress address);
    public void tliPrGetReplyDetected_c(String am, int ts, String src, int line, TriComponentId c,
            // TriPortId at, TriPortId from, TriSignatureId signature, in TciParameterList tciPars,
            TriPortId at, TriPortId from, TriSignatureId signature, TciParameterList tciPars,
            Value replValue);
    public void tliPrGetReplyMismatch_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, /* in */ TciValueTemplate parsTmpl,
            Value replValue, TciValueTemplate replyTmpl, TciValueDifferenceList diffs,
            Value addrValue, TciValueTemplate addressTmpl);
    public void tliPrGetReplyMismatch_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, /* in */ TciValueTemplate parsTmpl,
            Value replValue, TciValueTemplate replyTmpl, TciValueDifferenceList diffs,
            TriComponentId from, TciNonValueTemplate fromTmpl);
    public void tliPrGetReply_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, /* in */ TciValueTemplate parsTmpl,
            Value replValue, TciValueTemplate replyTmpl,
            Value addrValue, TciValueTemplate addressTmpl);
    public void tliPrGetReply_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            TciParameterList tciPars, /* in */ TciValueTemplate parsTmpl,
            Value replValue, TciValueTemplate replyTmpl,
            TriComponentId from, TciNonValueTemplate fromTmpl);
    public void tliPrRaise_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars, Value excValue,
            Value addrValue, TciStatus encoderFailure, TriException exc,
            TriAddress address, TriStatus transmissionFailure);
    public void tliPrRaise_m_BC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars, Value excValue,
            TciStatus encoderFailure, TriException exc, TriStatus transmissionFailure) ;
    public void tliPrRaise_m_MC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to,
            TriSignatureId signature, TciParameterList tciPars, Value excValue,
            TciValueList addrValues, TciStatus encoderFailure, TriException exc,
            TriAddressList addresses, TriStatus transmissionFailure);
    public void tliPrRaise_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId to, TriSignatureId signature,
            TciParameterList tciPars, Value excValue, TriStatus transmissionFailure);
    public void tliPrRaise_c_BC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortIdList to, TriSignatureId signature, TciParameterList tciPars,
            Value excValue, TriStatus transmissionFailure);
    public void tliPrRaise_c_MC(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortIdList to, TriSignatureId signature, TciParameterList tciPars,
            Value excValue, TriStatus transmissionFailure);
    public void tliPrCatchDetected_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId from, TriSignatureId signature,
            TriException exc, TriAddress address);
    public void tliPrCatchDetected_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriPortId from, TriSignatureId signature,
            Value excValue);
    public void tliPrCatchMismatch_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            Value excValue, TciValueTemplate excTmpl, TciValueDifferenceList diffs,
            Value addrValue, TciValueTemplate addressTmpl);
    public void tliPrCatchMismatch_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            Value excValue, TciValueTemplate excTmpl, TciValueDifferenceList diffs,
            TriComponentId from, TciNonValueTemplate fromTmpl);
    public void tliPrCatch_m(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            Value excValue, TciValueTemplate excTmpl,
            Value addrValue, TciValueTemplate addressTmpl);
    public void tliPrCatch_c(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature,
            Value excValue, TciValueTemplate excTmpl,
            TriComponentId from, TciNonValueTemplate fromTmpl);
    public void tliPrCatchTimeoutDetected(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature);
    public void tliPrCatchTimeout(String am, int ts, String src, int line, TriComponentId c,
            TriPortId at, TriSignatureId signature);
    public void tliCCreate(String am, int ts, String src, int line, TriComponentId c,
            TriComponentId comp, String name, Boolean alive);
    public void tliCStart(String am, int ts, String src, int line, TriComponentId c,
            TriComponentId comp, TciBehaviourId name, TciParameterList tciPars);
    public void tliCRunning(String am, int ts, String src, int line, TriComponentId c,
            TriComponentId comp, ComponentStatus status);
    public void tliCAlive(String am, int ts, String src, int line, TriComponentId c,
            TriComponentId comp, ComponentStatus status);
    public void tliCStop(String am, int ts, String src, int line, TriComponentId c,
            TriComponentId comp);
    public void tliCKill(String am, int ts, String src, int line, TriComponentId c,
            TriComponentId comp);
    public void tliCDoneMismatch(String am, int ts, String src, int line, TriComponentId c,
            TriComponentId comp, TciNonValueTemplate compTmpl);
    public void tliCDone(String am, int ts, String src, int line, TriComponentId c,
            TciNonValueTemplate compTmpl);
    public void tliCKilledMismatch(String am, int ts, String src, int line, TriComponentId c,
            TriComponentId comp, TciNonValueTemplate compTmpl);
    public void tliCKilled(String am, int ts, String src, int line, TriComponentId c,
            TciNonValueTemplate compTmpl);
    public void tliCTerminated(String am, int ts, String src, int line, TriComponentId c,
            VerdictValue verdict, String reason);
    public void tliPConnect(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port1, TriPortId port2);
    public void tliPDisconnect(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port1, TriPortId port2);
    public void tliPMap(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port1, TriPortId port2);
    public void tliPMapParam(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port1, TriPortId port2, TciParameterList tciPars,
            TciStatus encoderFailure, TriParameterList triPars);
    public void tliPUnmap(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port1, TriPortId port2);
    public void tliPUnmapParam(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port1, TriPortId port2, TciParameterList tciPars,
            TciStatus encoderFailure, TriParameterList triPars);
    public void tliPClear(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port);
    public void tliPStart(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port);
    public void tliPStop(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port);
    public void tliPHalt(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port);
    public void tliEncode(String am, int ts, String src, int line, TriComponentId c,
            Value val, TciStatus encoderFailure, TriMessage msg, String codec);
    public void tliDecode(String am, int ts, String src, int line, TriComponentId c,
            TriMessage msg, TciStatus decoderFailure, Value val, String codec);
    public void tliTTimeoutDetected(String am, int ts, String src, int line, TriComponentId c,
            TriTimerId timer);
    public void tliTTimeoutMismatch(String am, int ts, String src, int line, TriComponentId c,
            TriTimerId timer, TciNonValueTemplate timerTmpl);
    public void tliTTimeout(String am, int ts, String src, int line, TriComponentId c,
            TriTimerId timer, TciNonValueTemplate timerTmpl);
    public void tliTStart(String am, int ts, String src, int line, TriComponentId c,
            TriTimerId timer, TriTimerDuration dur);
    public void tliTStop(String am, int ts, String src, int line, TriComponentId c,
            TriTimerId timer, /* in */ TriTimerDuration dur);
    public void tliTRead(String am, int ts, String src, int line, TriComponentId c,
            TriTimerId timer, TriTimerDuration elapsed);
    public void tliTRunning(String am, int ts, String src, int line, TriComponentId c,
            TriTimerId timer, TimerStatus status);
    public void tliSEnter(String am, int ts, String src, int line, TriComponentId c,
            QualifiedName name, TciParameterList tciPars, String kind);
    public void tliSLeave(String am, int ts, String src, int line, TriComponentId c,
            QualifiedName name, TciParameterList tciPars, Value returnValue, String kind);
    public void tliVar(String am, int ts, String src, int line, TriComponentId c,
            QualifiedName name, Value varValue);
    public void tliModulePar(String am, int ts, String src, int line, TriComponentId c,
            QualifiedName name, Value parValue);
    public void tliGetVerdict(String am, int ts, String src, int line, TriComponentId c,
            VerdictValue verdict);
    public void tliSetVerdict(String am, int ts, String src, int line, TriComponentId c,
            VerdictValue verdict, String reason);
    /**
     * Shall be called by TM or TE to log the TTCN-3 statement log. This event occurs after the TTCN-3
     * log operation.
     * @param am An additional message.
     * @param ts The time when the event is produced.
     * @param src The source file of the test specification.
     * @param line The line number where the request is performed.
     * @param c The component which produces this event.
     * @param log The string to be logged.
     */
    public void tliLog(String am, int ts, String src, int line, TriComponentId c,
            //%% Tstring log);
            String log);
    public void tliAEnter(String am, int ts, String src, int line, TriComponentId c);
    public void tliALeave(String am, int ts, String src, int line, TriComponentId c);
    public void tliADefaults(String am, int ts, String src, int line, TriComponentId c);
    public void tliAActivate(String am, int ts, String src, int line, TriComponentId c,
            QualifiedName name, TciParameterList tciPars, Value ref);
    public void tliADeactivate(String am, int ts, String src, int line, TriComponentId c,
            Value ref);
    public void tliANomatch(String am, int ts, String src, int line, TriComponentId c);
    public void tliARepeat(String am, int ts, String src, int line, TriComponentId c);
    public void tliAWait(String am, int ts, String src, int line, TriComponentId c);
    public void tliAction(String am, int ts, String src, int line, TriComponentId c, String action);
    public void tliMatch(String am, int ts, String src, int line, TriComponentId c, Value expr,
            TciValueTemplate tmpl);
    public void tliMatchMismatch(String am, int ts, String src, int line, TriComponentId c,
            Value expr, TciValueTemplate tmpl, TciValueDifferenceList diffs);
    // public void tliInfo (String am, int ts, String src, int line, TriComponent c,
    public void tliInfo (String am, int ts, String src, int line, TriComponentId c,
            int level, String info); // ';' added
    //%% Extensions:
    public void tliCStaticCreate(String am, int ts, String src, int line, TriComponentId c,
            TriComponentId comp, String name);
    public void tliPStaticConnect(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port1, TriPortId port2);
    public void tliPStaticMap(String am, int ts, String src, int line, TriComponentId c,
            TriPortId port1, TriPortId port2);
    public void tliConfigStarted (String am, int ts, String src, int line, TriComponentId c,
            TciBehaviourId configId, TciParameterList tciPars, Value ref);
    public void tliConfigKilled (String am, int ts, String src, int line, TriComponentId c,
            Value ref);
}

