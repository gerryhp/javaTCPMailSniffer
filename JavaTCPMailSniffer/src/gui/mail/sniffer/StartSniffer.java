package gui.mail.sniffer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartSniffer extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Java TCP Mail Sniffer");
		
		BorderPane root = new BorderPane();
		
		//TODO Start des Programms
		root.setTop(createMenu());
	
		
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
	}
	
	public MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		
		menuBar.getMenus().addAll(menuFile);
		
		return menuBar;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
