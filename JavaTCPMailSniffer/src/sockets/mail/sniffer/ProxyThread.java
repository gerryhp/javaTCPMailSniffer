package sockets.mail.sniffer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
		System.out.println(socket);
	}
	
	@Override
	public void run() {
		try {		
				
			proxySocket.setClient(socket);
			int req;
			
			StringBuilder sb = new StringBuilder();
			
			while ((req = reader.read()) != -1) {
				System.out.println(req);
				sb.append((char) req);					
				String result = sb.toString();
				System.out.println(result);				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			proxySocket.connectionClosed(socket);
		}
	}
}
