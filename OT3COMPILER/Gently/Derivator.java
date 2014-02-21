
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

import java.util.HashSet;
import java.util.LinkedList;
import Generated.Yylex;
import Generated.EGrammar;

public class Derivator
{
   public static void PrepareGrammar()
   {
      Grammatik.RedefineGrammar();
   }

   public static int qqFrameCount = 0;
   public static FRAME qqcurframe;

   public static JavaNode Parse(Yylex scanner)
   {
      // System.out.println("///// using acute derivator /////");
      Llana.TestForRecursion();

      DerivatorDo.ReadSourceFile(scanner);
      // System.out.println("///// source file read");

      // Step 1: Parse

      qqFrameCount = 0;
      DerivatorDo.InitStartFrame();

      do {

         // System.out.println("///// frame " + qqFrameCount);
         qqFrameCount++;
         DerivatorDo.Actions = 0;
         DerivatorDo.ThisFrame = DerivatorDo.NextFrame;
         DerivatorDo.NextFrame = new FRAME();
         qqcurframe = DerivatorDo.NextFrame;
         qqcurframe.qqcount = qqFrameCount;
         FRAME SavedFrame = new FRAME();
         FRAME SavedFrame2 = new FRAME();
         SavedFrame.lili = (LinkedList<DERIVATION>) (DerivatorDo.ThisFrame.lili
               .clone());
         SavedFrame2.lili = (LinkedList<DERIVATION>) (DerivatorDo.ThisFrame.lili
               .clone());

         DerivatorDo.TokenFound = false;
         DerivatorDo.EofFound = false;

         DerivatorDo.ProcessThisFrame();

         if (!DerivatorDo.TokenFound) {
            // System.out.println("///// ! TokenFound");

            boolean EXPERIMENTAL = false;
            if (EXPERIMENTAL) {
               System.out.println("+++ Syntax Error");
               DerivatorDo.ErrorInfo(SavedFrame);
               DerivatorDo.Recover(SavedFrame2);
            }

            TokenStream.SyntaxErrorNextPos();
         } else {

            TokenStream.ADVANCE();
         }

      } while (!DerivatorDo.EofFound);

      if (!DerivatorDo.LastDerivation.Head().Name.equals("Eof")) {
         TokenStream.SyntaxError();
      }

      // Step 2: TreeBuilder

      JavaNode ast;
      try {
         // LastDerivation : [ Eof ]
         if (!DerivatorDo.LastDerivation.Head().Name.equals("Eof")) {
            JavaLib.RuntimeError("LastDerivation: Eof expected");
         }
         STATIC_DERI.TreeBuilder(DerivatorDo.LastDerivation);

         ast = STATIC_DERI.RUNTIME.SemanticValue;
      } catch (Exception e) {
         e.printStackTrace();
         JavaLib.RuntimeError("--- Exception in TreeBuilder");
         ast = null;
      }

      return ast;

   }

}

// ==============================================================================

class STACK
{
   public Symbol symbol = null;
   public STACK next = null;
}

// ==============================================================================

class DERIVATION
{
   // a DERIVATION is a list of elements that still are to be recognized
   // if ptr is { e1, e2, ... , en }
   // e1, e2, ... , en are to be recognized

   private STACK ptr;
   public DERIVATION ursprung;
   public FRAME urframe; // qq

   public Rule regel;

   // === constructors ===

   // constructor 1 InitWithRule
   // r = Root : UserRoot Eof
   // called from InitStartFrame
   static DERIVATION InitWithRule(Rule r)
   {
      DERIVATION NewDerivation = new DERIVATION();
      NewDerivation.ursprung = null; // 1
      NewDerivation.urframe = Derivator.qqcurframe;
      NewDerivation.regel = r;

      STACK head = null;
      for (int i = r.Rhs.length - 1; i >= 0; i--) {
         Symbol s = r.Rhs[i];
         STACK p = new STACK();
         p.symbol = s;
         p.next = head;
         head = p;
      }
      NewDerivation.ptr = head;

      return NewDerivation;
   }

   // constructor 2 Extend
   // oldder = { e1, e2, ..., en }
   // r = M0 : M1, M2, ..., Mk
   // newder = { M1, M2, ..., Mk, e2, ..., en }
   // i.e. e1 is replaced by M1, M2, ..., Mk
   static DERIVATION Extend(Rule r, DERIVATION oldder)
   {
      DERIVATION NewDerivation = new DERIVATION();
      NewDerivation.urframe = Derivator.qqcurframe;
      NewDerivation.ursprung = oldder; // 2
      NewDerivation.regel = r;

      STACK head = oldder.ptr.next;
      for (int i = r.Rhs.length - 1; i >= 0; i--) {
         Symbol s = r.Rhs[i];
         STACK p = new STACK();
         p.symbol = s;
         p.next = head;
         head = p;
      }
      NewDerivation.ptr = head;

      return NewDerivation;
   }

   // constructor 3 DER_Token
   // same as constructor 2 but no rule
   //
   static DERIVATION DER_Token(DERIVATION oldder)
   {
      DERIVATION NewDerivation = new DERIVATION();
      NewDerivation.urframe = Derivator.qqcurframe;
      NewDerivation.ursprung = oldder; // 3
      NewDerivation.regel = null;

      NewDerivation.ptr = oldder.ptr.next;

      return NewDerivation;
   }

   public Symbol Head()
   {
      return ptr.symbol;
   }

   public boolean Equals(DERIVATION that)
   {
      STACK p = this.ptr;
      STACK q = that.ptr;

      while (p != null) {
         if (p == q)
            return true;
         if (q == null)
            return false;
         if (p.symbol != q.symbol)
            return false;
         p = p.next;
         q = q.next;
      }

      return q == null;
   }

   void Print()
   {
      STACK p = ptr;

      System.out.print("   DERIVATION [");
      while (p != null) {
         System.out.print(" " + p.symbol.Name);
         p = p.next;
      }
      System.out.println(" ]");
   }

   void PrintHistory()
   {
      DERIVATION ur = ursprung;
      if (ur != null) {
         ur.PrintHistory();
      }
      Print();
   }
}

// ==============================================================================

class STATIC_DERI
{
   public static final int CODE_APRIME_EMPTY = 666712;
   public static final int CODE_TOKEN = 666713;

   static DERIVATION[] History = new DERIVATION[900000];
   static DERIVATION[] XHistory = new DERIVATION[900000];
   static int HistoryIndex = -1;
   static int POS;
   public static int level = 0;
   public static EarleyRuntime RUNTIME;

   static void CollectHistory(DERIVATION deri)
   {

      XCollectHistory(deri);
   }

   static void XCollectHistory(DERIVATION deri)
   {
      DERIVATION d = deri.ursprung;
      int i = 0;
      XHistory[i] = deri;
      while (d != null) {
         i++;
         XHistory[i] = d;
         // System.out.println("i="+i);
         d = d.ursprung;
      }
      while (i >= 0) {
         HistoryIndex++;
         History[HistoryIndex] = XHistory[i];
         i--;
      }
   }

   static void RCollectHistory(DERIVATION deri)
   {
      DERIVATION ur = deri.ursprung;
      if (ur != null) {
         RCollectHistory(ur);
      }
      HistoryIndex++;
      History[HistoryIndex] = deri;
   }

   static void ShowHistory()
   {
      int k = HistoryIndex;
      for (int i = 0; i <= k; i++) {
         System.out.print("Show i = " + i + " : ");
         DERIVATION deri = History[i];
         Rule r = deri.regel;
         if (r == null) {
            System.out.println("(token)");
         } else {
            int id = r.Id;
            String str = r.Lhs.Name;
            System.out.println("id = " + id + ", str = " + str);
         }
      }
   }

   static Knoten ConvertHistory()
   {
      POS = 1; // skip root
      Knoten k = ConvertHistoryR();
      return k;
   }

   public static void indent()
   {
      for (int i = 1; i <= level; i++) {
         System.out.print("   ");
      }
   }

   static Knoten ConvertHistoryR()
   {
      int i = POS;
      POS++;
      DERIVATION deri = History[i];
      Rule r = deri.regel;

      if (r == null) {
         Knoten k = new Knoten();
         k.Child = null;
         k.Code = CODE_TOKEN;
         k.Name = "(token)";
         return k;
      } else {
         int id = r.Id;
         String str = r.Lhs.Name;
         int len = r.Rhs.length;
         Knoten[] child = new Knoten[len];
         for (int k = 0; k < len; k++) {
            // level++;
            child[k] = ConvertHistoryR();
            // level--;
         }
         Knoten k = new Knoten();
         k.Child = child;
         k.Code = id;
         k.Name = str;
         return k;
      }
   }

   static Knoten Trafo(Knoten k)
   // r = Trafo ( k )
   // k ist a node corresponding to the converted grammar
   // (left recursion converted to right recursion)
   // r is the corresponding node of the original grammar
   {
      // 1. token
      if (k.Code == CODE_TOKEN) {
         return k;
      }
      // 2: A -> beta APRIME
      else if (IsLeftRecursiveBeta(k.Code)) {

         Knoten newk = ApplList(null, k);

         return newk;

      }
      // 3. other (no APRIME, no A)
      else {
         // System.out.println("Trafo else : " + k.Name + " " + k.Code);
         Knoten tk = new Knoten();
         tk.Name = k.Name;
         tk.Code = k.Code;
         tk.Child = new Knoten[k.Child.length];
         for (int i = 0; i < k.Child.length; i++) {
            tk.Child[i] = Trafo(k.Child[i]);
         }
         return tk;
      }
   }

   static HashSet<Integer> AlphaSet = new HashSet<Integer>();

   static void MarkLeftRecursiveAlpha(int code)
   {
      AlphaSet.add(code);
   }

   static boolean IsLeftRecursiveAlpha(int code)
   {
      return AlphaSet.contains(code);
   }

   static HashSet<Integer> BetaSet = new HashSet<Integer>();

   static void MarkLeftRecursiveBeta(int code)
   {
      BetaSet.add(code);
   }

   static boolean IsLeftRecursiveBeta(int code)
   {
      return BetaSet.contains(code);
   }

   static Knoten ApplList(Knoten inner, Knoten k)
   {
      int code = k.Code;
      String name = k.Name;
      Knoten[] child = k.Child;

      if (code == CODE_APRIME_EMPTY) {
         return inner;
      }

      if (!IsLeftRecursiveBeta(code) && !IsLeftRecursiveAlpha(code)) {
         JavaLib.RuntimeError("Unexpected code : " + code);
      }

      Knoten rec = Last(child);

      Knoten newinner = new Knoten();
      newinner.Code = code;
      newinner.Name = name;
      int len = child.length;

      if (inner == null) {
         Knoten[] newchild = new Knoten[len - 1];
         for (int i = 0; i < len - 1; i++) {
            newchild[i] = Trafo(child[i]);
         }
         newinner.Child = newchild;
      } else {
         Knoten[] newchild = new Knoten[len];
         newchild[0] = inner;
         for (int i = 0; i < len - 1; i++) {
            newchild[i + 1] = Trafo(child[i]);
         }
         newinner.Child = newchild;
      }

      Knoten conv = ApplList(newinner, rec);

      return conv;

   }

   static void Evaluate()
   {
      HistoryIndex++;
      int i = HistoryIndex;
      DERIVATION d = History[i];

      if (d == null) {
         System.out.print("++++++++++++++++ unexpected: d == null");
         TokenStream.SyntaxErrorNextPos();
      }

      if (d.regel == null) // constructor DER_Token
      {

         JavaNode nd = TokenStream.NaechstesRep();
         RUNTIME.Push(nd);
         TokenStream.ADVANCE();
      } else {

         Rule r = d.regel;
         int len = r.Rhs.length;
         for (int m = 0; m < len; m++) {
            Symbol sym = r.Rhs[m];
            if (!sym.Name.equals("Eof")) {
               Evaluate();
            }
         }

         if (i != 0) {
            // ------------------------------semantic action
            RUNTIME.LengthOfCurrentRule = len;
            int code = r.Id;

            EGrammar.SemanticAction(RUNTIME, code);

            RUNTIME.Pop(len);
            RUNTIME.Push(RUNTIME.SemanticValue);
            // ------------------------------semantic action
         }
      }
   }

   static void TreeBuilder(DERIVATION d)
   {
      RUNTIME = new EarleyRuntime();

      HistoryIndex = -1;
      TokenStream.ResetPtr();

      boolean EXTENDED = true;
      if (EXTENDED) {
         CollectHistory(d);
         Knoten k = ConvertHistory();
         Knoten tk = Trafo(k);
         tk.Aktion();
      } else {
         Evaluate();
      }
   }

   static Knoten Last(Knoten[] ks)
   {
      return ks[ks.length - 1];
   }

   static Knoten[] ButLast(Knoten[] ks)
   {
      int len = ks.length;
      Knoten[] rs = new Knoten[len - 1];
      for (int i = 0; i < len - 1; i++) {
         rs[i] = ks[i];
      }
      return ks;
   }
}

// ==============================================================================

class Knoten
{
   int Code;
   String Name;
   Knoten[] Child;

   void Print()
   {
      STATIC_DERI.indent();
      System.out.println(Name + " " + Code + " {");
      if (Child != null) {
         STATIC_DERI.level++;
         for (Knoten k : Child) {
            k.Print();
         }
         STATIC_DERI.level--;
      }
      STATIC_DERI.indent();
      System.out.println("}");
   }

   void PrintDeri()
   {
      STATIC_DERI.indent();
      System.out.println(Name + " " + Code + " {");
      if (Child != null) {
         STATIC_DERI.level++;
         for (Knoten k : Child) {
            k.PrintDeri();
         }
         STATIC_DERI.level--;
      }
      STATIC_DERI.indent();
      System.out.println("}");
   }

   public void Aktion()
   {
      if (Code == STATIC_DERI.CODE_TOKEN) // token
      {

         JavaNode nd = TokenStream.NaechstesRep();
         STATIC_DERI.RUNTIME.Push(nd);
         TokenStream.ADVANCE();

      } else {
         if (Child != null) {
            for (Knoten k : Child) {
               k.Aktion();
            }
         }

         int code = Code;
         int len = Child.length;

         // ------------------------------semantic action
         STATIC_DERI.RUNTIME.LengthOfCurrentRule = len;

         EGrammar.SemanticAction(STATIC_DERI.RUNTIME, code);

         STATIC_DERI.RUNTIME.Pop(len);
         STATIC_DERI.RUNTIME.Push(STATIC_DERI.RUNTIME.SemanticValue);
         // ------------------------------semantic action

      }
   }
}

// ==============================================================================

class FRAME
{

   public int qqcount;
   public LinkedList<DERIVATION> lili = new LinkedList<DERIVATION>();

   void Print()
   {
      System.out.println("FRAME {");
      int n = lili.size();
      for (int i = 0; i < n; i++) {
         lili.get(i).Print();
      }
      System.out.println("}");
   }

   public int qqElements()
   {
      return lili.size();
   }

   public boolean HasElements()
   {
      return lili.size() != 0;
   }

   DERIVATION PollFromFrame()
   {
      return lili.poll();
   }

   void AddToFrame(DERIVATION Derivation)
   {
      DerivatorDo.Actions++;
      for (DERIVATION D : lili) {
         if (Derivation.Equals(D)) {
            return;
         }
      }

      lili.add(Derivation);
   }

}

// ==============================================================================

class DerivatorDo
{

   public static FRAME ThisFrame;
   public static FRAME NextFrame;
   public static boolean TokenFound;
   public static boolean EofFound;

   public static int Actions;
   public static DERIVATION LastDerivation = null;

   public static void ReadSourceFile(Yylex scanner)
   {
      GrammarLib.SignalWhitespace = false;
      scanner.YyState = new LexerState();
      TokenStream.InitTokenStream("llana", scanner);
   }

   public static void InitStartFrame()
   {
      Nonterm nt = (Nonterm) ExhaustiveParser.UserRoot;

      Nonterm Root = new Nonterm("Root");
      Token Eof = new Token("Eof", -1);

      Rule rootrule = new Rule(-999, Root, new Symbol[] { nt, Eof });
      Root.Rules = new Rule[] { rootrule };
      DERIVATION FirstDerivation = DERIVATION.InitWithRule(rootrule);

      NextFrame = new FRAME();
      Derivator.qqcurframe = NextFrame;
      Derivator.qqcurframe.qqcount = Derivator.qqFrameCount;
      NextFrame.AddToFrame(FirstDerivation); // initial
   }

   public static void ProcessThisFrame()
   {

      while (ThisFrame.HasElements()) {
         DERIVATION Der = ThisFrame.PollFromFrame();
         LastDerivation = Der;

         ProcessDerivation(Der);
      }

   }

   static void ProcessDerivation(DERIVATION Der)
   {
      // called from Parse/ProcessThisFrame

      Symbol s0 = Der.Head();

      if (s0 instanceof Nonterm) {

         Nonterm s = (Nonterm) s0;

         for (Rule r : s.Rules) {
            boolean viable = false;
            for (Symbol t : Llana.Dir(r)) {
               if (((Token) t).Code == TokenStream.EinsWeiter()) {
                  viable = true;
                  break;
               }
            }

            // cyclic ?

            if (r.Rhs.length > 0 && r.Rhs[0] == s) {
               System.out.println("Adding cycle...");
            }

            if (viable) {
               // constructor 2
               DERIVATION NewerDerivation = DERIVATION.Extend(r, Der);

               ThisFrame.AddToFrame(NewerDerivation); // nonterm
            }

         }

      } else {

         Token s = (Token) s0;

         if (s.Code == TokenStream.EinsWeiter()) {
            // constructor 3
            if (s.Code == -1) {
               EofFound = true;
            } else {
               DERIVATION next = DERIVATION.DER_Token(Der);

               NextFrame.AddToFrame(next); // token
            }

            TokenFound = true;
         }
      }
   }

   static HashSet<Token> Valid;

   public static void ErrorInfo(FRAME SavedFrame)
   {
      ErrInf_CollectValidTokens(SavedFrame);
      System.out.println("+++ Valid:");
      for (Token t : Valid) {
         System.out.println(" " + t.Name);
      }
   }

   static void ErrInf_CollectValidTokens(FRAME SavedFrame)
   {
      NextFrame = SavedFrame;

      ThisFrame = NextFrame;
      NextFrame = new FRAME();

      TokenFound = false;
      EofFound = false;

      Valid = new HashSet<Token>();

      ErrInf_CollectValidTokensThisFrame();
   }

   static void ErrInf_CollectValidTokensThisFrame()
   {
      while (ThisFrame.HasElements()) {
         DERIVATION Der = ThisFrame.PollFromFrame();
         LastDerivation = Der;

         ErrInf_CollectValidTokensDerivation(Der);
      }
   }

   static void ErrInf_CollectValidTokensDerivation(DERIVATION Der)
   {
      Symbol s0 = Der.Head();

      if (s0 instanceof Nonterm) {

         Nonterm s = (Nonterm) s0;

         for (Rule r : s.Rules) {
            // constructor 2
            DERIVATION NewerDerivation = DERIVATION.Extend(r, Der);

            ThisFrame.AddToFrame(NewerDerivation); // here error info
         }

      } else {
         Token s = (Token) s0;
         Valid.add(s);
      }
   }

   public static void Recover(FRAME SavedFrame)
   {
      NextFrame = SavedFrame;
      int i = 0;
      do {
         i++;

         ThisFrame = NextFrame;
         NextFrame = new FRAME();

         TokenFound = false;
         EofFound = false;

         Valid = new HashSet<Token>();

         RecoverThisFrame();
         System.out.println("===== Step: " + i);
         NextFrame.Print();
         for (Token t : Valid) {
            System.out.println(" " + t.Name);
            if (t.Code == TokenStream.EinsWeiter()) {
               System.out.println("gefunden ! " + t.Name);
               TokenStream.ADVANCE();
               return;
            }
            if (t.Code == TokenStream.NWeiter(2)) {
               System.out.println("2 weiter gefunden ! " + t.Name);
               TokenStream.ADVANCE();
               TokenStream.ADVANCE();
               return;
            }
            if (t.Code == TokenStream.NWeiter(3)) {
               System.out.println("3 weiter gefunden ! " + t.Name);
               TokenStream.ADVANCE();
               TokenStream.ADVANCE();
               TokenStream.ADVANCE();
               return;
            }
         }

      } while (NextFrame.HasElements() && i < 10);
      JavaLib.RuntimeError("Recover failed");
   }

   static void RecoverThisFrame()
   {
      while (ThisFrame.HasElements()) {
         DERIVATION Der = ThisFrame.PollFromFrame();
         LastDerivation = Der;

         RecoverDerivation(Der);
      }

   }

   static void RecoverDerivation(DERIVATION Der)
   {
      Symbol s0 = Der.Head();

      if (s0 instanceof Nonterm) {

         Nonterm s = (Nonterm) s0;

         for (Rule r : s.Rules) {

            // constructor 2
            DERIVATION NewerDerivation = DERIVATION.Extend(r, Der);

            ThisFrame.AddToFrame(NewerDerivation); // recover; nonterm

         }

      } else {

         Token s = (Token) s0;

         Valid.add(s);
         // constructor 3
         if (s.Code == -1) {
            EofFound = true;
         } else {
            DERIVATION next = DERIVATION.DER_Token(Der);

            NextFrame.AddToFrame(next); // recover; token
         }

         TokenFound = true;
      }
   }

}

// ==============================================================================

class Grammatik
{

   public static void RedefineGrammar()
   {
      Nonterm root = (Nonterm) ExhaustiveParser.UserRoot;
      NontermSet = new HashSet<Nonterm>();
      CollectNonterminals(root);
      for (Nonterm nt : NontermSet) {
         if (Recursive(nt)) {
            TransformRecursiveNt(nt);
         }
      }
   }

   // private

   private static HashSet<Nonterm> NontermSet;

   private static void CollectNonterminals(Nonterm nt)
   {
      for (Rule r : nt.Rules) {
         for (Symbol s : r.Rhs) {
            if (s instanceof Nonterm) {
               Nonterm k = (Nonterm) s;
               if (!NontermSet.contains(k)) {
                  NontermSet.add(k);
                  CollectNonterminals(k);
               }
            }
         }
      }
   }

   private static void TransformRecursiveNt(Nonterm nt)
   {
      // System.out.println("recursive : " + nt.Name);

      Nonterm APRIME = new Nonterm("APRIME" + "_" + nt.Name);
      Llana.Register(APRIME);

      LinkedList<Rule> RuleList = new LinkedList<Rule>();
      int NRuleList = 0;

      Token IMPOSSIBLE_TOKEN = new Token("/Rumpelstilzchen/", -888);

      for (Rule r : nt.Rules) {
         if (r.Rhs.length > 0 && r.Rhs[0] == nt) {
            // A -> A alpha
            // =>
            // APRIME -> alpha APRIME
            // APRIME -> epsilon // CODE_APRIME_EMPTY

            int code = r.Id;

            int len = r.Rhs.length;

            STATIC_DERI.MarkLeftRecursiveAlpha(code);

            Symbol[] Syms = new Symbol[len];
            for (int i = 0; i < len - 1; i++) {
               Syms[i] = r.Rhs[i + 1];
            }
            Syms[len - 1] = APRIME;

            Rule newrule = new Rule(code, APRIME, Syms);
            RuleList.add(newrule);
            NRuleList++;

            // delete rule (make invisible)
            r.Rhs = new Symbol[] { IMPOSSIBLE_TOKEN };
         } else {
            // A -> beta
            // =>
            // A -> beta APRIME

            STATIC_DERI.MarkLeftRecursiveBeta(r.Id);

            int len = r.Rhs.length;
            Symbol[] newrhs = new Symbol[len + 1];
            for (int i = 0; i < len; i++) {
               newrhs[i] = r.Rhs[i];
            }
            newrhs[len] = APRIME;
            r.Rhs = newrhs;
         }
      }

      Rule[] rules = new Rule[NRuleList + 1];
      int i = 0;
      for (Rule r : RuleList) {
         rules[i] = r;
         i++;
      }
      rules[i] = new Rule(STATIC_DERI.CODE_APRIME_EMPTY, APRIME,
            new Symbol[] {});

      APRIME.Rules = rules;

   }

   private static boolean Recursive(Nonterm nt)
   {
      for (Rule r : nt.Rules) {
         if (r.Rhs.length > 0 && r.Rhs[0] == nt)
            return true;
      }
      return false;
   }

}
// ==============================================================================
