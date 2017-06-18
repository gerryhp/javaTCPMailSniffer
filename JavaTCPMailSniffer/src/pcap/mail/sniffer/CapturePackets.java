package pcap.mail.sniffer;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;

import gui.controller.mail.sniffer.SnifferController;

public class CapturePackets {
	
	public static final int SNAPLEN = 64*1024; //capture all packets, no trucation
	public static final int FLAGS = Pcap.MODE_PROMISCUOUS; //capture all packets
	public static final int TIMEOUT = 10*1000; //timeout in milli
	
	private CaptureThread thread;
	private SnifferController controller;
	
	public void capture(PcapIf pcapIf, SnifferController controller) {			
		this.controller = controller;
		
		thread = new CaptureThread(pcapIf, this);
		thread.start();
	}
	
	public void stopCapture() {		
		if (thread != null) {
			if (thread.isCapturing()) {
				thread.stopLoop();
			}
		}
	}
	
	public void currentStatus(boolean status) {
		controller.setStatus(status);
	}
	
	public boolean isCapturing() {
		if (thread == null) {
			return false;
		}
		return thread.isCapturing();
	}
	
	public void packetRec(PcapPacket packet) {
		//TODO einzelne packet abfangen stream einbeziehn und ab dafür
		
		controller.insertIntoTable(new PacketCaptured("test", 1, 2));
	}
}
