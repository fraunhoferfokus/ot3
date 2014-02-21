
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class Rule
{
   int Id;
   Nonterm Lhs;
   Symbol[] Rhs;

   public Rule(int id, Nonterm lhs, Symbol[] rhs)
   {
      Id = id;
      Lhs = lhs;
      Rhs = rhs;
   }

   void Print()
   {
      System.out.print(Lhs.Name + " :");
      for (Symbol s : Rhs) {
         System.out.print(" " + s.Name);
      }
      System.out.println();
   }
}
