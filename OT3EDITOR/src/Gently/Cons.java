
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class Cons<T extends JavaNode> extends Seq<T>
{

   public Cons(T hd, Seq<T> tl)
   {
      Name = tl.Name;
      Child1 = new JavaNode[] { hd, tl };
   }

   public Cons(String tag, JavaNode[] arr)
   {
      Name = tag;
      Child1 = arr;
   }

   public T getHead()
   {
      return (T) (Child1[0]);
   }

   public Seq<T> getTail()
   {
      return (Seq<T>) (Child1[1]);
   }

}
