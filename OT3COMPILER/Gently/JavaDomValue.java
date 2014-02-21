
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

import java.util.*;

public class JavaDomValue extends JavaNode
{
   private int Val;

   public JavaDomValue(String domain)
   {
      Name = "%dom%";
      KeyIndex++;
      Val = KeyIndex;
   }

   private Map<String, JavaNode> Fields = new HashMap<String, JavaNode>();
   private static int KeyIndex = 0;

   public int GetKey()
   {
      return Val;
   }

   public void PutField(String Field, JavaNode Value)
   {
      Fields.put(Field, Value);
   }

   public JavaNode GetField(String Field)
   {
      return Fields.get(Field);
   }
}
