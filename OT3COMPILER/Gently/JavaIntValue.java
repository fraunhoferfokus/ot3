
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class JavaIntValue extends JavaNode
{
   public int Val;

   public JavaIntValue(int i)
   {
      Name = "%int%";
      Val = i;
   }

   public void DumpI(String indent)
   {
      System.out
            .println(indent + "JavaIntValue{\"" + Name + "\", " + Val + "}");
   }

   public static boolean MatchesInt(JavaNode v, int i)
   {
      if (!v.Name.equals("%int%"))
         return false;

      JavaIntValue iv = (JavaIntValue) v;
      return iv.Val == i;
   }

   @Override
   public boolean Eq(JavaNode x)
   {
      boolean res = Val == ((JavaIntValue) x).Val;
      return res;
   }
}
