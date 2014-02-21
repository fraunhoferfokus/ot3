import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;


public class UniRunner {
   public static void ExecTest(Test T)
   {
      //ValisRunner.Verdict = "Crashed";
      DeleteDir.WegDamit("loeschmich");
      File dir = MakeDir.MkDir("loeschmich");
      for (Section S : T.Sections) {
         WriteSection(S);
      }
      Exec.Do(dir);
      CheckResults(T);
   }
   
   private static void WriteSection(Section S) {
      if (!S.Title.equals("input"))
         return;
      if (S.Param.equals(""))
         return;
      String name = "loeschmich/" + S.Param;
      try {
         PrintWriter pw = new PrintWriter(new FileWriter(name));

         for (String line : S.Lines) {
            pw.println(line);
         }
         pw.flush();
         pw.close();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }
   }

   private static void CheckResults(Test T) {
	   String module = T.FileName;
	      String testCaseId = T.TestId;
	   
      String FileExpected = null;
      LinkedList<String> Expected = null;
      for (Section S : T.Sections) {
         if (S.Title.equals("output")) {
            FileExpected = S.Param;
            Expected = S.Lines;
         }
      }
      if (Expected == null) {
         System.out.println("no output section");
      }

      LinkedList<String> Res = Reader.Read("loeschmich/" + FileExpected);
      for (Section S : T.Sections) {
         if (S.Title.equals("output")) {
            Expected = S.Lines;
         }
      }
      Iterator<String> i1exp = Expected.iterator();
      Iterator<String> i2res = Res.iterator();
      int ln = 0;
      while (i1exp.hasNext()) {
         ln++;
         if (i2res.hasNext()) {
         } else {
            Gui.Display("%%% zu wenig produced, expected: " + ln
                  + "'" + i1exp.next() + "'");
            //ValisRunner.Verdict = "Failed";
            // xx
            TableModel.SetVerdict(module, testCaseId,"FAIL");
            return;
         }
         String s1exp = i1exp.next();
         String s2res = i2res.next();
         if (s1exp.equals(s2res)) {
         } else {
            Gui.Display("%%% lines differ: " + ln);
            Gui.Display("%%% expected: '" + s1exp + "'");
            Gui.Display("%%% produced: '" + s2res + "'");
            //ValisRunner.Verdict = "Failed";
            TableModel.SetVerdict(module, testCaseId,"FAIL");
            return;
         }
      }
      if (i2res.hasNext()) {
         Gui.Display("%%% zu viel produced, expected: " + ln);
         Gui.Display("%%% produced: '" + i2res.next() + "'");
         //ValisRunner.Verdict = "Failed";
         TableModel.SetVerdict(module, testCaseId,"FAIL");
         
         return;
      }
      Gui.Display("%%% geht doch");
      //ValisRunner.Verdict = "Passed";
      
      
      TableModel.SetVerdict(module, testCaseId,"PASS");

   }

}
