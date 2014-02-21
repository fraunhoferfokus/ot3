package CoffeeServer;

//import animation.Animator;
//import animation.SwingAnimation;
import java.net.*;
import java.io.*;

public class Server
{
   static ServerSocket serverSocket = null;
   static Socket clientSocket = null;
   static BufferedReader READER;
   static PrintWriter WRITER;
   //static Animator anim;
   //SwingAnimation anim;

   public static void main(String[] args)
   {
	   /*
	  anim = new Animator();
	  anim.init();
	  */
	  SwingAnimation anim = new SwingAnimation();
      while (true) {


         //System.out.println("waiting for connection ...");
         OPEN();
         //System.out.println("connected");

         COMMUNICATE(anim);

         CLOSE();

      }
   }

   static void COMMUNICATE(SwingAnimation anim)
   {
      final int price = 50;
      int amount;


      amount = 0;
      //System.out.println("amount = " + amount);

      while(true) {

         //System.out.println("waiting for message ...");
         String str = RECEIVE();
         if (str == null) {
            //System.out.println("communication terminated");
            return;
         }   

         //System.out.println("received '" + str + "'");

         int i;
         try {
            i = Integer.parseInt(str);
         }
         catch (Exception E) {
            i = 0;
         }

         amount = amount+i;
         //System.out.println("amount = " + amount);

         while (amount >= price) {
        	// animation
        	//anim.run();
        	anim.SwingAnimator();
            SEND("coffee");
            //System.out.println("sent 'coffee'");
            amount = amount-price;
            //System.out.println("amount = " + amount);
         }
      }
   }

   static void OPEN()
   {
      try {
         //serverSocket = new ServerSocket(4444);
         serverSocket = new ServerSocket(8000);
         clientSocket = serverSocket.accept();

         WRITER = new PrintWriter(clientSocket.getOutputStream(), true);

         READER = new BufferedReader(
         new InputStreamReader(
         clientSocket.getInputStream()));
      }
      catch (Exception e) {
         System.out.println("Error in OPEN: exit");
         System.exit(1);
      }
   }                        

   static void CLOSE()
   {
      try {
         WRITER.close();
         READER.close();
         clientSocket.close();
         serverSocket.close();
      }
      catch (Exception e) {
         System.out.println("Error in CLOSE : exit");
         System.exit(1);
      }
   }

   static void SEND(String outmsg)
   {
      WRITER.println(outmsg);
   }

   static String RECEIVE()
   {
      String inmsg;
      try {
         inmsg = READER.readLine();
      }
      catch (Exception e) {
         inmsg = null;
      }
      return inmsg;
   }

}
