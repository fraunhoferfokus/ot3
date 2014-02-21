import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class Commands implements ActionListener {
   public static ActionListener Ich = new Commands();

   public void actionPerformed(ActionEvent event) {
      String command = event.getActionCommand();
      DoCommand(command);
   }

   public static void CommandSaveAndExit() {
      Gui.Display("Exit");

      StatusFile.WriteStatusFile();
      Mark.WriteMarkFile();
      Configure.WriteAdapterFile();
      Configure.WriteCodecFile();
      

      // try { Thread.sleep(5000); } catch (Exception e) {}

      System.exit(0);
   }

   // private

   private void DoCommand(String command) {

      // TESTS

      if (command.equals("Run All")) {
         GenericRunner.GuiRunAllTests();

      } else if (command.equals("Run")) {
      //System.out.println("qq vor run");
    	  GenericRunner.GuiRunMarkedTest();
      //System.out.println("qq hinter run");
      }
      
      // MARK
      
      else if (command.equals("Mark")) {
         int rowindex = Gui.table.getSelectedRow();
         Mark.MarkTestWithIndex(rowindex);
         
      }

      // CURSOR
      else if (command.equals("CursorUp")) {
         int rowindex = Gui.table.getSelectedRow();
         Gui.ScrollTable(rowindex - 1);

      } else if (command.equals("CursorDown")) {
         int rowindex = Gui.table.getSelectedRow();
         Gui.ScrollTable(rowindex + 1);
      }

      // MOVE
      else if (command.equals("Rauf")) {
         int rowindex = Gui.table.getSelectedRow();
         TableModel.Swap(rowindex, -1);
         Gui.ScrollTable(rowindex - 1);

      } else if (command.equals("Runter")) {
         int rowindex = Gui.table.getSelectedRow();
         TableModel.Swap(rowindex, +1);
         Gui.ScrollTable(rowindex + 1);
      }

      // REFRESH
      else if (command.equals("Stop")) {
         GenericRunner.CommandStop();

      }
      else if (command.equals("Stop All")) {
         GenericRunner.CommandStopAll();

      }
      
   // Clear
      else if (command.equals("Clear")) {
         TableModel.ClearVerdicts();

      }


      
   // Adapter
      else if (command.equals("Configure")) {
         Configure.CommandConfigure();

      }


      // EXIT
      else if (command.equals("Exit")) {
         boolean b = GenericRunner.ForceStop();
         if (b) {
        	 CommandSaveAndExit();
         }

      } else {
         Gui.Display("Command: Unknown");
      }
   }
   
   

  
}
