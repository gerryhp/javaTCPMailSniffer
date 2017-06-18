package gui.controller.mail.sniffer;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import preferences.mail.sniffer.SnifferPreferences;

public class SettingsController {
		
	private SnifferPreferences prefs;
	@FXML
	public ChoiceBox<String> ccbNetworkIf;
	@FXML
	public CheckBox cbPop;
	@FXML
	public CheckBox cbImap;
	@FXML
	public Button btnSave;
	
	/**
	 * getting the settings
	 * @param prefs
	 */
	public void setSettings(SnifferPreferences prefs) {
		this.prefs = prefs;
		initSettings();
	}
	
	/**
	 * Initializing the settings to the view
	 */
	public void initSettings() {		
		ccbNetworkIf.setItems(FXCollections.observableArrayList(prefs.getNetworkInterfaces().getDeviceNames()));
		ccbNetworkIf.setValue(prefs.getNetworkInterfaces().getDeviceNames().get(0));
		cbPop.setSelected(true);
	}
	
	/**
	 * on click save button - saving current settings
	 */
	public void saveSettings() {
		prefs.setCurrentIf(prefs.getNetworkInterfaces().getDevices().get(ccbNetworkIf.getSelectionModel().getSelectedIndex()));
		System.out.println("Network Interface choosen: "+prefs.getCurrentIf().getDescription());
		
		closeSettingsWindow();		
	}
	
	/**
	 * closing setting window
	 */
	private void closeSettingsWindow() {
		Stage stage = (Stage) btnSave.getScene().getWindow();	
		stage.close();		
	}
}
