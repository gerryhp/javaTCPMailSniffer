package gui.controller.mail.sniffer;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import preferences.mail.sniffer.SnifferPreferences;
import sockets.mail.sniffer.ProxySocket;

public class SnifferController implements Initializable {
	
	private ProxySocket socket;
	private SnifferPreferences pref;
	@FXML
	private Button button;
	@FXML
	private MenuItem menuItemClose;
	@FXML
	private MenuItem menuItemPrefs;
	@FXML
	private Label lblStatus;
	
	/**
	 * start a new server socket
	 */
	public void start() {	
		System.out.println("Starting...");
		//Starts a new Thread for the proxy socket
		socket = new ProxySocket(pref.getPort(), this);		
		socket.start();
		Platform.runLater(() -> button.setText("Stop"));
	}
	
	/**
	 * stops the server socket
	 * @throws IOException
	 */
	public void stop() throws IOException {
		socket.stopProxy();
		Platform.runLater(() -> button.setText("Start"));
	}
	
	/**
	 * starts or stops the proxy socket
	 * @throws IOException 
	 */
	public void startOrStop() throws IOException {
		if (socket == null || !socket.isRunning()) {
			start();
		} else if (socket.isRunning()){
			stop();
		}
	}
	
	/**
	 * Setting Status to view
	 * @param running
	 */
	public void setStatus(boolean running) {
		if (running) {
			Platform.runLater(() -> this.lblStatus.setText("Running on Port: "+pref.getPort()));			
		} else {
			Platform.runLater(() -> this.lblStatus.setText("Stopped")); 
		}
	}
	
	/**
	 * if new client is connected, print on view
	 * @param socket
	 */
	public void newClient(Socket socket) {		
		Platform.runLater(() -> this.lblStatus.setText(lblStatus.getText()+" Client: "+socket.getLocalAddress().toString()));
	}
			
	/**
	 * shows the view of settings
	 * @throws IOException
	 */
	public void showSettings() throws IOException {
		if (!pref.isSettingsOpen())
			this.pref.showSettings();
	}

	/**
	 * Set Settings to load and start the sniffer
	 * @param SnifferPreferences
	 */
	public void setSettings(SnifferPreferences pref) {
		this.pref = pref;
	}
	
	/**
	 * Init the view
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		button.setText("Start");	
	}

	/**
	 * quits the program
	 */
	public void quit() {
		if (socket != null)
			socket.interrupt();
		
		Platform.exit();
	}
}
