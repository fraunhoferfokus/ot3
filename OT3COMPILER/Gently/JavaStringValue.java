
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

//
// cyan.compilertools.net
//
// This is a beta release for personal use
//
// Distributed by Metarga GmbH, Berlin
// Copyright (C) 2001-2012
//

package Gently;

public class JavaStringValue extends JavaNode
{
   public String Str;
   
   public JavaStringValue(String s)
   {
      Name = "%string%";
      Str = s;
   }
   public void DumpI(String indent)
   {
      System.out.println
      (indent + "JavaStringValue {\"" + Name + "\", \"" + Str + "\"}");
   }

   public static boolean MatchesString(JavaNode v, String s)
   {
      if (! v.Name.equals("%string%") ) return false;
      
      JavaStringValue sv = (JavaStringValue) v;
      return sv.Str.equals(s);
   }
   
   @Override
   public boolean Eq(JavaNode x)
   {
	   return Str == ((JavaStringValue) x).Str;
   }
}   
