
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

public class OneOf<T extends JavaNode> extends Option<T>
{

   public OneOf(T hd, Seq<T> tl)
   {
      Name = tl.Name;
      Child1 = new JavaNode[] { hd, tl };

   }

   public T getHead()
   {
      return (T) (Child1[0]);
   }

   public Seq<T> getTail()
   {
      return (Seq<T>) (Child1[1]);
   }

   public OneOf(String tg, JavaNode[] arr)
   {
      Name = tg;
      Child1 = arr; // new JavaNode[] { arr[0], arr[1] };
   }
}
