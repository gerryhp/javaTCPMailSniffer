package gui.mail.sniffer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartSniffer extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Java TCP Mail Sniffer");
		
		BorderPane root = new BorderPane();
		
		VBox menus = new VBox();
		
		menus.getChildren().add(createMenu());
		menus.getChildren().add(createToolbar());
		
		//TODO Start des Programms
		root.setTop(menus);
		
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
	}
	
	public MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		
		menuBar.getMenus().addAll(menuFile);
		
		return menuBar;
	}
	
	public ToolBar createToolbar() {
		ToolBar toolBar = new ToolBar();
		
		//TODO HBox für alle Buttons für event. nachher noch einer Searchleiste
		Button btnStart = new Button("Start");
		
		toolBar.getItems().add(btnStart);
		
		return toolBar;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
