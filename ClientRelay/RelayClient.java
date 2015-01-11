package ClientRelay;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class RelayClient {

	public static void Start(String name,String address,JTextArea jt) {
		//connect to relay server
		//set call back
		
	
		try {
			Socket s = new Socket(address,1338);
			PrintWriter out = new PrintWriter(s.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out.println(name);
			out.flush();
			String reply;
			reply = in.readLine();
			if(reply.compareTo("-1")==0){
				JOptionPane.showMessageDialog(null, "No Messages for you");	
				}
			else{
				jt.setText(jt.getText()+"\n" + reply);
			}
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
