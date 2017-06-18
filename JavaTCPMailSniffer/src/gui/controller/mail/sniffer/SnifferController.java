package gui.controller.mail.sniffer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.view.mail.sniffer.SettingsView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import pcap.mail.sniffer.CapturePackets;
import preferences.mail.sniffer.SnifferPreferences;

public class SnifferController implements Initializable {
	
	private SnifferPreferences pref;
	private SettingsView settingsView;
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
		Platform.runLater(() -> button.setText("Stop"));
	}
	
	/**
	 * stops the server socket
	 * @throws IOException
	 */
	public void stop() throws IOException {
		Platform.runLater(() -> button.setText("Start"));
	}
	
	/**
	 * starts or stops the proxy socket
	 * @throws IOException 
	 */
	public void startOrStop() throws IOException {
		//TODO Thread am laufen dann stoppen und andersrum
		new CapturePackets().capture(pref.getCurrentIf());
	}
	
	/**
	 * Setting Status to view
	 * @param running
	 */
	public void setStatus(boolean running) {
		
	}
	
			
	/**
	 * shows the view of settings
	 * @throws IOException
	 */
	public void showSettings() throws IOException {	
		if (!settingsView.isShowing()) {
			settingsView.show();
		}
	}

	/**
	 * Set Settings to load and start the sniffer
	 * @param SnifferPreferences
	 */
	public void setSettings(SnifferPreferences pref) {
		this.pref = pref;
		
		//For the Settings view
		try {
			this.settingsView = new SettingsView(this.pref);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Platform.exit();
	}
}
