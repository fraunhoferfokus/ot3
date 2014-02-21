package targetcode;

import org.etsi.ttcn.tri.TriCommunicationSA;
import org.etsi.ttcn.tci.TciCDProvided;
import org.etsi.ttcn.tci.TciTMProvided;
import org.etsi.ttcn.tci.TciTMRequired;
import de.fraunhofer.fokus.ttcn.framework.Framework;

public class SystemSetup
{
   public static TriCommunicationSA Adapter;
   public static TciCDProvided Codec;

   public static TciTMProvided UserTM; // provided by user, called by system
   public static TciTMRequired SystemTM; // provided by system, called by user

   public static void Setup1() // called before LocalSetup.Setup
   {

      // xxx
      Framework.SetTriCommunicationTE( new SpecialCommunicationTE() );

      //
      UserTM = new XciTMProvidedImpl();
      SystemTM = new XciTMRequiredImpl();
      Framework.SetTciTMProvided( UserTM );
      Framework.SetTciTMRequired( SystemTM );
   }

   public static void Setup2() // called after LocalSetup.Setup
   {

      Adapter = Framework.GetTriCommunicationSA();
      Codec = Framework.GetTciCDProvided();
   }
}
