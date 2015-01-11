package ServerSide;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

import javax.swing.JTextArea;

public class RelayServer{

	 public static void start(JTextArea jt,int portListen){
		CopyOnWriteArrayList<Mapping> names = new CopyOnWriteArrayList<Mapping>();
		ReplyToClient rc = new ReplyToClient(names,jt,portListen);
		Thread rc_t = new Thread(rc);
		rc_t.start();
		
		ReplyToRelay rr = new ReplyToRelay(names);
		Thread rr_t = new Thread(rr);
		rr_t.start();
	}

}
class Mapping{
	public String Name;
	public String Message;
}
class ReplyToRelay implements Runnable{
	CopyOnWriteArrayList<Mapping> names;
	public ReplyToRelay(
			CopyOnWriteArrayList<Mapping> names) {
		this.names = names;
	}
	@Override
	public void run() {
		ServerSocket server=null;
		try {
			server = new ServerSocket(1338);
			while(true){
				
				RelayHandler rh = new RelayHandler(server.accept(),names);
				Thread theThread = new Thread(rh);
				theThread.start();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}finally{
			try {	
				if(server!=null)//Without try catch it does not compile
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}




class RelayHandler implements Runnable{
	Socket _socket;
	CopyOnWriteArrayList<Mapping> _names;
	public RelayHandler(Socket s,CopyOnWriteArrayList<Mapping> names) {
		this._socket = s;
		_names = names;
	}
	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(_socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			String name = in.readLine();
			check(out, name);					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void check(PrintWriter out, String name) {
		boolean isMessage=false;
		for (Mapping mapping : _names) {
			if(mapping.Name.compareTo(name)==0){
				out.println(mapping.Message);
				out.flush();
				_names.remove(mapping);
				isMessage = true;
				break;
			}
		}
		if(!isMessage){
			out.println("-1");
			out.flush();
			
		}
	}


}





class ReplyToClient implements Runnable{
	CopyOnWriteArrayList<Mapping> names;
	JTextArea jt;
	int port;
	public ReplyToClient(CopyOnWriteArrayList<Mapping> names,JTextArea jt,int port) {
		this.names = names;
		this.jt = jt;
		this.port = port;
	}
	@Override
	public void run() {
		ServerSocket server=null;
		try {
			 server = new ServerSocket(port);
			while(true){
				
				ClientHandler ch = new ClientHandler(server.accept(),names,jt);
				Thread theThread = new Thread(ch);
				theThread.start();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(server!=null)
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
	}
	
}




class ClientHandler implements Runnable{
	private Socket _socket;
	CopyOnWriteArrayList<Mapping> names;
	JTextArea jt;
	public ClientHandler(Socket s,CopyOnWriteArrayList<Mapping> names,JTextArea jt) {
		if(s==null) return;
		this._socket = s;
		this.names = names;
		this.jt = jt;
	}
	@Override
	public void run() {
		
		
		try {
			PrintWriter out = new PrintWriter(_socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			String message = in.readLine();
			String destination = in.readLine();
			Mapping m = new Mapping();
			m.Message = message;
			m.Name = destination;
			//System.out.println("Message is " + message);
			//System.out.println("Destination is " + destination);
			String info = String.format("Message: %s\nDestination:%s\n",message,destination );
			synchronized (this) {
				jt.setText(jt.getText() + info);	
			}
			
			names.add(m);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
}