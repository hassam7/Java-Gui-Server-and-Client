package Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;



public class ClientRelay {
//port = 1337
	public static void start(String host,int port,String destination,String message) {
		System.out.println("Running Echo Client");
		Socket s = null;
		PrintWriter out = null;
		BufferedReader in=null;
		try {
			s = new Socket(host, port);
			out = new PrintWriter(s.getOutputStream());
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out.println(message);
			out.flush();
			out.println(destination);
			out.flush();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
				out.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
