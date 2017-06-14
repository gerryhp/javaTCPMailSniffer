package gui.view.mail.sniffer;

import java.io.IOException;

import gui.controller.mail.sniffer.SnifferController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import preferences.mail.sniffer.SnifferPreferences;

public class StartSniffer extends Application {

	private SnifferPreferences prefs = new SnifferPreferences();
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Java TCP Mail Sniffer");
		
		//Load Scene
		FXMLLoader loader = new FXMLLoader(getClass().getResource("startfxml.fxml"));
		Parent root = (Parent) loader.load();
		
		//Controller Settings
		SnifferController snifferController = (SnifferController) loader.getController();
		snifferController.setSettings(prefs);
					
		//Show Scene
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
