//import java.io.*;
import java.util.*;

import static Trace.Trace.*;

class OT3TESTER {

   public static boolean GUI = true;
   public static boolean TT = true;
   public static String Script = "ttscript";
   public static String TheTest = "-";
   public static String TheModule = "-";
   
   //public static boolean EEE = false;

   public static void main(String[] args) {
	   

      // *** args

      int i = 0;
      LinkedList<String> suitefiles = new LinkedList<String>();
      while (i < args.length) {
         if (args[i].equals("-script")) {
            if (i > args.length) {
               System.out.println("missing arg for -script");
               return;
            }
            i++;
            Script = args[i];
            TT = false;
            i++;
         } else if (args[i].equals("-batch")) {
            i++;
            GUI = false;
         } else if (args[i].equals("-module")) {
            if (i > args.length) {
               System.out.println("missing arg for -module");
               return;
            }
            i++;
            TheModule = args[i];
            i++;
            GUI = false;
            
         } else if (args[i].equals("-test")) {
            if (i > args.length) {
               System.out.println("missing arg for -test");
               return;
            }
            i++;
            TheTest = args[i];
            i++;
            GUI = false;
            
         } else if (args[i].equals("-eee")) {
        	 //EEE = true;
            i++;
            
         } else if (args[i].equals("-null")) {
        	 System.out.println("-null");
        	 return;
        	 // to trigger build in Eclipse
            
         } else {

            suitefiles.add(args[i]);
            i++;
         }
      }

      // *** go

      if (! TheTest.equals("-")) {
    	  prepare(suitefiles);
         GenericRunner.CommandLineRunNamedTest(TheModule, TheTest);
      }
      else if (GUI) {
         Gui.InitGui(suitefiles);
      } else {
    	  prepare(suitefiles);
         GenericRunner.CommandLineRunAllTests();

      }
   }
   
   public static void prepare(LinkedList<String> suitefiles)
   {
	      // *** suitefiles

	      if (TT) {
	         TtcnSuite.ReadSuite();

	      } else {
	         UniSuite.ReadSuite(suitefiles);
	      }

	      // *** status

	      StatusFile.ReadStatusFile();
	      
	      // *** model
	      
	      StatusFile.DefineModelData();
	      
   }
   
   


}
// -----------------------------------------------------------------------------

