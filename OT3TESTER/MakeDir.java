import java.io.*;

public class MakeDir
{
   public static File MkDir(String name)
   {
      File file = new File(name);
      if (!file.exists()) {
         if (file.mkdir()) {
            // System.out.println("Directory is created!");
            return file;
         }
         else {
            GenericRunner.RuntimeError("MkDir "+name);
            return null;
         }
      }
      else {
         return file;
      }
   }
}
