import java.io.*;
import java.util.*;

class UniSuite {
	public static LinkedList<Test> TestList = new LinkedList<Test>();
	
	public static void ClearSuite()
	{
		TestList = new LinkedList<Test>();
	}

	public static void ReadSuite(LinkedList<String> suitefiles) {
		for (String name : suitefiles) {
			UniSuite.ReadSuiteFile(name);
		}
	}
	
	// PRIVATE
		// =========================================================================

	private static void ReadSuiteFile(String filename) {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = null;
			while ((line = br.readLine()) != null) {
				ProcessLine(filename, line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
			}
		}
	}

	

	private static void ProcessLine(String filename, String line) {
		if (line.length() >= 1 && line.charAt(0) == '@') {
			if (line.length() >= 2 && line.charAt(1) == '-') {
				return;
			}
			int a = 1;
			while (a < line.length() && Character.isLetter(line.charAt(a))) {
				a++;
			}
			String title = line.substring(1, a);

			while (a < line.length() && line.charAt(a) == ' ') {
				a++;
			}

			int b = line.length() - 1;
			while (b > a && line.charAt(b) == ' ') {
				b--;
			}
			// "'");
			String param = line.substring(a, b + 1);

			if (title.equals("test")) {
				BeginTest(filename, param);
			} else {
				BeginSection(title, param);
			}
		} else {
			AddLine(line);
		}
	}

	private static Test CurrentTest = null;

	private static void BeginTest(String filename, String param) {
		CurrentTest = new Test();
		CurrentTest.FileName = filename;
		CurrentTest.TestId = param;
		TestList.add(CurrentTest);
	}

	private static Section CurrentSection = null;

	private static void BeginSection(String title, String param) {
		CurrentSection = new Section();
		CurrentSection.Title = title;
		CurrentSection.Param = param;
		CurrentTest.Sections.add(CurrentSection);
	}

	private static void AddLine(String line) {
		CurrentSection.Lines.add(line);
	}

}

// -----------------------------------------------------------------------------

