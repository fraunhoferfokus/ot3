
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

import Generated.Yylex;

import java.io.*;

public class TokenStream
{

   public static int STARTOFFSET;

   private static int AktuellerIndex;
   private static int StartAktuellerIndex;
   private static int AktuellerPoint;

   private static int[] TOKENINFO_TokenCode;
   private static JavaNode[] TOKENINFO_TokenNode;
   private static int[] TOKENINFO_TokenOffset;
   private static int[] TOKENINFO_TokenLine;

   // --------------------------------------------------------------------

   static int TokenCount;

   public static void InitTokenStream(String newstate, Yylex Lexer)
   {
      final int MaxLengthTokenStream = 90000; // xxx

      // ALLOCATE TOKENINFO
      TOKENINFO_TokenCode = new int[MaxLengthTokenStream];
      TOKENINFO_TokenNode = new JavaNode[MaxLengthTokenStream];
      TOKENINFO_TokenOffset = new int[MaxLengthTokenStream];
      TOKENINFO_TokenLine = new int[MaxLengthTokenStream];

      AktuellerIndex = -1;

      TokenCount = 0;

      int LEXERRETURNVALUE;

      LexerState.LEXERSTATE_SourceLine = 1;
      LexerState.LEXERSTATE_SourceOffset = 0;
      LexerState.LEXERSTATE_yylval = null; // JavaNode
      do {
         // LOOP
         LexerState lexerstate = Lexer.YyState;
         // TOKENINFO_TokenOffset[TokenCount] = lexerstate.SourceOffset;
         int vorher = -1;
         try {
            vorher = lexerstate.SourceOffset;
            LEXERRETURNVALUE = Lexer.yylex();
         } catch (java.io.IOException exc) {
            JavaLib.RuntimeError("IOException in scanner");
            LEXERRETURNVALUE = -9; // dummy
         } catch (Exception exc) {
            System.out.println("Exception in yylex (generated by JLex)");
            // exc.printStackTrace();

            // LexerState lexerstate = Lexer.YyState;

            int line = lexerstate.SourceLine;
            int offset = lexerstate.SourceOffset;
            int len = 1; // xxxx
            offset = STARTOFFSET;
            offset = vorher;
            Coordinate coord = new Coordinate( // COORD 5 SyntaxError
                  CurrentFile, line, offset, len);

            internalError(coord, "lexer exception");

            LEXERRETURNVALUE = -9; // dummy
         }

         // LexerState lexerstate = Lexer.YyState;

         JavaNode n = Lexer.YyState.yylval;

         // SET TOKENINFO
         TOKENINFO_TokenCode[TokenCount] = LEXERRETURNVALUE;
         TOKENINFO_TokenNode[TokenCount] = n;
         TOKENINFO_TokenOffset[TokenCount] = STARTOFFSET;
         TOKENINFO_TokenLine[TokenCount] = lexerstate.SourceLine;

         TokenCount++;

         // END LOOP

      } while (LEXERRETURNVALUE > 0);

   }

   // --------------------------------------------------------------------
   // Position
   // --------------------------------------------------------------------

   public static void ResetPtr()
   {
      AktuellerIndex = -1;
   }

   // --------------------------------------------------------------------

   public static void MarkStart()
   {
      StartAktuellerIndex = AktuellerIndex;
   }

   // --------------------------------------------------------------------

   public static void BackToStart()
   {
      AktuellerIndex = StartAktuellerIndex;
   }

   // --------------------------------------------------------------------

   public static void MarkSuccess()
   {
      AktuellerPoint = AktuellerIndex;
   }

   // --------------------------------------------------------------------

   public static void BackToSuccess()
   {
      AktuellerIndex = AktuellerPoint - 1;
   }

   // --------------------------------------------------------------------

   static void ADVANCE()
   {
      AktuellerIndex++;
   }

   // --------------------------------------------------------------------
   // Aktuell
   // --------------------------------------------------------------------

   public static int Aktuelles()
   {
      return TOKENINFO_TokenCode[AktuellerIndex];
   }

   // --------------------------------------------------------------------

   public static JavaNode EarleyCurRep()
   {
      return TOKENINFO_TokenNode[AktuellerIndex];
   }

   // --------------------------------------------------------------------
   // Naechster
   // --------------------------------------------------------------------

   public static int EinsWeiter()
   {
      return TOKENINFO_TokenCode[AktuellerIndex + 1];
   }

   public static int NWeiter(int N)
   {
      if (TokenCount < AktuellerIndex + N) {
         return -999;
      }
      return TOKENINFO_TokenCode[AktuellerIndex + N];
   }

   // --------------------------------------------------------------------

   public static JavaNode NaechstesRep()
   {
      return TOKENINFO_TokenNode[AktuellerIndex + 1];
   }

   // --------------------------------------------------------------------
   // etc
   // --------------------------------------------------------------------

   public static LexerState GetEgon()
   {
      // generated code for semantic action
      // passes RT.EgonLexerState()
      // to SemeanticActions.SourceRange and other functions
      // EgonGetLexerState calls this function

      int k = AktuellerIndex + 1;
      LexerState EGON = new LexerState();

      EGON.SourceLine = TOKENINFO_TokenLine[k];
      EGON.SourceOffset = TOKENINFO_TokenOffset[k];
      EGON.yylval = TOKENINFO_TokenNode[k];

      return EGON;
   }

   // --------------------------------------------------------------------

   private static Token EOFTOKEN = new Token("/EOF/", -1);

   static Token EofToken()
   {
      return EOFTOKEN;
   }

   // --------------------------------------------------------------------
   // Messages
   // --------------------------------------------------------------------

   public static String CurrentFile;

   private static java.util.Hashtable<String, String> HT = new java.util.Hashtable<String, String>();

   public static void SetCurrentFile(String file, String text)
   {
      CurrentFile = file;
      if (text != null)
         HT.put(file, text);
   }

   public static void SyntaxError()
   {
      int line = TOKENINFO_TokenLine[AktuellerIndex]; // state.SourceLine;
      int offset = TOKENINFO_TokenOffset[AktuellerIndex];
      int len = 0; // xxxx

      Coordinate coord = new Coordinate( // COORD 5 SyntaxError
            CurrentFile, line, offset, len);
      internalSyntaxError(coord, "syntax error");
   }

   // MSG 2
   public static void SyntaxErrorNextPos()
   {
      int line = TOKENINFO_TokenLine[AktuellerIndex + 1]; // state.SourceLine;
      int offset = TOKENINFO_TokenOffset[AktuellerIndex + 1]; // state.SourceLine;
      int len = 0; // xxxx
      Coordinate coord = new Coordinate( // COORD 6 SyntaxErrorNextPos
            CurrentFile, line, offset, len);
      internalSyntaxError(coord, "syntax error");
   }

   public static Coordinate ReportedErrorPos = null;
   public static String ReportedErrorMsg = null;

   // generated by Gentle compiler
   // as translation of: error Msg, Pos
   // or: error Msg (error Msg, /nopos/)
   // MSG 3
   public static void ReportError(JavaNode msg, JavaNode pos)
   // pos is in string format
   // coordinate converted by obsolete JavaLib.rtsGetSourcePos
   {
      // message text
      JavaStringValue valmsg = (JavaStringValue) msg;
      String strmsg = valmsg.Str;

      // coordinate
      JavaStringValue valpos = (JavaStringValue) pos;
      String strpos = valpos.Str;

      String file = GetFileFromString(strpos);
      int line = GetLineFromString(strpos);
      int offset = GetOffsetFromString(strpos);
      int length = 0; // xxxxxxxxxxxxxxxx

      Coordinate coord = new Coordinate( // COORD 7 ReportError
            file, line, offset, length);
      internalError(coord, strmsg);
   }

   public static String KonvertCoordinateToString(Coordinate coord)
   {
      if (coord == null)
         return "/unknown pos/";
      else
         return "/line,offset,length,file=/ " + coord.getLine() + " "
               + coord.getOffset() + " " + coord.getLength() + " "
               + coord.getFile();
   }

   public static int GetLineFromString(String str)
   {
      int k;
      String rest;
      String head;

      k = str.indexOf(" ");
      head = str.substring(0, k);
      rest = str.substring(k + 1);

      k = rest.indexOf(" ");
      head = rest.substring(0, k);
      rest = rest.substring(k + 1);
      int line = Integer.parseInt(head);
      return line;
   }

   public static int GetOffsetFromString(String str)
   {
      int k;
      String rest;
      String head;

      k = str.indexOf(" ");
      head = str.substring(0, k);
      rest = str.substring(k + 1);

      k = rest.indexOf(" ");
      head = rest.substring(0, k);
      rest = rest.substring(k + 1);

      k = rest.indexOf(" ");
      head = rest.substring(0, k);
      rest = rest.substring(k + 1);
      int offset = Integer.parseInt(head);

      return offset;
   }

   public static String GetFileFromString(String str)
   {
      int k;
      String rest;

      k = str.indexOf(" ");
      rest = str.substring(k + 1);

      k = rest.indexOf(" ");
      rest = rest.substring(k + 1);

      k = rest.indexOf(" ");
      rest = rest.substring(k + 1);

      k = rest.indexOf(" ");
      rest = rest.substring(k + 1);

      return rest;
   }

   // --------------------------------------------------------------------
   // ExhaustiveParser/Derivator ->
   // TokenStream.SyntaxError / TokenStream.SyntaxErrorNextPos ->
   // GrammarLib.SyntaxError
   //
   // MSG 5
   private static void internalError(Coordinate coord, String txt)
   {
      String SourceFile = coord.getFile();
      int SourceLine = coord.getLine();
      FindErrorLine(coord);
      System.out.println(SourceFile + ", line " + SourceLine + ": " + txt);
      ReportedErrorPos = coord;
      ReportedErrorMsg = txt;

      throw new ReportedError();
   }

   private static void internalSyntaxError(Coordinate coord, String txt)
   {
      String SourceFile = coord.getFile();
      int SourceLine = coord.getLine();
      FindErrorLine(coord);
      System.out.println(SourceFile + ", line " + SourceLine + ": " + txt);
      ReportedErrorPos = coord;

      throw new ErrorReportedByParser();
   }

   private static void FindErrorLine(Coordinate coord)
   {
      String SourceFile = coord.getFile();
      int SourceLine = coord.getLine();
      int SourceOffset = coord.getOffset();

      BufferedReader br = null;
      try {

         String sCurrentLine;

         br = new BufferedReader(new FileReader(SourceFile));

         int linecount = 0;
         int lineoffset = 0;
         while ((sCurrentLine = br.readLine()) != null) {
            linecount++;
            if (linecount == SourceLine) {
               int errorcol = SourceOffset - lineoffset + 1;
               System.out.println(sCurrentLine);
               for (int i = 1; i < errorcol; i++) {
                  if (sCurrentLine.charAt(i - 1) == '\t') {
                     System.out.print("\t");
                  } else {
                     System.out.print(" ");
                  }
               }
               System.out.println("^");
               break;
            }
            int linelength = sCurrentLine.length();
            lineoffset = lineoffset + linelength + 1;
         }

      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            if (br != null)
               br.close();
         } catch (IOException ex) {
            ex.printStackTrace();
         }
      }
   }

}
