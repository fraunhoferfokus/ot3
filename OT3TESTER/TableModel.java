import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
class TableModel extends AbstractTableModel {

	public static TableModel Ich = new TableModel();

	public TableModel() {
		Ich = this;
		ClearModel();
	}

	private static String[] columnNames = { "", "MODULE", "TESTCASE",
			"VERDICT", "COMMENT", "" };

	final static int MODULE = 1;
	final static int TESTCASE = 2;
	final static int VERDICT = 3;
	final static int RUN = 0;
	final static int DOC = 4;
	final static int SPECIAL = 5;

	public Object[][] thedata = null;
	
	public static int SelectTableIndex(String Module, String ID) {
		int k = -1;
		for (int j = 0; j < TableModel.NRows; j++) {
			Object obj0 = TableModel.Ich.thedata[j][TableModel.MODULE];
			String str0 = (String) obj0;
			Object obj1 = TableModel.Ich.thedata[j][TableModel.TESTCASE];
			String str1 = (String) obj1;
			if (str0.equals(Module) && str1.equals(ID)) {
				k = j;
				break;
			}
		}

		if (k == -1) {
			System.out.println("test not found" + ID);
			GenericRunner.RuntimeError("test not found");
		}
		return k;
	}
	
	public static Test SelectTestWithIndex(int k) {
		Object obj = TableModel.Ich.thedata[k][TableModel.MODULE];
		String module = (String) obj;
		Object obj2 = TableModel.Ich.thedata[k][TableModel.TESTCASE];
		String ID = (String) obj2;
		Test X = null;
		String filename;
		for (Test T : UniSuite.TestList) {
			filename = T.FileName;
			if (T.TestId.equals(ID) && (filename.equals(module))) {
				X = T;

				break;
			}
		}

		return X;

	}

	static void RunSelected() {
		GenericRunner.KillOthers = false;
		for (int k = 0; k < NRows; k++) {
			if (GenericRunner.KillOthers) {
				return;
			}

			if (OT3TESTER.GUI) {
				Gui.ScrollTable(k);
			}

			Object val3 = (Ich.thedata[k][RUN]);
			boolean Selected = (Boolean) val3;

			if (Selected) {
				Test T = SelectTestWithIndex(k);
				TableModel.SetVerdict(k, "RUNNING");
				GenericRunner.RunTest(T);
			}
		}
	}

	public static void ClearVerdicts() {
		for (int k = 0; k < NRows; k++) {
			Ich.thedata[k][VERDICT] = "CLEARED";
		}
		if (OT3TESTER.GUI) {
			Gui.RefreshTable();
		}
		
		ReportVerdicts();
	}

	// VERDICT
	static void SetVerdict(int i, String v) {
		Ich.thedata[i][VERDICT] = v;
		if (OT3TESTER.GUI) {
			Gui.RefreshTable();

		}
		ReportVerdicts();
	}
	
	public static void ReportVerdicts()
	{
		int n_pass = 0;
		int n_fail = 0;
		int n_none = 0;
		int n_error = 0;
		int n_clear = 0;
		for (int k = 0; k < NRows; k++) {
			String v = (String) Ich.thedata[k][VERDICT];
			if (v.equals("PASS")) n_pass++;
			else if (v.equals("FAIL")) n_fail++;
			else if (v.equals("ERROR")) n_error++;
			else if (v.equals("NONE")) n_none++;
			else if (v.equals("CLEARED")) n_clear++;
		}
		String str =
				"PASS " + n_pass + 
				"  FAIL " + n_fail + 
				"  ERROR " + n_error + 
				"  NONE " + n_none +
				"  CLEAR " + n_clear +
				"  TOTAL " + NRows;
		
		Gui.ShowVerdicts(str);
		
	   
	}

	public static void SetVerdict(String Module, String Test, String Verdict) {
		int i = Lookup(Module, Test);
		SetVerdict(i, Verdict);
	}

	private static int Lookup(String Module, String Test) {
		for (int k = 0; k < NRows; k++) {

			if (OT3TESTER.GUI)
				Gui.ScrollTable(k);

			String m = (String) (Ich.thedata[k][MODULE]);
			String t = (String) (Ich.thedata[k][TESTCASE]);
			if (m.equals(Module) && t.equals(Test))
				return k;
		}

		return -1;

	}

	// set: -------------------------------------------------------

	static int NRows = 0;

	public static int NewRow() {
		int k = NRows;
		NRows++;
		return k;
	}

	/*
	 * public static void DefCol() {
	 * 
	 * TableColumn column = null; for (int i = 0; i < columnNames.length; i++) {
	 * column = Gui.table.getColumnModel().getColumn(i); if (i == 0 || i == 1) {
	 * column.setPreferredWidth(10); //third column is bigger } else {
	 * column.setPreferredWidth(50); } }
	 * 
	 * 
	 * }
	 */

	private static void ClearModel() {
		int n = 3600; // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

		Object[][] D = new Object[n][];

		for (int i = 0; i < n; i++) {

			D[i] = new Object[] { "tag-a",
					"tag-b",
					"tag-c", new Boolean(true), "tag-e", "tag-f" };
		}

		// data = newdata;
		Ich.thedata = D;
		NRows = 0;

	}

	// .....................................................................
	public static void Swap(int rowindex, int direction) {
		int COL = columnNames.length - 1;
		String a;
		String b;
		int m = Mark.GetIndexOfMarkedTest();
		if (rowindex == m) {
			a = "  <=";
			b = "   ";
		} else if (rowindex + direction == m) {
			a = "   ";
			b = "  <=";
		} else {
			a = "";
			b = "";
		}

		for (int i = 1; i <= COL; i++) {
			Object tmp = TableModel.Ich.thedata[rowindex][i];
			TableModel.Ich.thedata[rowindex][i] = TableModel.Ich.thedata[rowindex
					+ direction][i];
			TableModel.Ich.thedata[rowindex + direction][i] = tmp;
		}
		TableModel.Ich.thedata[rowindex + direction][TableModel.SPECIAL] = "   "
				+ (rowindex + direction) + a;
		TableModel.Ich.thedata[rowindex][TableModel.SPECIAL] = "   " + rowindex
				+ b;

	}

	// override: -------------------------------------------------------

	// not used
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	// .....................................................................

	// not used
	@Override
	public int getRowCount() {
		// return data.length;
		return NRows;
	}

	// .....................................................................

	// not used
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	// .....................................................................

	// used in getColumnClass, which itself is not used
	@Override
	public Object getValueAt(int row, int col) {
		return thedata[row][col];
	}

	// .....................................................................

	// not used
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	// .....................................................................

	// not used
	@Override
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		return col == RUN || col == DOC;

	}

	// .....................................................................

	// not used
	@Override
	public void setValueAt(Object value, int row, int col) {
		thedata[row][col] = value;
		fireTableCellUpdated(row, col);
	}
	// .....................................................................

} // MyTableModel
