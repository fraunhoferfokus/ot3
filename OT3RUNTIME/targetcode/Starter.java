package targetcode;

// framework
import de.fraunhofer.fokus.ttcn.framework.Framework;

// tri
import org.etsi.ttcn.tri.QualifiedName;
import org.etsi.ttcn.tri.TriCommunicationSA;

// tci
import org.etsi.ttcn.tci.TciModuleId;
import org.etsi.ttcn.tci.TciTMRequired;
import org.etsi.ttcn.tci.TciCDProvided;

// tci impl
import de.fraunhofer.fokus.ttcn.tci.QualifiedNameImpl;
import de.fraunhofer.fokus.ttcn.tci.TciModuleIdImpl;

// targetcode
import targetcode.XciTMRequiredImpl;
import targetcode.MetaModule;
import targetcode.SystemSetup;
import targetcode.Trace;

public class Starter
{
   public static void main(String[] args)
   {
      Trace.print("Starter.main started.");
      

      String root = args[0];
      String adapter = "";
      String codec = "";

      if (args.length == 3) {
         adapter = args[1];
         codec = args[2];
      }

      try {

         SystemSetup.Setup1();

      if (adapter != "") {
         Class kl = Class.forName(adapter);
         Object obj = kl.newInstance();
         TriCommunicationSA Adapter = (TriCommunicationSA) obj;
         Framework.SetTriCommunicationSA(Adapter);
      }

      if (codec != "") {
         Class kl = Class.forName(codec);
         Object obj = kl.newInstance();
         TciCDProvided Codec = (TciCDProvided) obj;
         Framework.SetTciCDProvided(Codec);
      }

         SystemSetup.Setup2();

      if (root != "") {
         Class kl = Class.forName(root);
         Object obj = kl.newInstance();
         MetaModule metaabc = (MetaModule) obj;


         metaabc.RunControlPart();
      }

      }
      catch (Exception e)
      {
         Trace.print("oops: " + e);
      }

      Trace.print("Starter.main end");
   }
}
