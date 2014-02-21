
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class EarleyRuntime
{
   JavaNode[] Stack = new JavaNode[5000]; // xxx
   private int SP = -1;
   JavaNode SemanticValue;
   public int LengthOfCurrentRule;

   public void ClearStack()
   {
      SP = -1;
   }

   public LexerState EgonGetLexerState()
   {
      return TokenStream.GetEgon();
   }

   void PrintStack()
   {
      System.out.println("<<<<<<");
      for (int i = 0; i <= SP; i++) {
         System.out.print("   " + i + ": ");
         JavaLib.PrintNode(Stack[i]);
      }
      System.out.println(">>>>>>");
   }

   public void Push(JavaNode n)
   {
      SP++;
      Stack[SP] = n;
   }

   public JavaNode Dollar1()
   {
      return EarleyStack(1);
   }

   public JavaNode Dollar2()
   {
      return EarleyStack(2);
   }

   public JavaNode DollarI(int i)
   {
      return EarleyStack(i);
   }

   public void DefDollarDollar(JavaNode n)
   {
      SemanticValue = n;
   }

   public JavaNode EarleyStack(int i)
   {
      int adapted = SP - LengthOfCurrentRule + i;
      JavaNode n = Stack[adapted];
      return n;
   }

   public void Pop(int n)
   {
      SP = SP - n;
   }
}
