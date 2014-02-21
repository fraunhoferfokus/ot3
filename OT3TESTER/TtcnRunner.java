import static P.P.*;

import targetcode.Trace;
import targetcode.TestCaseKilled;
import targetcode.MetaModule;
import targetcode.Instructions;


import targetcode.TtcnFatal;


import org.etsi.ttcn.tci.TciParameterList;

public class TtcnRunner {

	/*
	public static void CommandStop() {
		Instructions.KillTestcase();
	}
	
	public static void CommandStopAll() {
		GenericRunner.KillOthers = true;
		
		Instructions.KillTestcase();
		
	}
	*/
	
	private static boolean Executing = false;

	public static void ExecTest(String module, String testCaseId) {
		String Verdict;
                //System.out.println("qq inside ExecTest 111");
		
		if (Executing) {
			Gui.Display("still executing ...");
			return;
		
		}
                //System.out.println("qq inside ExecTest 222");
		
		Executing = true;

		Gui.Display("Execute testcase " + module + "." + testCaseId);

		if (!module.equals(TtcnSuite.CurrentModule)) {
			TtcnSuite.DefRoot(module);
		}

		TciParameterList parameterList = null;

		Trace.Clear();

                //System.out.println("qq inside ExecTest 333");
		try {
			
                //System.out.println("qq inside ExecTest 444");
			TtcnSuite.TMReq.tciStartTestCase(testCaseId, parameterList); // <===
                //System.out.println("qq inside ExecTest 555");
			
			Verdict = Instructions.LastVerdict;
			
		} catch (TestCaseKilled X) {

			Gui.Display("caught TestCaseKilled");
			Verdict = "KILLED";
		} catch (TtcnFatal E) {
			//E.printStackTrace();
			Verdict = "FATAL";

		} catch (Exception E) {
			//E.printStackTrace();
			Verdict = "CRASHED";

		}
                //System.out.println("qq inside ExecTest 666");


		//qq!!!
                Gui.Display("Testcase " + testCaseId + " terminated with verdict " + Verdict);
		//qq!!!
                SetVerdict(Verdict, module, testCaseId);
		
		Executing = false;
                //System.out.println("qq inside ExecTest 999");
	}

	private static void Proc_RunCtl() {
		Trace.print("Starter main started.");

		try {
			String root = "MODULE";
			Class<?> kl = Class.forName(root);
			Object obj = kl.newInstance();
			MetaModule metaabc = (MetaModule) obj;

			Trace.Clear();
			metaabc.RunControlPart();
			// xxx
		} catch (Exception e) {
			Trace.print("oops: " + e);
		}

		Trace.print("Starter main end.");
	}

	private static void SetVerdict(String Verdict, String module,
			String testCaseId) {
		TableModel.SetVerdict(module, testCaseId, Verdict);

	}
}
