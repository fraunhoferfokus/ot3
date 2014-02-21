
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class Print
{

   static boolean quiet = true;

   static int line = 0;

   public static void qq_(int str)
   {
      System.out.print(str + " ");
   }

   public static void qq_(String str)
   {
      System.out.print(str + " ");
   }

   public static void qq(int str)
   {
      System.out.println(str);
   }

   public static void qq(String str)
   {
      System.out.println(str);
   }

   public static void pln(String str)
   {
      if (quiet)
         return;
      line++;
      System.out.println("(" + line + ", " + ThreadInfo() + ")  " + str);
   }

   public static void begin(String str)
   {
      if (quiet)
         return;
      line++;
      System.out.println("(" + line + ", " + ThreadInfo() + ")  { " + str);
   }

   public static void end(String str)
   {
      if (quiet)
         return;
      line++;
      System.out.println("(" + line + ", " + ThreadInfo() + ")  } // " + str);
   }

   public static void err(String str)
   {
      line++;
      System.out.println("(" + line + ")  +++++ " + str + " +++++");
      throw new Error();
   }

   public static void fatal(String str)
   {
      line++;
      System.out.println("(" + line + ") fatal +++++ " + str + " +++++");
      JavaLib.RuntimeError("fatal");
   }

   public static void ensure(boolean condition)
   {
      if (!condition) {
         err("assertion violated");
      }
   }

   static String ThreadInfo()
   {
      Thread t = Thread.currentThread();
      String str = t.getName();
      return str;

   }
}
