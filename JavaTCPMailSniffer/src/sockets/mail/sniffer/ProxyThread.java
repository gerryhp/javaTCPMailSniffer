package sockets.mail.sniffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ProxyThread extends Thread {
	
	private Socket socket;
	private BufferedReader reader; 
	private ProxySocket proxySocket;
	
	/**
	 * new Thread for a new socket connection
	 * @param socket
	 * @throws IOException
	 */
	public ProxyThread(Socket socket, ProxySocket proxySocket) throws IOException {
		this.socket = socket;
		this.proxySocket = proxySocket;		
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
