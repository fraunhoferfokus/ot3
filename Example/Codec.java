import de.fraunhofer.fokus.ttcn.framework.Framework;
import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

public class Codec implements TciCDProvided {

   @Override
   public TriMessage encode(Value value)
   {

      Type type = value.getType();
      int typeclass = type.getTypeClass();

      if (typeclass == TciTypeClass.INTEGER) {

         int i = ((IntegerValue) value).getInteger();
         byte[] bytes = ConvertIntToByteArray(i);
         TriMessage msg = Framework.GetFactory().TriMessage();
         msg.setEncodedMessage(bytes);
         return msg;

      }
      else {
         Framework.GetTciCDRequired().tciErrorReq
            ("unexpected typeclass");
         return null;
      }
   }

   @Override
   public Value decode
      (TriMessage message, Type decodingHypothesis)
   {
      int typeclass = decodingHypothesis.getTypeClass();

      if (typeclass == TciTypeClass.CHARSTRING) {

         byte[] bytes = message.getEncodedMessage();
         String str = ConvertByteArrayToString(bytes);

         CharstringValue val = Framework.GetFactory().CharstringValue();
         val.setString(str);
         return val;
      }
      else {
         Framework.GetTciCDRequired().tciErrorReq
            ("unexpected typeclass");
         return null;
      }
   }

   private static byte[] ConvertIntToByteArray(int i)
   {
      String str = String.valueOf(i);
      try {
         byte[] bytes = str.getBytes("UTF8");
         return bytes;
      } catch (Exception e) {
         return null;
      }
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
