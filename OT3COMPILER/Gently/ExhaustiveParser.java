
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

import Generated.EGrammar;
import Generated.Yylex;

import java.util.HashSet;

public class ExhaustiveParser
{
   public static Symbol UserRoot;

   private static EarleyRuntime RT;

   private ItSet CurItSet;
   private ItSet PrevItSet;
   private boolean Change;
   private int CurIndex;

   private Nonterm Root;
   private Token Eof;

   // ---------------------------------------------------------------------------
   private static boolean Vorher = false;

   public void Matcher(Nonterm nt, EarleyRuntime rt)
   {

      TokenStream.MarkStart();
      DiesItem = null;
      DasItem = null;

      try {
         RT = rt;

         DefineRootRule();

         CurItSet = new ItSet(-1);
         InitialKernel(nt);
         Closure();

         for (CurIndex = 0;; CurIndex++) {
            PrevItSet = CurItSet;
            CurItSet = new ItSet(CurIndex);

            Kernel();

            if (CurItSet.LastIndex == -1) {
               break;

            }

            Closure();

            if (TokenStream.Aktuelles() == -1) { // Matcher
               // EOF
               break;
            }
         }

         CurItSet = PrevItSet;
         UNPARSE();
         if (Vorher)
            TokenStream.BackToSuccess();

      } catch (ErrorReportedByParser e) {
         throw e;
      } catch (Exception e) {
         System.out.println(e);
         e.printStackTrace();
         JavaLib.RuntimeError("Error in Matcher");
      }

   }

   // ........................................................................

   private static Profile ProfSequence = new Profile("Sequence");

   private void Sequence()
   {

      ProfSequence.Start();

      CurItSet = new ItSet(-1);
      InitialKernel();
      Closure();

      for (CurIndex = 0;; CurIndex++) {
         PrevItSet = CurItSet;
         CurItSet = new ItSet(CurIndex);
         Kernel();
         if (CurItSet.LastIndex == -1) {
            // Empty Kernel: syntax error

            TokenStream.SyntaxError();

            res_ast = null;

            ProfSequence.Stop();
            return;

         }

         Closure();

         if (TokenStream.Aktuelles() == -1) { // Sequence
            // EOF
            break;
         }
      }

      ProfSequence.Stop();
   }

   // ---------------------------------------------------------------------------
   private static Profile ProfUNPARSE1 = new Profile("UNPARSE1");

   private void UNPARSE1()
   {
      ProfUNPARSE1.Start();

      Item FinalItem = CurItSet.Items[0];
      TokenStream.ResetPtr();

      TRUEUNPARSE(FinalItem);

      ProfUNPARSE1.Stop();
   }

   private void TRUEUNPARSE(Item FinalItem)
   {
      try {
         Unparse(FinalItem);
         res_ast = RT.SemanticValue;

      } catch (StackOverflowError exc) {
         Stopp("Stack zu klein");
      }
   }

   // ---------------------------------------------------------------------------
   private void UNPARSE()
   {

      Item EofItem;

      if (DiesItem == null) {
         TokenStream.SyntaxError();
         EofItem = null;
      } else {
         if (DasItem == null) {
            EofItem = DiesItem;
            Vorher = true;
         } else {
            EofItem = DasItem;
            Vorher = false;
         }
      }

      TokenStream.BackToStart();

      TRUEUNPARSE(EofItem);
   }

   private void DefineGrammar()
   {
      EGrammar.DefineGrammar();
      if (OPTIMIZE) {
         Llana.AnalizeGrammar();
      }

      DefineRootRule();
   }

   private void DefineRootRule()
   {
      Root = new Nonterm("Root");
      Eof = new Token("Eof", -1);

      Root.Rules = new Rule[] { new Rule(-999, Root, new Symbol[] { UserRoot,
            Eof }) };
   }

   // ---------------------------------------------------------------------------

   private void InitialKernel(Symbol nt)
   {
      Item It = new Item(Root.Rules[0], 0, CurItSet, null, null);
      Root.Rules[0].Rhs[0] = nt;
      CurItSet.Add(It);
   }

   private void InitialKernel()
   {
      Item It = new Item(Root.Rules[0], 0, CurItSet, null, null);
      CurItSet.Add(It);
   }

   // ---------------------------------------------------------------------------

   private Item DasItem = null;
   private Item DiesItem = null;

   private static Profile ProfKernel = new Profile("Kernel");

   private void Kernel()
   {
      ProfKernel.Start();

      TokenStream.ADVANCE();

      DasItem = null;

      int i = 0;
      while (i <= PrevItSet.LastIndex) {
         Item it = PrevItSet.Items[i];
         if (it.Pos < it.Rule.Rhs.length) {
            Symbol sym = it.Rule.Rhs[it.Pos];
            if (sym instanceof Token) {

               if (((Token) sym).Code == TokenStream.Aktuelles()) { // Kernel

                  CurItSet
                        .Add(new Item(it.Rule, it.Pos + 1, it.Back, it, null));
               }

               if (((Token) sym).Code == -1) {
                  // -1 : EOF
                  DasItem = it;
                  DiesItem = it;
                  TokenStream.MarkSuccess();

               }
            }
         }
         i++;
      }
      ProfKernel.Stop();
   }

   private static Profile ProfClosure = new Profile("Closure");

   private void Closure()
   {
      ProfClosure.Start();
      do {
         Change = false;

         int i = 0;
         while (i <= CurItSet.LastIndex) {
            Item it = CurItSet.Items[i];
            if (it.Pos < it.Rule.Rhs.length) {
               Symbol sym = it.Rule.Rhs[it.Pos];
               if (sym instanceof Nonterm) {
                  Nonterm nt = (Nonterm) sym;
                  Predictor(nt);
               }
            } else {
               // pos at end
               Completer(it);
            }
            i++;
         }

      } while (Change);
      ProfClosure.Stop();
   }

   // ---------------------------------------------------------------------------

   private static Profile ProfCompleter = new Profile("Completer");

   private void Completer(Item It)
   {
      // It = Nont : gamma * , BackSet

      // for each PreItem in BackSet = Lhs : alpha * Nont beta , DDD
      // insert PostItem = Lhs : alpha Nont * beta , DDD

      ProfCompleter.Start();

      Nonterm Nont = It.Rule.Lhs;
      ItSet BackSet = It.Back;
      int i = 0;
      while (i <= BackSet.LastIndex) {
         Item PreItem = BackSet.Items[i];
         if (PreItem.Pos < PreItem.Rule.Rhs.length) {
            Symbol sym = PreItem.Rule.Rhs[PreItem.Pos];
            if (sym == Nont) {
               Item PostItem = new Item(PreItem.Rule, PreItem.Pos + 1,
                     PreItem.Back, PreItem, It);
               CurItSet.Add(PostItem);
               if (CurItSet.Added)
                  Change = true;
            }
         }

         i++;
      }
      ProfCompleter.Stop();
   }

   // ---------------------------------------------------------------------------

   private static final boolean OPTIMIZE = true;
   private static Profile ProfPredictor = new Profile("Predictor");

   private void Predictor(Nonterm nt)
   {
      // A: alpha * B beta, I
      // =>
      // B : * gamma , J
      // hier

      ProfPredictor.Start();

      for (Rule r : nt.Rules) {

         boolean jawoll = true;
         // ++++++++++
         if (OPTIMIZE) {
            HashSet<Symbol> d = Llana.Dir(r);
            int t = TokenStream.EinsWeiter();
            // int t = TokenStream.NextTok();
            jawoll = false;
            if (t == -1)
               jawoll = true;
            else
               for (Symbol s : d) {
                  if (((Token) s).Code == t) {
                     jawoll = true;
                     break;
                  }
               }

         }
         // ++++++++++

         if (jawoll) {

            Item It = new Item(r, 0, CurItSet, null, null);
            CurItSet.Add(It);
            if (CurItSet.Added)
               Change = true;

         }
      }

      ProfPredictor.Stop();

   }

   // ---------------------------------------------------------------------------

   private static void Stopp(String str)
   {
      System.out.println(str);
      throw new Error();
   }

   // ---------------------------------------------------------------------------

   private void Unparse(Item It)
   {
      Item left = It.Left;
      Item sub = It.Sub;
      Rule r = It.Rule;
      int len = r.Rhs.length;
      int p = It.Pos;
      boolean atend = (len == p);

      if (left != null)
         Unparse(left);

      if (sub != null) {
         Unparse(sub);
      } else {

         if (left != null) {
            // sub == null & left != null : token

            TokenStream.ADVANCE();
            JavaNode n = TokenStream.EarleyCurRep();

            RT.Push(n);
         }
      }

      if (atend) {
         RT.LengthOfCurrentRule = len;
         EGrammar.SemanticAction(RT, r.Id);
         RT.Pop(len);
         RT.Push(RT.SemanticValue);
      }
   }

   // ---------------------------------------------------------------------------

   private static void CheckAmbiguity(Item a, Item b)
   {
      if (a.Left == b.Left & a.Sub == b.Sub)
         return;
      /*
       * System.out.println("++++++++++++++++++++++++++++++ umbiguous"); if
       * (a.Left != b.Left) { System.out.println("+++ Conjunctive ambiguity"); }
       * if (a.Sub != b.Sub) { System.out.println("+++ Disjunctive ambiguity");
       * }
       */
   }

   // ---------------------------------------------------------------------------
   private JavaNode res_ast;

   public Gently.JavaNode EarleyParseProc(Yylex scanner)
   {

      DefineGrammar();

      RT = new EarleyRuntime();

      RT.ClearStack();

      TokenStream.InitTokenStream("earleyseq", scanner);

      Sequence();
      UNPARSE1();

      return res_ast;

   }

   // ==============================================================================

   static Profile ProfAddItem = new Profile("AddItem");

   class Item
   {
      public int No;
      public Rule Rule;
      public int Pos;
      public ItSet Back;
      public Item Left;
      public Item Sub;

      public Item(Rule r, int p, ItSet b, Item left, Item sub)
      {
         Rule = r;
         Pos = p;
         Back = b;
         Left = left;
         Sub = sub;
         No = -1;
      }

      public void Print()
      {
         System.out.print(No);
         System.out.print("   ");
         System.out.print(Rule.Lhs.Name);
         System.out.print(":  ");
         for (int i = 0; i < Pos; i++) {
            System.out.print(Rule.Rhs[i].Name);
            System.out.print(" ");
         }
         System.out.print("* ");
         for (int i = Pos; i < Rule.Rhs.length; i++) {
            System.out.print(Rule.Rhs[i].Name);
            System.out.print(" ");
         }
         System.out.print("    ");
         System.out.print(Back.Idx);
         System.out.print(" ");
         if (Left == null)
            System.out.print("-");
         else
            System.out.print(Left.No);
         System.out.print(" ");
         if (Sub == null)
            System.out.print("-");
         else
            System.out.print(Sub.No);
         System.out.println();
      }
   }

   // ==============================================================================

   class ItSet
   {
      public int Idx;
      public Item[] Items;
      public int LastIndex;

      public boolean Added;

      public void Add(Item It)
      {
         ProfAddItem.Start();

         Items[LastIndex + 1] = It;
         for (int i = 0;; i++) {
            Item Inspected = Items[i];
            if (Inspected.Rule == It.Rule && Inspected.Pos == It.Pos
                  && Inspected.Back == It.Back) {
               if (i == LastIndex + 1) {
                  LastIndex++;
                  It.No = 99999;
                  Added = true;

                  ProfAddItem.Stop();

                  return;
               } else {
                  Added = false;
                  CheckAmbiguity(It, Items[i]);

                  ProfAddItem.Stop();

                  return;
               }
            }
         }
      }

      public ItSet(int idx)
      {
         Items = new Item[20000];
         LastIndex = -1;
         Idx = idx;
      }

      public void Print()
      {
         System.out.println("ItSet {");
         for (int i = 0; i <= LastIndex; i++) {
            System.out.print("(" + i + ") ");
            Items[i].Print();
         }
         System.out.println("}");
      }
   }
}
