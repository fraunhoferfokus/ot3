import de.fraunhofer.fokus.ttcn.framework.Framework;
import org.etsi.ttcn.tri.*;

public class Listener
{
   static Thread ListenerThread;

   public static void Listen(final TriPortId savedPortId)
   {
      try {

         Thread Th = new Thread() {
            public void run() {
            //........................................
            targetcode.Instructions.StartThread();


            while (true) {

            String inmsg = Communication.RECEIVE();
            if (inmsg == null) {
               targetcode.Instructions.EndThread();
               return;
            }

            byte[] bytes = ConvertStringToByteArray(inmsg);

            TriCommunicationTE tricom =
               Framework.GetTriCommunicationTE();

       TriPortId tsiPortId = savedPortId;
       TriAddress sutAddress = null;
       TriComponentId componentId = null;
       TriMessage receivedMessage = Framework.GetFactory().TriMessage();
       receivedMessage.setEncodedMessage(bytes);

    tricom.triEnqueueMsg(
       tsiPortId,
       sutAddress,
       componentId,
       receivedMessage
    );

               }
            //targetcode.Instructions.EndThread();
            //........................................
            }
         };

         ListenerThread = Th;

         Th.start();


      }
      catch (Exception e) {
         System.err.println("Error in Listener");
         System.exit(-1);
      }
   }

   private static byte[] ConvertStringToByteArray(String str)
   {
      try {
      byte[] bytes = str.getBytes("UTF8");
          return bytes;
      } catch (Exception e) {
          return null;
      }
   }

}
