package targetcode;

//import java.util.ArrayList;
//import java.util.Hashtable;
//import java.util.List;

//import de.fraunhofer.fokus.ttcn.framework.Framework;
//import de.fraunhofer.fokus.ttcn.tci.*;
import de.fraunhofer.fokus.ttcn.tri.*;
import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

import targetcode.Trace;

public class CompoundValue
   implements
   Value
   // Value : to make it assignable , Comp := CompType.create
{

   public Value[] vals;

   public CompoundValue (Value[] Vs)
   {
      vals = Vs;
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

}
