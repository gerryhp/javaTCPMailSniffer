package gui.view.mail.sniffer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SettingsView extends Stage {
	
	public SettingsView() throws IOException {
		
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("settingsfxml.fxml"));
		
		this.setScene(new Scene(root));		
		this.setTitle("Settings");
	}
}
