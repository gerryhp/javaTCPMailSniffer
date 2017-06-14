package sockets.mail.sniffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ProxyThread extends Thread {
	
	private Socket socket;
	private BufferedReader reader; 
	
	/**
	 * new Thread for a new socket connection
	 * @param socket
	 * @throws IOException
	 */
	public ProxyThread(Socket socket) throws IOException {
		this.socket = socket;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	@Override
	public void run() {
		try {		
			System.out.println("IP Address: "+socket.getInetAddress());
			
			String request = reader.readLine();
			System.out.println(request);			
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}
}
