package preferences.mail.sniffer;

import org.jnetpcap.PcapIf;

import pcap.mail.sniffer.NetworkInterfaces;

/**
 * Default Preferences for the sniffer
 * 
 * @author Gerry
 *
 */
public class SnifferPreferences {

	private NetworkInterfaces networkIf;
	private PcapIf currentIf;
	
	public SnifferPreferences() {
		initDevices();
	}
	
	private void initDevices() {
		networkIf = new NetworkInterfaces();
		setCurrentIf(networkIf.getDevices().get(0));
	}
				
	public NetworkInterfaces getNetworkInterfaces() {
		return this.networkIf;
	}
	
	public void setCurrentIf(PcapIf currentIf) {
		this.currentIf = currentIf;
	}
	
	public PcapIf getCurrentIf() {
		return this.currentIf;
	}
}
