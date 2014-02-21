
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class Nil<T extends JavaNode> extends Seq<T>
{

   public Nil()
   {
      Name = "?nil?";
      Child1 = new JavaNode[] {};
   }

   public Nil(String tag, JavaNode[] args)
   {
      Name = tag;
      Child1 = args;
   }

}
