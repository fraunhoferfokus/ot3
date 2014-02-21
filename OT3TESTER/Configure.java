import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Configure {

	static String the_adapter = "Adapter";
	static String the_codec = "Codec";

	public static void SetAdapter(String str) {
		the_adapter = str;
	}

	public static String GetAdapter() {
		return the_adapter;
	}

	public static void SetCodec(String str) {
		the_codec = str;
	}

	public static String GetCodec() {
		return the_codec;
	}
	public static void ReadAdapterFile() {
		BufferedReader br = null;
	      
	      try {
	         br = new BufferedReader(new FileReader("_adapter"));
	      }
	      catch (Exception E) {
	    	  return;
	      }
	      try {
	         String line = null;
	        line = br.readLine();
	        SetAdapter(line);
	        	 
	         br.close();
	      } catch (Exception E) {
	    	  return;
	      }
	      
	   }
	public static void WriteAdapterFile() {
	      try {
	         PrintWriter pw = new PrintWriter(new FileWriter("_adapter"));
	         
	         String str = GetAdapter();
	         pw.println(str);


	         pw.flush();
	         pw.close();
	      } catch (IOException ioe) {
	         ioe.printStackTrace();
	      }
	   }
	public static void ReadCodecFile() {
		BufferedReader br = null;
	      
	      try {
	         br = new BufferedReader(new FileReader("_codec"));
	      }
	      catch (Exception E) {
	    	  return;
	      }
	      try {
	         String line = null;
	        line = br.readLine();
	        SetCodec(line);
	        	 
	         br.close();
	      } catch (Exception E) {
	    	  return;
	      }
	      
	   }
	public static void WriteCodecFile() {
	      try {
	         PrintWriter pw = new PrintWriter(new FileWriter("_codec"));
	         
	         String str = GetCodec();
	         pw.println(str);


	         pw.flush();
	         pw.close();
	      } catch (IOException ioe) {
	         ioe.printStackTrace();
	      }
	   }


	public static void CommandConfigure() {

		final JTextField text_Adapter = new JTextField(10);
		text_Adapter.setText(the_adapter);
		final JTextField text_Codec = new JTextField(10);
		text_Codec.setText(the_codec);

		final JFrame frame = new JFrame();
		frame.setSize(340, 180);
		frame.setLayout(new FlowLayout());

		ActionListener lis_Cancel = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame,
						WindowEvent.WINDOW_CLOSING));
			}
		};

		ActionListener lis_Ok = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = text_Adapter.getText();
				String txt2 = text_Codec.getText();
				the_adapter = txt;
				the_codec = txt2;
				
				TtcnSuite.PrepareAdapterAndCodec();
				
				frame.dispatchEvent(new WindowEvent(frame,
						WindowEvent.WINDOW_CLOSING));
			}
		};

		JPanel p2 = new JPanel(new SpringLayout());
		JLabel l1 = new JLabel("Adapter", JLabel.TRAILING);
		p2.add(l1);

		l1.setLabelFor(text_Adapter);
		p2.add(text_Adapter);

		JLabel l2 = new JLabel("Codec", JLabel.TRAILING);
		p2.add(l2);
		l1.setLabelFor(text_Codec);
		p2.add(text_Codec);

		SpringUtilities.makeCompactGrid(p2, 2, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		JPanel p3 = new JPanel(new SpringLayout());
		JButton jb = new JButton("Cancel");
		jb.addActionListener(lis_Cancel);
		p3.add(jb);
		JButton jb2 = new JButton("Ok");
		jb2.addActionListener(lis_Ok);
		p3.add(jb2);
		SpringUtilities.makeCompactGrid(p3, 1, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		frame.add(p2);
		frame.add(p3);

		frame.setVisible(true);

	}

}
