package gui.controller.mail.sniffer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import gui.model.mail.sniffer.SnifferModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import preferences.mail.sniffer.SnifferPreferences;

public class SnifferController implements Initializable {
	
	private SnifferPreferences pref;
	private SnifferModel model = new SnifferModel();	
	@FXML
	private Button button;
	@FXML
	private MenuItem menuItemClose;
	@FXML
	private MenuItem menuItemPrefs;
	
	/**
	 * start to sniff
	 */
	public void start() {
		//TODO if running - stop button - otherwise start
		button.setText(model.getStopButton());
		System.out.println("Start! Listening on port "+pref.getPort());
		
		//TODO neuen Thread starten für das Capturen
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
