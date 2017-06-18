package pcap.mail.sniffer;

import java.util.ArrayList;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

public class NetworkInterfaces {
	
	private ArrayList<PcapIf> alldevs = new ArrayList<>();
	private StringBuilder errbuf = new StringBuilder();
	
	public NetworkInterfaces() {
		if (findDevices() != Pcap.OK) {
			System.out.println("Error occured: "+errbuf.toString());
			return;
		}
	}
	
	private int findDevices() {		
		int statusCode = Pcap.findAllDevs(alldevs, errbuf);		
		
		for (PcapIf device: alldevs) {
			System.out.println(device.getName());
			System.out.println(device.getDescription());
		}
		
		return statusCode;
	}
	
	public ArrayList<PcapIf> getDevices() {
		return new ArrayList<>(alldevs);		
	}
	
	public ArrayList<String> getDeviceNames() {
		ArrayList<String> deviceNames = new ArrayList<>();
		
		for (PcapIf device: alldevs) {
			deviceNames.add(device.getDescription());
		}
		
		return deviceNames;
	}
	
}
