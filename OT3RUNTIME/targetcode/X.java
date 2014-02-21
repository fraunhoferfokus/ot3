package targetcode;

public class X
{
   public static void P(String str)
   {
      System.out.println(str);
   }

   public static void Assert(boolean b)
   {
      if (! b) {
         System.out.println("[Assertion violated]");
         //throw new Error("[Error]");
      }
   }
}
