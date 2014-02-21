
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class Program
{

   public static void main(String argv[])
   {
      Gently.JavaLib.SaveProgramArguments(argv);
      try {
         Generated.YyProcs.Start();
      } catch (ReportedError X) {
         X.printStackTrace();
         System.exit(-1);
      } catch (Error X) {
         X.printStackTrace();
         System.exit(-1);
      }
   }
}
