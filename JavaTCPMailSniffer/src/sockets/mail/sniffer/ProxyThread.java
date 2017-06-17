package sockets.mail.sniffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ProxyThread extends Thread {

	private Socket socket;
	private BufferedReader reader;
	private ProxySocket proxySocket;

	/**
	 * new Thread for a new socket connection
	 * 
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
			System.out.println("IP Address: " + socket.getInetAddress());

			String request = reader.readLine();
			System.out.println(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a Session, which stores the POP3&SMTP or the IMAP&SMTP Server
	 * including the user name and password
	 * @param sniffedRequest
	 * @return
	 */
	private Session createSession(String sniffedRequest) {
		final Properties prop = new Properties();
		// pop3
		// imap
		// smtp
		
		// TODO
		return null;
	}

	/**
	 * Creates a Message object out of the given parameters
	 * 
	 * @param session
	 * @param content
	 *            The actual message of the Email
	 * @param subject
	 *            The subject of the Email
	 * @param destination
	 *            The recipient of the Email
	 * 
	 * @throws MessagingException
	 */
	private Message createMessage(Session session, String content, String subject, String destination)
			throws MessagingException {
		Message message = new MimeMessage(session);
		InternetAddress address = new InternetAddress(destination);

		message.setRecipient(Message.RecipientType.TO, address);
		message.setSubject(subject);
		message.setContent(content, "text/plain");

		return message;
	}

}
