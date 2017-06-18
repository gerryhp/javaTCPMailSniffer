package gui.view.mail.sniffer;

import java.io.IOException;

import gui.controller.mail.sniffer.SettingsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import preferences.mail.sniffer.SnifferPreferences;

public class SettingsView extends Stage {

	public SettingsView(SnifferPreferences prefs) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("settingsfxml.fxml"));
		Parent root = loader.load();

		//Controller Settings
		SettingsController settingsController = (SettingsController) loader.getController();
		//add Settings to view
		settingsController.setSettings(prefs);

		//test
		this.setScene(new Scene(root));
		this.setTitle("Settings");
	}
}
