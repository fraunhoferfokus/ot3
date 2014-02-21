
import de.fraunhofer.fokus.ttcn.framework.Framework;
import de.fraunhofer.fokus.ttcn.framework.BaseAdapter;
import org.etsi.ttcn.tri.*;

public class Adapter extends BaseAdapter
{


   @Override
   public TriStatus triMap(TriPortId compPortId, TriPortId tsiPortId)
   {
      if (compPortId.getPortName() == "InputPort") { 
         Communication.OPEN();
         Listener.Listen(compPortId);
      }
      return Framework.TriStatusOk;
   }

   @Override
   public TriStatus triUnmap(TriPortId compPortId, TriPortId tsiPortId)
   {
      if (compPortId.getPortName() == "InputPort") {
         Communication.CLOSE();
      }
      return Framework.TriStatusOk;
   }

   @Override
   public TriStatus triSend(TriComponentId componentId, TriPortId tsiPortId,
      TriAddress sutAddress, TriMessage sendMessage)
   {
      byte[] bytes = sendMessage.getEncodedMessage();
      String str = ConvertByteArrayToString(bytes);

      try {
         Communication.SEND(str);
      } catch (Exception e) {
         return Framework.TriStatusError;
      }
      return Framework.TriStatusOk;
   }


   private static String ConvertByteArrayToString(byte[] bytes)
   {
      try {
      String str = new String(bytes, "UTF-8");
      return str;
      } catch (Exception e) {
          return null;
      }
   }

}
