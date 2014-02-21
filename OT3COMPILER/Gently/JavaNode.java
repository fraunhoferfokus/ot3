
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class JavaNode

{
   public void foo()
   {
      foo();
   }

   // fields

   public String Name = "/noname/";

   public String getName()
   {
      return Name;
   }

   public JavaNode[] getChildren()
   {
      return Child1;
   }

   public JavaNode[] Child1 = NoNodes;

   public Coordinate coord = null;

   public int folding = -1;

   // static

   private static JavaNode[] NoNodes = new JavaNode[] {};

   // ---------------------------------------------------------------

   public JavaNode()
   {
   }

   // ---------------------------------------------------------------

   public void Log()
   {
      JavaLib.Log(this);
   }

   // ---------------------------------------------------------------

   public JavaNode(String str, JavaNode... n) // CONS
   {
      Name = str;
      Child1 = n;
   }

   // ---------------------------------------------------------------

   public boolean Matches(String tag)
   {
      return Name == tag;
   }

   // ---------------------------------------------------------------

   public void setCoordinate(Coordinate c)
   {
      coord = c;
   }

   public Coordinate getCoordinate()
   {
      return coord;
   }

   // ---------------------------------------------------------------

   // @Override() // ITypedRegion
   public String getType()
   {
      return Name;
   }

   // ---------------------------------------------------------------

   public void Print()
   {
      System.out.println("[cannot print this value (" + Name + ")]");
   }

   // ---------------------------------------------------------------

   public void SetChildPositions()
   {
      for (JavaNode ch : Child1) {
         if (ch.coord == null) {
            ch.coord = coord;

            ch.SetChildPositions();
         }
      }
   }

   // ---------------------------------------------------------------

   public boolean Eq(JavaNode x)
   {
      if (Name.compareTo(x.Name) != 0)
         return false;
      for (int i = 0; i < Child1.length; i++) {
         if (!Child1[i].Eq(x.Child1[i]))
            return false;
      }
      return true;
   }

   // ---------------------------------------------------------------

   public static JavaNode getChildNearOffset(JavaNode parent, int offs)
   {
      if (parent.Child1.length > 0) {
         JavaNode result = parent;
         for (int i = 0; i < parent.Child1.length; i++) {
            int OFFSET = parent.Child1[i].getCoordinate().getOffset();
            if (OFFSET > offs)
               // if (parent.Child1[i].getOffset()>offs)
               return result;
            result = getChildNearOffset(parent.Child1[i], offs);
         }
         return result;
      }
      return parent;
   }
   // ---------------------------------------------------------------

}
