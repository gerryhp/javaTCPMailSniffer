package pcap.mail.sniffer;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapBpfProgram;
import org.jnetpcap.PcapIf;

public class CaptureThread extends Thread {
	
	private PcapIf pcapIf;
	private Pcap pcap;
	private StringBuilder errbuf = new StringBuilder();
	private boolean capture;
	private CapturePackets capturePackets;
	
	public CaptureThread(PcapIf pcapIf, CapturePackets capturePackets) {
		this.pcapIf = pcapIf;
		this.capture = false;
		this.capturePackets = capturePackets;
		this.setDaemon(true);
	}
	
	/**
	 * Starts the Thread to sniff
	 */
	public void run() {
		capture = true;
		capturePackets.currentStatus(true);
		pcap = Pcap.openLive(pcapIf.getName(), CapturePackets.SNAPLEN, 
				CapturePackets.FLAGS, CapturePackets.TIMEOUT, errbuf);
		
		if (pcap == null) {
			System.err.println("Error");
			return;
		}
		
		applyFilter();
		
				
		//What to do with sniffed packets - @param true: shows in console
		Packets packets = new Packets(false, capturePackets);
		
		/*
		 * captures every packet in a loop
		 */
		pcap.loop(-1, packets, "jNetPcap");
		System.out.println("Stopped to sniff");
		pcap.close();
		capture = false;
		capturePackets.currentStatus(false);
	}
	
	/**
	 * stops the capture in loop
	 */
	public void stopLoop() {
		pcap.breakloop();
	}
	
	/**
	 * status if the sniffer is capturing packets
	 * @return
	 */
	public synchronized boolean isCapturing() {
		return capture;
	}
	
	/**
	 * apply a filter to the sniffed packets
	 * @return
	 */
	private boolean applyFilter() {
		PcapBpfProgram filter = new PcapBpfProgram();
		
		String expression = "tcp port 995";
		
		int optimize = 0; //0 = false
		int netmask = 0xFFFFFF00; //255.255.255.0
		
		if (pcap.compile(filter, expression, optimize, netmask) != Pcap.OK) {
			return false;
		}
		
		pcap.setFilter(filter);
		
		return true;
	}
	

}
