import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Mark {
   
   private static String MarkedTest = "";
   private static String MarkedModule = "";
   
   
public static void ReadMarkFile() {
	BufferedReader br = null;
      
      try {
         br = new BufferedReader(new FileReader("_marked"));
      }
      catch (Exception E) {
    	  return;
      }
      try {
         String line = null;
         while ((line = br.readLine()) != null) {
        	 String[] fields = line.split("#");
            MarkedModule = fields[0];
            MarkedTest = fields[1];
         }
         br.close();
      } catch (Exception E) {
    	  return;
      }
      
      int i = GetIndexOfMarkedTest();
      MarkTestWithIndex(i);
   }
   
   public static void MarkTestWithIndex(int i)
   {
	   if (i == -1) {
		   return;
	   }
      for (int j = 0; j < TableModel.NRows; j++) {
         TableModel.Ich.thedata[j][TableModel.SPECIAL] = "   "+j + "   ";
         
      }
      TableModel.Ich.thedata[i][TableModel.SPECIAL] = "   " + i + "   <=";
      Gui.RefreshTable();
      MarkedModule = (String) TableModel.Ich.thedata[i][TableModel.MODULE];
      MarkedTest = (String) TableModel.Ich.thedata[i][TableModel.TESTCASE];
      WriteMarkFile();
      
      Gui.SetText1("Marked: " + MarkedModule + "." + MarkedTest);
   }
   
   public static int GetIndexOfMarkedTest()
   {
      for (int j = 0; j < TableModel.NRows; j++) {
         if (
        		 TableModel.Ich.thedata[j][TableModel.MODULE].equals(MarkedModule) &&
        		 TableModel.Ich.thedata[j][TableModel.TESTCASE].equals(MarkedTest)
        		 ) {
         return j;
         }
         
      }
      return -1;
      
   }
   
   
   public static void WriteMarkFile() {
      try {
         PrintWriter pw = new PrintWriter(new FileWriter("_marked"));
         
         
         pw.println(MarkedModule+"#"+MarkedTest);


         pw.flush();
         pw.close();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }
   }

}
