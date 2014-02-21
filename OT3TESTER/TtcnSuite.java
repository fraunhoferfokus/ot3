import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.JLabel;

import org.etsi.ttcn.tci.TciCDProvided;
import org.etsi.ttcn.tci.TciModuleId;
import org.etsi.ttcn.tci.TciTMRequired;
import org.etsi.ttcn.tci.TciTestCaseId;
import org.etsi.ttcn.tci.TciTestCaseIdList;
import org.etsi.ttcn.tri.TriCommunicationSA;

import targetcode.Instructions;
import targetcode.SystemSetup;
import targetcode.Trace;
import targetcode.XciTMRequiredImpl;
import de.fraunhofer.fokus.ttcn.framework.Framework;
import de.fraunhofer.fokus.ttcn.tci.TciModuleIdImpl;

public class TtcnSuite {
	public static TciTMRequired TMReq = new XciTMRequiredImpl();

	public static void ReadSuite() {

		Instructions.SetLogger(new TtcnLogger());

		PrepareAdapterAndCodec();
		FillTable();
		

	}


	private static void FillTable() {

		UniSuite.ClearSuite();

		ArrayList<String> ModuleList = GetModuleList();

		for (String M : ModuleList) {
			DefRoot(M);

			TciTestCaseIdList List = TMReq.tciGetTestCases(); // <===
			int n = List.size();

			Enumeration<TciTestCaseId> Enum = List.tciGetTestCases();

			Enumeration<TciTestCaseId> enu = Enum;
			for (int i = 0; i < n; i++) {
				TciTestCaseId Entry = enu.nextElement();
				String mn = Entry.getModuleName();
				String bn = Entry.getBaseName();
				Test T = new Test();
				T.FileName = mn;
				T.TestId = bn;

				UniSuite.TestList.add(T);

			}
		}
	}
	
	
	private static ArrayList<String> GetModuleList() {
		ArrayList<String> L = new ArrayList<String>();
		File f = new File(".");
		File[] list = f.listFiles();
		
		
		ArrayList<String> CompArgs = new ArrayList<String>();
		for (File m : list) {
			String mname = m.getName();
			if (mname.indexOf(".ttcn") > 0) {
				//Compiling.Compile(mname);
				
				String module = mname.substring(0, mname.indexOf(".ttcn"));
				CompArgs.add(module);
				
			}
		
		}
		
		// Compiling.CompileAll(CompArgs);
		
		for (File m : list) {
			String mname = m.getName();

			if (m.isDirectory()) {

				File mo = new File(mname + "/MODULE.java");
				if (mo.exists()) {
					L.add(mname);
				}
			}
		}
		
		for (File m : list) {
			String mname = m.getName();

			if (m.isDirectory()) {

				File mo = new File(mname + "/MODULE.java");
				if (mo.exists()) {
					L.add(mname);
				}
			}
		}
		return L;
	}

	public static void PrepareAdapterAndCodec() {

		
		SystemSetup.Setup1();

		String adapter = "Adapter";
		String codec = "Codec";

		adapter = Configure.GetAdapter();
		codec = Configure.GetCodec();

		if (adapter != "") {
			try {
				//Gui.Display("Loading adapter : " + adapter);
				Class<?> kl = Class.forName(adapter);
				Object obj = kl.newInstance();
				TriCommunicationSA Adapter = (TriCommunicationSA) obj;
				Framework.SetTriCommunicationSA(Adapter);
			} catch (Exception e) {
				Gui.Display("Adapter not loaded");
			}
		}

		if (codec != "") {
			try {
				//Gui.Display("Loading codec : " + codec);
				Class<?> kl = Class.forName(codec);
				Object obj = kl.newInstance();
				TciCDProvided Codec = (TciCDProvided) obj;
				Framework.SetTciCDProvided(Codec);
			} catch (Exception e) {
				Gui.Display("Codec not loaded");
			}
		}

		SystemSetup.Setup2();

	}

	public static String CurrentModule = "/nomodule/";

	public static void DefRoot(String Arg_Module) {
		String modulename = Arg_Module;
		String basename = "basename";
		TciModuleId Id = new TciModuleIdImpl(modulename, basename);
		TMReq.tciRootModule(Id);
		CurrentModule = Arg_Module;
	}

}
