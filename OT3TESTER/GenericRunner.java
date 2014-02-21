import java.io.*;
import java.util.*;

import targetcode.Instructions;

class GenericRunner {
	
	public static boolean KillOthers;
	
	public static void CommandStop() {
		if(OT3TESTER.TT) Instructions.KillTestcase();
	}
	
	public static void CommandStopAll() {
		GenericRunner.KillOthers = true;
		
		if(OT3TESTER.TT) Instructions.KillTestcase();
		
	}
	
	
	

	public static void CommandLineRunNamedTest(String Module, String ID) {
		PrvRunNamedTest(Module, ID);
	}

	private static void PrvRunNamedTest(String Module, String ID) {
		
		int k = TableModel.SelectTableIndex(Module, ID);
		Test X = TableModel.SelectTestWithIndex(k);

		TableModel.SetVerdict(k, "RUNNING");
		RunTest(X);

		StatusFile.WriteStatusFile();
	}

	public static void RuntimeError(String str) {
		System.out.println("Runtime error: " + str);
		throw new Error();
	}

	public static void CommandLineRunAllTests() {
		PrvRunAllTests();
	}

	private static void PrvRunAllTests() // all direct
	{
		TableModel.RunSelected();
		StatusFile.WriteStatusFile(); // xxx immer ?
	}
	

	private static boolean RunCommandActive = false;

	public static boolean ForceStop() {
		if (RunCommandActive) {
			Gui.Display("Still running ...");
			return false;
		}
		return true;
	}

	public static void GuiRunAllTests() {
		if (RunCommandActive) {
			Gui.Display("Still running ...");
			return;
		}
		RunCommandActive = true;
		Gui.ShowStatus("Running");
		Gui.Display("Running ...");

		Thread Thr = new Thread() {
			public void run() {

				PrvRunAllTests(); // <===

				Gui.Display("Ready");
				Gui.ShowStatus("Ready");
				RunCommandActive = false;

			}
		};
		Thr.start();
	}

	public static void GuiRunMarkedTest() {
		if (RunCommandActive) {
			Gui.Display("Still running ...");
			return;
		}
		RunCommandActive = true;
		Gui.ShowStatus("Running");
		Gui.Display("Running ...");

		final int k = Mark.GetIndexOfMarkedTest();
		
		final Test X = TableModel.SelectTestWithIndex(k);
		TableModel.SetVerdict(k, "RUNNING");

		new Thread() {
			@Override
			public void run() {

				
				RunTest(X);
				Gui.Display("Ready");
				Gui.ShowStatus("Ready");
				RunCommandActive = false;

			}
		}.start();

	}

	public static void RunTest(Test T) {

		
		if (OT3TESTER.TT) {
			String modname = T.FileName;
			String name = T.TestId;
			TtcnRunner.ExecTest(modname, name);
		} else {
			UniRunner.ExecTest(T);
		}
	}

}
