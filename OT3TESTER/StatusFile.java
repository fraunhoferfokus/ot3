import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//import java.util.LinkedList;

public class StatusFile {

	public static void ReadStatusFile() {
		try {
			LoadStatus();
		} catch (Exception e) {

		}
	}

	public static void DefineModelData() {
		for (Test T : UniSuite.TestList) {
			String id = T.TestId;
			String modid = T.FileName;
			boolean found = false;
			for (int i = 0; i < TableModel.NRows; i++) {
                          if (TableModel.Ich.thedata[i][TableModel.MODULE].equals(modid)
						&& TableModel.Ich.thedata[i][TableModel.TESTCASE].equals(id)) {
					found = true;
					break;

				}
			}

			if (!found) {
				Entry1(T);
			}

		}

	}

	// Entry0 : found in _status
	// (Suite not yet read)
	private static void Entry0(String module, String id, String verd, String sel, String doc) {
		int k = TableModel.NewRow();

		TableModel.Ich.thedata[k][TableModel.MODULE] = module;
		TableModel.Ich.thedata[k][TableModel.TESTCASE] = id;
		TableModel.Ich.thedata[k][TableModel.VERDICT] = verd;
		TableModel.Ich.thedata[k][TableModel.RUN] = (sel.equals("true"));

		TableModel.Ich.thedata[k][TableModel.DOC] = doc;

		TableModel.Ich.thedata[k][TableModel.SPECIAL] = "";
	}

	// Entry 1 : found in Suite, not mentioned in _status
	// xxx found in Suite, already present in status : old entry wins
	private static void Entry1(Test T) {
		int k = TableModel.NewRow();
		String module = T.FileName;
		String id = T.TestId;
		TableModel.Ich.thedata[k][TableModel.MODULE] = module;
		TableModel.Ich.thedata[k][TableModel.TESTCASE] = id;
		TableModel.Ich.thedata[k][TableModel.VERDICT] = "-";
		TableModel.Ich.thedata[k][TableModel.RUN] = true;

		Section docsection = T.GetSection("doc");
		String doc;
		if (docsection != null) {
			doc = docsection.Param;
		} else {
			doc = "-";
		}

		TableModel.Ich.thedata[k][TableModel.DOC] = doc;
		TableModel.Ich.thedata[k][TableModel.SPECIAL] = "";
	}

	public static void WriteStatusFile() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("_status"));

			for (int k = 0; k < TableModel.NRows; k++) {
				String moduleid = (String) (TableModel.Ich.thedata[k][TableModel.MODULE]);
				String id = (String) (TableModel.Ich.thedata[k][TableModel.TESTCASE]);
				String verd = (String) (TableModel.Ich.thedata[k][TableModel.VERDICT]);
				String sel = "" + (Boolean) (TableModel.Ich.thedata[k][TableModel.RUN]);
				String doc = (String) (TableModel.Ich.thedata[k][TableModel.DOC]);
				String special = (String) (TableModel.Ich.thedata[k][TableModel.SPECIAL]);
				String line = moduleid + "#" + id + "#" + verd + "#" + sel
						+ "#" + doc + "#" + special;
				pw.println(line);
			}

			pw.flush();
			pw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// PRIVATE
	// ===========================================================================

	private static void LoadStatus() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("_status"));
		} catch (Exception E) {
			Gui.Display("no _status");
			return;
		}
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				ProcessStatusLine(line);
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static void ProcessStatusLine(String line) {

		String[] fields = line.split("#");

		// id#verd#sel#doc

		String module = fields[0];
		String id = fields[1];
		String verd = fields[2];
		String sel = fields[3];
		String doc = fields[4];

		Entry0(module, id, verd, sel, doc);

		// }
	}

}
