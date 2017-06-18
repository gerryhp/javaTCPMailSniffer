package pcap.mail.sniffer;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

public class CaptureThread extends Thread {
	
	private PcapIf pcapIf;
	private Pcap pcap;
	private StringBuilder errbuf = new StringBuilder();
	
	public CaptureThread(PcapIf pcapIf) {
		this.pcapIf = pcapIf;
		this.setDaemon(true);
	}
	
	public void run() {
		pcap = Pcap.openLive(pcapIf.getName(), CapturePackets.SNAPLEN, 
				CapturePackets.FLAGS, CapturePackets.TIMEOUT, errbuf);
		
		if (pcap == null) {
			System.err.println("Error");
		}
						
		Packets packets = new Packets(true);
		
		/*
		 * captures every packet in a loop
		 */
		pcap.loop(-1, packets, "jNetPcap");
		pcap.close();
	}
	
	/**
	 * stops the capture in loop
	 */
	public void stopLoop() {
		pcap.breakloop();
	}


}
