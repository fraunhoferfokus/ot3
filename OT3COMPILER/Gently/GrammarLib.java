
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

import Generated.YyProcs;
import Gently.JavaNode;
import Gently.JavaStringValue;
import Gently.JavaLib;
import Generated.Yylex; //7
import Generated.YyConfig; //5
import Generated.EGrammar; //2

public class GrammarLib
{
   public static boolean SignalWhitespace;
   public static int WhitespaceToken = 257;
   public static int ErrorToken = 357;
   public static JavaNode JavaParseResult = null;

   public static String CyanSource = null;

   public static boolean CyanSemantics(String source, JavaNode AbstractSyntax)
   {
      CyanSource = source;
      if (AbstractSyntax != null) {
         try {
            YyProcs.Semantics(AbstractSyntax);
            return true;
         } catch (ReportedError E) {

         } catch (Error E) {
            System.out.println("Error in Semantics");
            E.printStackTrace();

         }
      }
      return false;
   }

   // --------------------------------------------------------------------------
   // main, DOPARSE is entry for all parsing algorithms (acute, accent, yacc)
   // --------------------------------------------------------------------------

   // Code generated from ``Parse(fn -> ast)''

   public static JavaNode ParseNamedFile(JavaNode fn)
   {
      JavaStringValue sv = (JavaStringValue) fn;
      String str = sv.Str;

      JavaNode n = ParseFile(str);
      return n;
   }

   // --------------------------------------------------------------------------

   // Code generated from ``Parse(-> ast)'', ``Nonterm(-> ast)''

   public static JavaNode ParseStandardFile()
   {

      String[] args = JavaLib.GetProgramArguments();
      String str = args[0];

      return ParseFile(str);
   }

   // --------------------------------------------------------------------------

   private static JavaNode ParseFile(String fn)
   {
      java.io.Reader rdr = null;

      try {
         rdr = new java.io.FileReader(fn);
      } catch (java.io.FileNotFoundException exc) {
         System.out.println("file not found: " + fn);
         throw new ReportedError();
      }

      TokenStream.SetCurrentFile(fn, null);
      JavaNode n = DOPARSE(rdr, true);
      return n;
   }

   // --------------------------------------------------------------------------

   private static JavaNode DOPARSE(java.io.Reader rdr, boolean ExitOnError)
   {
      SignalWhitespace = false;

      try {

         JavaNode n = null;

         boolean meins = false;
         if (meins) {
            n = GammaParseFile(rdr);
         } else if (YyConfig.UseAcute) {

            n = GammaParseFile(rdr);

         } else if (YyConfig.UseAccent) {

            n = EarleyParseFile(rdr);

         } else {

            n = YaccParseFile(rdr);

         }

         return n;

      }

      catch (ErrorReportedByParser x) {
         if (ExitOnError)
            throw new RuntimeError();
         return null;
      } catch (RuntimeException x) {

         x.printStackTrace();
         PARSEERROR(ExitOnError);
         return null;

      } catch (Exception x) {

         x.printStackTrace();
         PARSEERROR(ExitOnError);
         return null;

      }
   }

   private static void PARSEERROR(boolean ExitOnError)
   {
      System.out.println("PARSE ERROR");
      if (ExitOnError)
         throw new RuntimeError();
   }

   // --------------------------------------------------------------------------
   // end main
   // --------------------------------------------------------------------------

   // --------------------------------------------------------------------------
   // Acute ; see class Derivator
   // (see also class PredictiveParser)
   // (see also class Llana)
   // --------------------------------------------------------------------------

   private static JavaNode GammaParseFile(java.io.Reader rdr)
   {
      Yylex scanner = new Yylex(rdr);

      scanner.YyState = new LexerState();
      return GoGo(scanner);

   }

   public static JavaNode GoGo(Yylex scanner)
   {
      EGrammar.DefineGrammar();

      Derivator.PrepareGrammar();
      Llana.AnalizeGrammar();

      JavaNode nd = Derivator.Parse(scanner);

      return nd;
   }

   // --------------------------------------------------------------------------
   // end Acute
   // --------------------------------------------------------------------------

   // --------------------------------------------------------------------------
   // Accent
   // --------------------------------------------------------------------------

   private static JavaNode EarleyParseFile(java.io.Reader rdr)
   {
      ExhaustiveParser earl = new ExhaustiveParser();
      Yylex scanner = new Yylex(rdr);

      scanner.YyState = new LexerState();

      return earl.EarleyParseProc(scanner);

   }

   // --------------------------------------------------------------------------
   // end Accent
   // --------------------------------------------------------------------------

   // --------------------------------------------------------------------------
   // Yacc
   // --------------------------------------------------------------------------

   private static JavaNode YaccParseFile(java.io.Reader rdr)
   {
      System.out.println("Error: BISON selected");
      JavaLib.RuntimeError("Error: BISON selected");
      return null;

   }

   // --------------------------------------------------------------------------
   // end Yacc
   // --------------------------------------------------------------------------

   // CyanParser

   public static JavaNode CyanParser(String name, java.io.Reader rdr,
         String text)
   {

      java.io.StringReader xxrdr = new java.io.StringReader(text);
      // 1
      TokenStream.SetCurrentFile(name, text);
      return DOPARSE(xxrdr, false); // Call from CyanParser(Reader rdr)

   }

   // --------------------------------------------------------------------------

   // CyanScanner

   public static Yylex CyanScanner(java.io.Reader rdr, int offset)
   {
      SignalWhitespace = true;
      Yylex scanner = new Yylex(rdr);

      scanner.YyState = new LexerState();
      return scanner;
   }

   // --------------------------------------------------------------------------

}
