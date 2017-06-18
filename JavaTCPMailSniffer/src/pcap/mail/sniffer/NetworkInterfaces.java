package pcap.mail.sniffer;

import java.util.ArrayList;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

public class NetworkInterfaces {
	
	public ArrayList<PcapIf> getDevices() {
		ArrayList<PcapIf> alldevs = new ArrayList<>();
		StringBuilder errbuf = new StringBuilder();
		
		int statusCode = Pcap.findAllDevs(alldevs, errbuf);
		
		if (statusCode != Pcap.OK) {
			System.out.println("Error occured: "+errbuf.toString());
			return null;
		}
		
		for (PcapIf device: alldevs) {
			System.out.println(device.getName());
			System.out.println(device.getDescription());
		}	
		
		return alldevs;		
	}
}
