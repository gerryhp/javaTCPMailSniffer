package sockets.mail.sniffer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

import gui.controller.mail.sniffer.SnifferController;

public class ProxySocket extends Thread {
		
	private int port;
	private SnifferController controller;
	private ServerSocket serverSocket;
	private boolean running;

	/**
	 * start a new proxy socket 
	 * @param port port address of proxy socket
	 */
	public ProxySocket(int port, SnifferController controller) {
		this.setDaemon(true);
		this.port = port;
		this.controller = controller;
		this.running = false;
	}
	
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			
			System.out.println("Waiting for client connections");
			controller.setStatus(true);
			setRunning(true);
			
			while (true) {
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

}
