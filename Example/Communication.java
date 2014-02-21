import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Communication
{
   static Socket SOCKET;
   static PrintWriter WRITER;
   static BufferedReader READER;
   
   static boolean ConnectionOk = false;
   
   public static void OPEN()
   {
      try {

         SOCKET = new Socket("localhost", 8000);
         //SOCKET = new Socket("localhost", 4444);
         WRITER = new PrintWriter(SOCKET.getOutputStream(), true);
         READER = new BufferedReader
            (new InputStreamReader(SOCKET.getInputStream()));
         ConnectionOk = true;
      }
      catch (Exception e) {
         System.err.println("Error in OPEN\n" + e);
         ConnectionOk = true;
      }
   }

   public static void CLOSE()
   {
      try {
         WRITER.close();
         READER.close();
         SOCKET.close();
      }
      catch (Exception e) {
         System.err.println("Exception in CLOSE");
      }
      ConnectionOk = false;
   }

   public static void SEND(String outmsg)
   {
      if (ConnectionOk) {
    	  WRITER.println(outmsg);
      }
      else {
    	  System.out.println("Send: Not Connected");
      }
   }

   public static String RECEIVE()
   {
	   if (ConnectionOk) {
      try {

         return READER.readLine();

      }
      catch (Exception e) {
         return null;
      }
	   }
	   else {
		   System.out.println("Receive: Not connected");
		   return null;
	   }
   }
}
