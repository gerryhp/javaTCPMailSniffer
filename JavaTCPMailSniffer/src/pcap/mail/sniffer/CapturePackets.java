package pcap.mail.sniffer;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

public class CapturePackets {
	
	public static final int SNAPLEN = 64*1024; //capture all packets, no trucation
	public static final int FLAGS = Pcap.MODE_PROMISCUOUS; //capture all packets
	public static final int TIMEOUT = 10*1000; //timeout in milli
	
	private CaptureThread thread;
	
	public void capture(PcapIf pcapIf) {					
		thread = new CaptureThread(pcapIf);
		thread.start();
	}
	
	public void stopCapture() {		
		if (thread != null) {
			if (!thread.isInterrupted()) {
				thread.interrupt();
				thread.stopLoop();
			}
		}
	}

}
