package tcp.mail.sniffer;

import java.io.IOException;
import java.net.ServerSocket;

public class ProxySocket {
	
	private static final int PORT = 1234;
	
	private int port;
		
	/**
	 * starts a new proxy socket at port 1234
	 */
	public ProxySocket() {
		this.port = PORT;		
		startProxy();
	}
	
	/**
	 * start a new proxy socket 
	 * @param port port address of proxy socket
	 */
	public ProxySocket(int port) {
		this.port = port;
		startProxy();
	}
	
	/**
	 * creates new Proxy Socket / Server Socket
	 * and waiting for connections
	 */
	public void startProxy() {
		try(ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Waiting for client connections");
			
			while (true) {		
				new ProxyThread(serverSocket.accept()).start();
			}
			
		} catch (IOException io) {
			System.out.println(io);
		}
	}

}
