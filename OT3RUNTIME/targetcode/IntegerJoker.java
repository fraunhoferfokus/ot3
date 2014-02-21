package targetcode;

import de.fraunhofer.fokus.ttcn.tri.*;
import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

import targetcode.Trace;

public class IntegerJoker implements Value
{
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

}
