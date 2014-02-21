package targetcode;

public class Trace
{
   static int[] Id = new int[50];
   static int N = 0;
   static int cur;

   static Printer P = new Printer();

   static String line = "";

   private static boolean TraceOption = false; //true;

   public static void setTraceOption(boolean flag)
   {
      TraceOption = true; // xxx
      TraceOption = false; // flag;
   }

   public static void setPrinter(Printer prtr)
   {
      P = prtr;
   }

   public static void Clear()
   {
      N = 0;
   }

   private static void Identify()
   {
      long n = Thread.currentThread().getId();
      Id[N] = (int) n;
      cur = 0;
      while (Id[cur] != n) {
         cur++;
      }
      if (cur == N) {
         N++;
      }   
   }

   private static void Indent()
   {
      for (int i = 0; i <= cur; i++) {
         //System.out.print("+  ");
         append("+  ");
      }
   }

   public static synchronized void print(String str)
   {
      if (! TraceOption) return;

      Identify();
      Indent();
      long id = Thread.currentThread().getId();
      String Name = Thread.currentThread().getName();
      //System.out.print("[" + id + "] ");
      //System.out.print("[" + Name + "] ");

      //System.out.print("[" + cur + "] ");
      append("[" + cur + "] ");

      //System.out.println(str);
      append(str);
      P.print(line);
      line = "";
   }

   private static void append(String str)
   {
      line = line + str;
   }
}
