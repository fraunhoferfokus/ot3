
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class SemanticActions
{
   public static void TraceAction()
   {
   }

   // === Parser Actions ===

   // ....................................................................
   // called in semantic actions of parser :
   // SourceRange //1
   // SourceRangeEmpty //2
   // ....................................................................

   // Source Range
   // ------------
   // called in semantic action of generated parser
   // m : m1 ... mn {
   // x = Node (...);
   // SourceRange($1, $n, x);
   // $$ = x;
   // }
   static LexerState Voriger = null;

   public static void SourceRange(JavaNode x1, JavaNode x2, JavaNode x,
         LexerState PEgon, int Folding)
   // 1
   {

      int line1 = x1.getCoordinate().getLine();
      int offset1 = x1.getCoordinate().getOffset();
      int length1 = x1.getCoordinate().getLength();
      int offset2 = x2.getCoordinate().getOffset();
      int length2 = x2.getCoordinate().getLength();

      int line = line1;
      int offset = offset1;
      int length = offset2 + length2 - offset1;

      if (Voriger == null)
         Voriger = PEgon;

      x.setCoordinate(new Coordinate(TokenStream.CurrentFile, line, offset,
            length));

      x.SetChildPositions();

      if (x.folding != 1)
         x.folding = Folding;

      Voriger = PEgon;
   }

   // SourceRangeEmpty
   // ----------------
   // called in semantic action of generated parser
   // see SourceRange
   // used for empty rhs
   public static void SourceRangeEmpty(JavaNode x, LexerState PEgon)
   {
      x.setCoordinate(new Coordinate(TokenStream.CurrentFile, PEgon.SourceLine,
            PEgon.SourceOffset, 0 // length
      ));

   }

   // === Lexer Actions ===

   // Yywhitespace
   // ------------
   // called in semantic action of generated lexer rules for whitespace
   // e.g.
   // " " {
   // Yywhitespace(yytext);
   // }
   public static void Yywhitespace(String str, LexerState LEgon)
   {
      final int len = str.length();

      CheckForEol(str, LEgon);

      JavaStringValue node = new JavaStringValue(str);

      // int offset = Egon.SourceOffset-len;
      int offset = LEgon.SourceOffset - len;
      TokenStream.STARTOFFSET = offset;

      node.setCoordinate(new Coordinate( // COORD 1 YyWhitespace (Lexer)
            TokenStream.CurrentFile, LEgon.SourceLine, offset, len));

      LEgon.yylval = node;
      LexerState.LEXERSTATE_yylval = node;

   }

   // Yyunmatched
   // -----------
   // called in semantic action of generated lexer rule
   // for unmatched input
   // e.g.
   // . {
   // Yyunmatched(yytext);
   // }
   public static void Yyunmatched(String str, LexerState LEgon)
   {
      final int startoffset = LEgon.SourceOffset;// +1;
      final int len = str.length();

      TokenStream.STARTOFFSET = startoffset;

      CheckForEol(str, LEgon);

      JavaStringValue node = new JavaStringValue(str);

      int offset = LEgon.SourceOffset - len;
      node.setCoordinate(new Coordinate( // COORD 2 Yytext (Lexer)
            TokenStream.CurrentFile, LEgon.SourceLine, startoffset, // offset,
            len));

      LEgon.yylval = node;
      LexerState.LEXERSTATE_yylval = node;

   }

   // Yytext
   // ------
   // called in semactic actions of lex rules for matched tokens
   // e.g.
   // "abc" {
   // yylval = Yytext(yytext);
   // return tokencode;
   // }
   public static JavaNode Yytext(String str, LexerState LEgon)
   {

      final int startoffset = LEgon.SourceOffset;// +1;
      final int len = str.length();

      TokenStream.STARTOFFSET = startoffset;

      CheckForEol(str, LEgon);

      JavaStringValue node = new JavaStringValue(str);

      node.setCoordinate(new Coordinate( // COORD 2 Yytext (Lexer)
            TokenStream.CurrentFile, LEgon.SourceLine, startoffset, // offset,
            len));

      LEgon.yylval = node;
      LexerState.LEXERSTATE_yylval = node;

      return node;
   }

   // see value converter
   public static void AssignYylval(JavaNode node, LexerState Egon)
   {
      Egon.yylval = node;
      LexerState.LEXERSTATE_yylval = node;
   }

   private static void CheckForEol(String str, LexerState LEgon)
   {
      final int len = str.length();

      for (int i = 0; i < len; i++) {
         char ch = str.charAt(i);

         LEgon.SourceOffset++; // NEXTCOL 1 YyWhitespace
         LexerState.LEXERSTATE_SourceOffset++;

         if (ch == '\n') {
            LEgon.SourceLine++; // NEXTLINE 1 YyWhitespace
            LexerState.LEXERSTATE_SourceLine++;
         }
      }

   }

}
