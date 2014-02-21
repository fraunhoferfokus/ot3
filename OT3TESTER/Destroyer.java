

class Destroyer extends Thread
{
    
    // a Destroyer process
	// kills the argument process p
	// after some time
	
	// used to stop a test
	
	private final Process proc;
	
    public Destroyer(Process p)
    {
       proc = p;
    }
    
    public void run()
    {
    	int X = 100; //30;
       for (int i = 1; i <= 30; i++) {

          if (Exec.RUNNING != proc) {
             return;
          }

          try {
             Thread.sleep(500);
          } catch (Exception e) {}

       }

       if (Exec.RUNNING == proc) {
          System.out.println("ausgeschlafen, destroy !!!!!!!!!!!!!!!");
          proc.destroy();
       }
       else {
          System.out.println("doch noch zuende !!!!!!!!!");
       }
    }
}
