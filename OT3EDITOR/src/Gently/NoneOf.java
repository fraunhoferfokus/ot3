
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class NoneOf<T extends JavaNode> extends Option<T>
{
   public NoneOf(JavaNode[] args)
   {
      Name = "?nil?";
   }

   public NoneOf(String tag, JavaNode[] args)
   {
      Name = tag;
      Child1 = new JavaNode[] {};
   }
}
