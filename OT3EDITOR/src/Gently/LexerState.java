
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class LexerState
{
   static int COUNT = 0;

   public int INDEX = ++COUNT;

   public int SourceLine = 1;
   public int SourceOffset = 0;
   public JavaNode yylval;

   public static int LEXERSTATE_SourceLine;
   public static int LEXERSTATE_SourceOffset;
   public static JavaNode LEXERSTATE_yylval;

}
