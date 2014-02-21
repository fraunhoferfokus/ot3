import targetcode.Printer;

public class TtcnLogger extends Printer {
   //@Override
   public void Log(String str)
   {
      Gui.Display("Log: " + str);
   }
}
