// class Gui

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;

@SuppressWarnings("serial")
class Gui extends JPanel {

	static int BREITE = 700;

	static int loc_x = 100;
	static int loc_y = 50;

	static int fensterhoehe = 400; // wirkt!
	static int toolbarhoehe = 50; // wirkt
	static int tafelhoehe = 150;// wirkt
	static int texthoehe = 1;

	public static JTable table;
	// private static JTextField txtfld;
	private static JToolBar toolBar;
	private static JTextArea output;

	static JScrollPane p1;
	static JScrollPane p2;

	private static JLabel text1;
	private static JLabel text2;
	private static JLabel text3;
	private static JLabel text4;

	// private static JLabel status;

	public static void InitGui(final LinkedList<String> suitefiles) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GuiMain(suitefiles);
				ReadLine("end run in InitGui");
				// JValis.prepare(suitefiles);
			}
		});

		ReadLine("end InitGui");

	}

	// PRIVATE

	private Gui(LinkedList<String> suitefiles) {
		super();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		toolBar = MakeToolBar();
		add(toolBar);

		text1 = new JLabel();
		text1.setText(" ");
		add(text1);

		text3 = new JLabel();
		text3.setText(" ");
		add(text3);

		// Status
		text2 = new JLabel();
		text2.setText("");
		add(text2);

		text4 = new JLabel();
		text4.setText(" ");
		add(text4);

		output = MakeTextArea();
		table = MakeTable();

		p1 = new JScrollPane(table);
		p2 = new JScrollPane(output);

		JSplitPane splitPane = MakeSplitPane(p1, p2);

		add(splitPane);

	}

	public static void ShowStatus(String s) {
		text2.setText(Indent + s);
	}

	public static void ShowVerdicts(String s) {
		if (!OT3TESTER.GUI)
			return;
		text3.setText(Indent + s);
	}

	private final static String Indent = "  ";

	public static void SetText1(String str) {
		text1.setText(Indent + str);
	}

	// *** toolbar ***

	private static JToolBar MakeToolBar() {
		JToolBar toolBar = new JToolBar("Still draggable");
		toolBar.setFloatable(false);
		toolBar.setRollover(true);
		addButtons(toolBar);

		toolBar.setPreferredSize(new Dimension(BREITE, toolbarhoehe)); // <===
																		// $$$$$$
		toolBar.setMinimumSize(new Dimension(BREITE, toolbarhoehe)); // <===
																		// $$$$$$
		toolBar.setMaximumSize(new Dimension(BREITE, toolbarhoehe)); // <===
																		// $$$$$$
		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		return toolBar;
	}

	// *** tabelle ***

	private static JTable MakeTable() {
		final JTable table = new JTable(TableModel.Ich);
		int tafelbreite = BREITE;
		int tafelhoehe = 1000; // wirkt nicht!
		table.setPreferredScrollableViewportSize(new Dimension(tafelbreite,
				tafelhoehe));
		table.setFillsViewportHeight(true);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = table.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table.getRowCount()) {
					table.setRowSelectionInterval(r, r);
				} else {
					table.clearSelection();
				}

				// int rowindex = table.getSelectedRow();
				// selectedrow = rowindex; // NOT USED xxxxx

			}
		});

		table.setPreferredSize(new Dimension(BREITE, tafelhoehe)); // <===
																	// $$$$$$
		// table.setMinimumSize(new Dimension(BREITE, tafelhoehe)); // <===
		// $$$$$$
		// table.setMaximumSize(new Dimension(BREITE, tafelhoehe)); // <===
		// $$$$$$

		return table;
	}

	// *** ausgabe ***

	private static JTextArea MakeTextArea() {

		JTextArea output = new JTextArea(BREITE, 2);

		output.setEditable(false);

		output.setPreferredSize(new Dimension(BREITE, texthoehe)); // <===
																	// $$$$$$
		// output.setMinimumSize(new Dimension(BREITE, texthoehe)); // <===
		// $$$$$$
		// output.setMaximumSize(new Dimension(BREITE, texthoehe)); // <===
		// $$$$$$

		return output;
	}

	private static JSplitPane MakeSplitPane(JScrollPane p1, JScrollPane p2) {

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p1, p2);
		splitPane.setOneTouchExpandable(true);

		p1.setPreferredSize(new Dimension(BREITE, tafelhoehe)); // <=== $$$$$$
		// p1.setMinimumSize(new Dimension(BREITE, tafelhoehe)); // <=== $$$$$$
		// p1.setMaximumSize(new Dimension(BREITE, tafelhoehe)); // <=== $$$$$$

		p2.setPreferredSize(new Dimension(BREITE, texthoehe)); // <=== $$$$$$
		// p2.setMinimumSize(new Dimension(BREITE, texthoehe)); // <=== $$$$$$
		// p2.setMaximumSize(new Dimension(BREITE, texthoehe)); // <=== $$$$$$

		splitPane.setAlignmentX(Component.LEFT_ALIGNMENT);

		return splitPane;

	}

	static JFrame fenster;

	private static void GuiMain(LinkedList<String> suitefiles) {

		UIManager.put("swing.boldMetal", Boolean.FALSE);

		JFrame frame = new JFrame("OT3 TESTER");
		fenster = frame;

		// on window closing ...
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				Commands.CommandSaveAndExit();
			}
		});

		Gui newContentPane = new Gui(suitefiles);

		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		newContentPane.setPreferredSize(new Dimension(BREITE, fensterhoehe));

		frame.setLocation(loc_x, loc_y);

		frame.pack();
		frame.setVisible(true);

		// --- end building gui ---

		// JValis.prepare(suitefiles);
		PrepareThread(suitefiles);

		Configure.ReadAdapterFile();
		Configure.ReadCodecFile();

		// Mark.ReadMarkFile();
		// ScrollTable(Mark.GetIndexOfMarkedTest());

		// RefreshTable();

		// ShowStatus("Ready");
		// Gui.Display("Ready");
	}

	static void PrepareThread(final LinkedList<String> suitefiles) {
		new Thread() {
			@Override
			public void run() {

				OT3TESTER.prepare(suitefiles);

				Mark.ReadMarkFile();
				ScrollTable(Mark.GetIndexOfMarkedTest());

				RefreshTable();

				TableModel.ReportVerdicts();

				ShowStatus("Ready");
				Gui.Display("Ready");

			}
		}.start();

	}

	private static void addButtons(JToolBar toolBar) {

		// TESTS
		xAddButton("Run", "Run marked test", "Run", toolBar);
		xAddButton("Run All", "Run checked tests", "Run All", toolBar);

		toolBar.addSeparator(new Dimension(15, 0));

		xAddButton("Stop", "Stop test", "Stop", toolBar);
		xAddButton("Stop All", "Stop all scheduled tests", "Stop All", toolBar);

		toolBar.addSeparator(new Dimension(15, 0));

		// MARK
		xAddButton("Mark", "mark test as current", "Mark", toolBar);

		toolBar.addSeparator(new Dimension(15, 0));

		// CURSOR
		/*
		 * xAddButton("CursorUp", "", "Cursor Up", toolBar);
		 * xAddButton("CursorDown", "", "Cursor Down", toolBar);
		 * 
		 * toolBar.addSeparator(new Dimension(15, 0));
		 */

		// MOVE
		xAddButton("Rauf", "", "Move Up", toolBar);
		xAddButton("Runter", "", "Move Down", toolBar);

		toolBar.addSeparator(new Dimension(15, 0));

		// REFRESH

		// xAddButton("Stop", "", "Stop", toolBar);

		// toolBar.addSeparator(new Dimension(15,0));

		// Clear

		xAddButton("Clear", "Clear Verdicts", "Clear", toolBar);

		toolBar.addSeparator(new Dimension(15, 0));

		// Adapter

		xAddButton("Configure", "Configure environment", "Configure", toolBar);

		toolBar.addSeparator(new Dimension(15, 0));

		// EXIT

		xAddButton("Exit", "", "Exit", toolBar);

	}

	static private void xAddButton(String actionCommand, String toolTipText,
			String altText, JToolBar toolBar) {

		JButton button = new JButton();

		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.setText(altText);
		button.addActionListener(Commands.Ich);// <-

		toolBar.add(button);

	}

	// ===================

	public static void Display(String line) {

		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
		Calendar cal = Calendar.getInstance();
		// System.out.println(dateFormat.format(cal.getTime()));
		String time = dateFormat.format(cal.getTime());

		// System.out.println("|||Display||| '" + time + " " + line + "'");
		String display = time + " " + line;
		// System.out.println(display);

		if (OT3TESTER.GUI) {
			if (output != null) {
				output.append(display + "\n");
				output.setCaretPosition(output.getDocument().getLength());
			}
		} else {
			System.out.println(display);

		}
	}

	public static void RefreshTable() {
		table.revalidate();
		table.repaint();
	}

	public static void ScrollTable(int row) {
		int i = row;
		table.getSelectionModel().setSelectionInterval(i, i);
		table.scrollRectToVisible(new Rectangle(table.getCellRect(i, 0, true)));

	}

	static void ReadLine(String str) {
		boolean flag = false;
		if (flag) {
			System.out.println("Enter something here : " + str);

			try {
				BufferedReader bufferRead = new BufferedReader(
						new InputStreamReader(System.in));
				String s = bufferRead.readLine();
				System.out.println(str + " danke fuer " + s);

				System.out.println(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
