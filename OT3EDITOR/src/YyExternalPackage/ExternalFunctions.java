package YyExternalPackage;

import Gently.JavaNode;
import Gently.JavaLib;
import Gently.JavaStringValue;
import Gently.GrammarLib;
import java.io.*;

public class ExternalFunctions
{
   public static void Error(JavaNode msg, JavaNode pos)
   {
      JavaStringValue msgval = (JavaStringValue) msg;
      String msgstr = msgval.Str;
      JavaStringValue posval = (JavaStringValue) pos;
      String posstr = posval.Str;
      System.out.println("Line "+posstr+": "+msgstr);
      JavaLib.ErrorMessage(msg, pos);
   }

   public static void OpenJavaTargetFileMeta(JavaNode pkg, JavaNode m)
   {
      JavaStringValue sv_pkg = (JavaStringValue) pkg;
      String str_pkg = sv_pkg.Str;
      JavaStringValue sv_m = (JavaStringValue) m;
      String str_m = sv_m.Str;
      String workdir = ExtractWorkingDirecory();
      String dir = workdir + "/" + str_pkg;
      CreatePackage(dir);
      JavaLib.rtsOpenAlphaFile(dir + "/" + "Meta" + str_m + ".java");
   }

   public static void OpenJavaTargetFile(JavaNode pkg, JavaNode m)
   {
      JavaStringValue sv_pkg = (JavaStringValue) pkg;
      String str_pkg = sv_pkg.Str;
      JavaStringValue sv_m = (JavaStringValue) m;
      String str_m = sv_m.Str;
      String workdir = ExtractWorkingDirecory();
      String dir = workdir + "/" + str_pkg;
      CreatePackage(dir);
      JavaLib.rtsOpenAlphaFile (dir + "/" + str_m + ".java");
   }
   private static String ExtractWorkingDirecory()
   {
      if (GrammarLib.CyanSource == null) return ".";

      System.out.println("***** CyanSource = " + GrammarLib.CyanSource);
      String src = GrammarLib.CyanSource;
      int ind = src.lastIndexOf("/");
      String workdir = src.substring(0,ind);
      System.out.println("********** workdir = " + workdir);
      return workdir;
   }

   public static void CreatePackage(String name)
   {
       //System.out.println("CreatePackage: '" + name + "'");
       //System.out.println("Working Directory = " +
                     //System.getProperty("user.dir"));
       File file = new File(name);
         if (!file.exists()) {
           if (file.mkdir()) {
                //System.out.println("Created!");
              }
               else {
                 JavaLib.CompilerError
                    ("Failed to create directory " + name);
                   }
                    }
                    else {
                //System.out.println("Exists!");
                    }
   }

   public static void RemoveQuotes(JavaNode n)
   {
      JavaStringValue sv = (JavaStringValue) n;
      String str = sv.Str;
      int l = str.length();
      String resstr = str.substring(1,l-1);
      JavaStringValue res = new JavaStringValue(resstr);
      JavaLib.yyoutvalue1 = res; 
   }
}
