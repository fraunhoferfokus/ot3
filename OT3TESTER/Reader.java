import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class Reader {
	public static LinkedList<String> Read(String name)
	// read a file in return its content
	// as list of strings
	{
		// System.out.println("Reader.Read.....");
		LinkedList<String> Res = new LinkedList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(name));
			String line = null;
			while ((line = br.readLine()) != null) {
				Res.add(line);
			}
		} catch (IOException ioe) {
			System.out.println("error while reading '" + name +"'");
		}
		try {
			if (br != null)
				br.close();
		} catch (IOException e) {

		}

		return Res;
	}

}
