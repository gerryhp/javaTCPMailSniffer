package pcap.mail.sniffer;

import java.util.ArrayList;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

public class CapturePackets {
	
	private static final int SNAPLEN = 64*1024; //capture all packets, no trucation
	
	public void capture(PcapIf pcapIf) {
		
		int snaplen = 64*1024; //capture all packets, no trucation
		int flags = Pcap.MODE_PROMISCUOUS; //capture all packets;
		int timeout = 10 * 1000; //timeout in milli
		
		StringBuilder errbuf = new StringBuilder();
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Pcap pcap = Pcap.openLive(pcapIf.getName(), snaplen, flags, timeout, errbuf);
				
				if (pcap == null) {
					System.err.println("Error");
				}
								
				Packets packets = new Packets(true);
				
				/*
				 * captures every packet
				 */
				pcap.loop(-1, packets, "jNetPcap");					
				pcap.close();
			}
		});
		t.setDaemon(true);
		t.start();
		
	}

}
