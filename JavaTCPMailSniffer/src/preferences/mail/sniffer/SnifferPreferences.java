package preferences.mail.sniffer;

import java.io.IOException;

import gui.view.mail.sniffer.SettingsView;

/**
 * Default Preferences 
 * 
 * @author Gerry
 *
 */
public class SnifferPreferences {
	
	public static final int DEFAULT_PORT = 8081;
	
	private int port;
	private SettingsView view;
	
	public SnifferPreferences() {
		this.port = DEFAULT_PORT;
		try {
			this.view = new SettingsView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void showSettings() {
		this.view.show();
	}
	
	public boolean isSettingsOpen() {
		return this.view.isShowing();
	}
}
