
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class NodeFactory
{

   public static JavaNode NewNode(String str, JavaNode[] n)
   {
      JavaNode node;

      node = new JavaNode(str, n);
      return node;
   }

   public static JavaNode NewAttributedNode(String str, JavaNode[] n,
         int AttrCount)
   {
      JavaNode node;

      node = new AttributedJavaNode(str, n);
      return node;
   }

}
