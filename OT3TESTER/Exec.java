//import java.util.*;
import java.io.*;



class Exec
{
    public static Process RUNNING = null;
    
    // needs StreamGobbler

    public static void Do(File dir)
    {
        
        try
        {            
            //FileOutputStream fos = new FileOutputStream("redirected.txt");
            Runtime rt = Runtime.getRuntime();

            //Process proc = rt.exec("echo 'Hello World'");

            String Shell;
            String OS = System.getProperty("os.name");
            if (OS.equals("Windows 7")) {
            	Shell = "/cygwin/bin/sh.exe";
            }
            else {
            	Shell = "sh";
            }
            
            String ScriptFile = "../" + OT3TESTER.Script;
           
            
            Process proc = rt.exec(new String[]{Shell, ScriptFile}, null, dir);

            // any error message?
            StreamGobbler errorGobbler = new 
                StreamGobbler(proc.getErrorStream(), "ERROR");            
            
            // any output?
            StreamGobbler outputGobbler = new 
                //StreamGobbler(proc.getInputStream(), "OUTPUT", fos);
            StreamGobbler(proc.getInputStream(), "OUTPUT");
                
            // kick them off
            errorGobbler.start();
            outputGobbler.start();
                                    
            // destroy
            RUNNING = proc;
            Destroyer Dest = new Destroyer(proc);
            Dest.start();
            //proc.destroy();

            // any error???
            int exitVal = proc.waitFor();
            RUNNING = null;
            //System.out.println("ExitValue: " + exitVal);
            //fos.flush();
            //fos.close();        
        } catch (Throwable t)
          {
            t.printStackTrace();
          }
    }
    
    
    public static void DoCmd(String[] cmd, File dir)
    {
        
        try
        {            
            //FileOutputStream fos = new FileOutputStream("redirected.txt");
            Runtime rt = Runtime.getRuntime();

          //Process proc = rt.exec(cmd);

            
           
            
            Process proc = rt.exec(cmd, null, dir);

            // any error message?
            StreamGobbler errorGobbler = new 
                StreamGobbler(proc.getErrorStream(), "ERROR");            
            
            // any output?
            StreamGobbler outputGobbler = 
                //new StreamGobbler(proc.getInputStream(), "OUTPUT", fos);
                new StreamGobbler(proc.getInputStream(), "OUTPUT");
                
            // kick them off
            errorGobbler.start();
            outputGobbler.start();
                                    
            // destroy
            RUNNING = proc;
            Destroyer Dest = new Destroyer(proc);
            Dest.start();
            //proc.destroy();

            // any error???
            int exitVal = proc.waitFor();
            RUNNING = null;
            //System.out.println("ExitValue: " + exitVal);
            //fos.flush();
            //fos.close();        
        } catch (Throwable t)
          {
            t.printStackTrace();
          }
    }
}
