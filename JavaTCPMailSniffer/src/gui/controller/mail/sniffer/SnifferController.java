package gui.controller.mail.sniffer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import gui.view.mail.sniffer.SettingsView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pcap.mail.sniffer.CapturePackets;
import pcap.mail.sniffer.PacketCaptured;
import preferences.mail.sniffer.SnifferPreferences;

public class SnifferController implements Initializable {
	
	private SnifferPreferences pref;
	private CapturePackets capturePackets = new CapturePackets();	
	private SettingsView settingsView;
	private ObservableList<PacketCaptured> pcapList = FXCollections.observableArrayList();		
	@FXML
	private Button button;
	@FXML
	private MenuItem menuItemClose;
	@FXML
	private MenuItem menuItemPrefs;
	@FXML
	private Label lblStatus;
	@FXML
	private TableView<PacketCaptured> tblPackets;
	@FXML
	private TableColumn<PacketCaptured, String> tcProtocol;
	@FXML
	private TableColumn<PacketCaptured, Integer> tcFrom;
	@FXML
	private TableColumn<PacketCaptured, Integer> tcTo;
	
	/**
	 * start a new server socket
	 */
	public void start() {	
		System.out.println("Starting...");
		capturePackets.capture(pref.getCurrentIf(), this);
		Platform.runLater(() -> button.setText("Stop"));
	}
	
	/**
	 * stops the server socket
	 * @throws IOException
	 */
	public void stop() throws IOException {
		capturePackets.stopCapture();
		Platform.runLater(() -> button.setText("Start"));
	}
	
	/**
	 * starts or stops the proxy socket
	 * @throws IOException 
	 */
	public void startOrStop() throws IOException {
		System.out.println("isAlive: "+capturePackets.isCapturing());
		if (capturePackets.isCapturing()) {
			stop();
		} else {
			start();
		}
	}
	
	/**
	 * Setting Status to view
	 * @param running
	 */
	public void setStatus(boolean running) {
		if (running) {
			Platform.runLater(() -> lblStatus.setText("Running on Interface "+pref.getCurrentIf().getDescription()));
		} else {
			Platform.runLater(() -> lblStatus.setText("Stopped"));
		}
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
	 * inserts pcap packet into table
	 * @param packet
	 */
	public void insertIntoTable(PacketCaptured packetCaptured) {
		pcapList.add(packetCaptured);		
	}
	
	/**
	 * Init the view
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		button.setText("Start");
		
		//init table
		tblPackets.setEditable(false);		
		
		tcProtocol.setCellValueFactory(new PropertyValueFactory<PacketCaptured, String>("protocol"));
		tcFrom.setCellValueFactory(new PropertyValueFactory<PacketCaptured, Integer>("from"));
		tcTo.setCellValueFactory(new PropertyValueFactory<PacketCaptured, Integer>("to"));
		
		tblPackets.setItems(pcapList);
	}

	/**
	 * quits the program
	 */
	public void quit() {	
		Platform.exit();
	}
}
