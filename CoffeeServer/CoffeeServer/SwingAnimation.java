package CoffeeServer;

import java.awt.*;
import javax.swing.*;

public class SwingAnimation{
	ImageIcon images[] = new ImageIcon[10];
	JFrame frame;
	JLabel lbl;
	int i = 0;
	int j;

	public SwingAnimation(){
		frame = new JFrame("Animation Frame");
		lbl = new JLabel();
		Panel panel = new Panel();
		panel.add(lbl);
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    images[0] = new ImageIcon(getClass().getResource("drawable/coffee0.png"));
	    images[1] = new ImageIcon(getClass().getResource("drawable/coffee1.png"));
	    images[2] = new ImageIcon(getClass().getResource("drawable/coffee2.png"));
	    images[3] = new ImageIcon(getClass().getResource("drawable/coffee3.png"));
	    images[4] = new ImageIcon(getClass().getResource("drawable/coffee4.png"));
	    images[5] = new ImageIcon(getClass().getResource("drawable/coffee5.png"));
	    images[6] = new ImageIcon(getClass().getResource("drawable/coffee6.png"));
	    images[7] = new ImageIcon(getClass().getResource("drawable/coffee7.png"));
	    images[8] = new ImageIcon(getClass().getResource("drawable/coffee8.png"));
	    images[9] = new ImageIcon(getClass().getResource("drawable/coffee9.png"));	
		lbl.setIcon(images[0]);
	}

	public void SwingAnimator(){
		try{
			for(i = 0; i <= 9; i++){
				lbl.setIcon(images[i]);
				Thread.sleep(300);
			}
			lbl.setIcon(images[0]);
		}
		catch(InterruptedException e){
		    e.printStackTrace();
		}
	}
}
