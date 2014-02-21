package targetcode;

import java.util.Hashtable;

//import de.fraunhofer.fokus.ttcn.framework.Framework;
//import de.fraunhofer.fokus.ttcn.tci.*;
import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;
import targetcode.Trace;

public class Timer implements Value, TriPlatformPA
{
   public Timer()
   {
      Trace.print("Timer created");
   }

   //--- Value methods

   public Type
   getType()
   {
      return null;
   }
   public boolean
   notPresent()
   {
      return false;
   }
   public String
   getValueEncoding()
   {
      return "";
   }

   public String
   getValueEncodingVariant()
   {
      return "";
   }

   long Started;
   long Duration;

   public void StartTimer(double d)
   {
      Started = System.nanoTime()/100000;
      Duration = (int) (d*10000.0);
      //System.out.println("d = "+d);
      //System.out.println("Duration = "+Duration);
      //System.out.println("Started at:");
      //Jetzt();
   }

   public static void Jetzt()
   {
      java.text.SimpleDateFormat df =
         new java.text.SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.S" );
      java.util.Date dt = new java.util.Date();
      System.out.println( "Date = " + df.format( dt ) );
   }

   public boolean Running()
   {
      long now = System.nanoTime()/100000;
      long delta = now - Started;
      boolean res = Duration > delta;
      return res;
   }
   
   @Override
   public TriStatus triPAReset() {
	   return null;
   }
   
   @Override
   public TriStatus triStartTimer(TriTimerId timerId,
           TriTimerDuration timerDuration) {
	   StartTimer((int)timerDuration.getDuration());
	   return null;
   }
   
   @Override
   public TriStatus triStopTimer(TriTimerId timerId) {
	   return null;
   }
   
   @Override
   public TriStatus triReadTimer(TriTimerId timerId,
           TriTimerDuration elapsedTime) {
	   return null;
   }
   
   @Override
   public TriStatus triTimerRunning(TriTimerId timerId,
           TriBoolean running) {
	   running.setBooleanValue(Running());
	   return null;
   }
   
   @Override
   public TriStatus triExternalFunction(TriFunctionId functionId,
           TriParameterList parameterList, TriParameter returnValue) {
	   return null;
   }
}
