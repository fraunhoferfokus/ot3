package android.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class Server extends Activity {
	
	static ServerSocket serverSocket = null;
	static Socket clientSocket = null;
	static BufferedReader READER;
	static PrintWriter WRITER;

    AnimationDrawable coffeeAnimation;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server);
		
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {

				while (true) {

					Log.d("testing","waiting for connection ...");
					OPEN();
					Log.d("testing","connected");

					COMMUNICATE();

					CLOSE();

				}

			}

		});
		thread.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_server, menu);
		return true;
	}
	
	
	   void COMMUNICATE()
	   {
	      final int price = 50;
	      int amount;


	      amount = 0;
	      Log.d("testing","amount = " + amount);

	      while(true) {

	         Log.d("testing","waiting for message ...");
	         String str = RECEIVE();
	         if (str == null) {
	            Log.d("testing","communication terminated");
	            return;
	         }   

	         Log.d("testing","received '" + str + "'");

	         int i;
	         try {
	            i = Integer.parseInt(str);
	         }
	         catch (Exception E) {
	            i = 0;
	         }

	         amount = amount+i;
	         Log.d("testing","amount = " + amount);

	         while (amount >= price) {
                 animate(true);
                 try {
                     Thread.sleep(5000); // delay between two cups of coffee
                 } catch (Exception e) {

                 }
                 animate(false);
	        	 
 	             SEND("coffee");
 	             Log.d("testing","sent 'coffee'");
 	             amount = amount-price;
 	             Log.d("testing","amount = " + amount);
                 try {
                     Thread.sleep(1000); // delay between two cups of coffee
                 } catch (Exception e) {
                 	
                 }



	         }
	      }
	   }

	   static void OPEN()
	   {
		   for (;;) {
			   try {
				   //serverSocket = new ServerSocket(4444);
				   serverSocket = new ServerSocket(8000);
				   
				   clientSocket = serverSocket.accept();

				   WRITER = new PrintWriter(clientSocket.getOutputStream(), true);

				   READER = new BufferedReader(
						   new InputStreamReader(
							clientSocket.getInputStream()));
				   break;
			   }
			   catch (Exception e) {
				   Log.d("testing","Error in OPEN: exit");
				   e.printStackTrace();
				   System.exit(1);
			   }
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
	         Log.d("testing","Error in CLOSE : exit");
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
	    	  inmsg = null;
	         inmsg = READER.readLine();
	      }
	      catch (Exception e) {
	         inmsg = null;
	      }
	      return inmsg;
	   }
	    
	    private void animate(boolean start) {

	        ImageView coffee = (ImageView) findViewById(R.id.imageView1);
	        coffeeAnimation = (AnimationDrawable) coffee.getDrawable();
	        
	        if (coffeeAnimation.isRunning() && !start) {
	            runOnUiThread(new Runnable(){
	                public void run(){
	                    coffeeAnimation.stop();
	                }
	            });
	            Log.d("Testing", "animation stopped");
	            //b.setText("Start");
	        } else if (!coffeeAnimation.isRunning() && start) {
	            runOnUiThread(new Runnable(){
	                public void run(){
	                    coffeeAnimation.start();
	                }
	            });
	            Log.d("Testing", "animation started");
	            //b.setText("Stop");
	        }
	    }
	    
	    
}
