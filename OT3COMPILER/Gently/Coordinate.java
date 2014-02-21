
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class Coordinate

{
   private String file;
   private int line;
   private int offset;
   private int length;

   public Coordinate(String fil, int lin, int offs, int len)
   {
      file = fil;
      line = lin;
      offset = offs;
      length = len;
   }

   public String getFile()
   {
      return file;
   }

   public int getLine()
   {
      return line;
   }

   public int getOffset()
   {
      return offset;
   }

   public int getLength()
   {
      return length;
   }
}
