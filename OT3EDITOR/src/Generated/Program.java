package Generated;
public class Program
{
   public static void main (String argv[])
   {
      Gently.JavaLib.SaveProgramArguments(argv);
      try {
         Generated.YyProcs.Start();
      }
      catch (Error E) {
         System.exit(-1);
      }
   }
}
