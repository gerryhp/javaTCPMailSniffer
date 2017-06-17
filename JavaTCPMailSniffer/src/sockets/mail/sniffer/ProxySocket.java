package sockets.mail.sniffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import gui.controller.mail.sniffer.SnifferController;

public class ProxySocket extends Thread {
		
	private int port;
	private SnifferController controller;
	private ServerSocket serverSocket;
	private boolean running;
	private ArrayList<Socket> clients;
	
	/**
	 * start a new proxy socket 
	 * @param port port address of proxy socket
	 */
	public ProxySocket(int port, SnifferController controller) {
		this.setDaemon(true);
		this.port = port;
		this.controller = controller;
		this.running = false;
		this.clients = new ArrayList<>(); 
	}
	
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			
			System.out.println("Waiting for client connections");
			controller.setStatus(true);
			setRunning(true);
			
			while (true) {
//				BufferedReader re = new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));
//				System.out.println(re.read());
				
				new ProxyThread(serverSocket.accept(), this).start();
			}
	
		} catch (SocketException ex) {
			//ServerSocket stops connection
			System.out.println(ex);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			setRunning(false);
		}
	}	
	
	public boolean isRunning() {
		return running;
	}
	
	private void setRunning(boolean running) {
		controller.setStatus(running);
		this.running = running;
	}
	
	public void stopProxy() throws IOException {
		if (serverSocket != null) {
			serverSocket.close();
		}
	}
	
	public synchronized void setClient(Socket client) {
		controller.newClient(client);
		clients.add(client);		
	}
	
	public synchronized void connectionClosed(Socket client) {
		clients.remove(client);
	}

}
