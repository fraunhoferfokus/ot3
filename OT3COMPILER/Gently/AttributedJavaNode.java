
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class AttributedJavaNode extends JavaNode

{
   public JavaNode[] Child2 = new JavaNode[3];

   public AttributedJavaNode(String str, JavaNode... n)
   {
      Name = str;
      Child1 = n;
   }

}
