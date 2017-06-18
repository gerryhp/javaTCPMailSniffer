package pcap.mail.sniffer;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

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
		Ip4 ip = new Ip4();
		byte[] dIP = new byte[4];
		byte[] sIP = new byte[4];
		
		if (packet.hasHeader(ip)) {
			dIP = packet.getHeader(ip).destination();
			sIP = packet.getHeader(ip).source();
			
			String s = FormatUtils.ip(dIP);
			String s1 = FormatUtils.ip(sIP);
			
			if (packet.hasHeader(new Tcp())) {
				StringBuilder str = new StringBuilder();
				packet.getUTF8String(0, str, packet.getTotalSize());
				String payload = str.toString();
				
				if (controller.getFilterText().isEmpty()) {
					controller.insertIntoTable(new PacketCaptured("TCP", s1, s, payload));					
				} else if (payload.contains(controller.getFilterText())) {
					controller.insertIntoTable(new PacketCaptured("TCP", s1, s, payload));
				}
				
			}
		}
		
		
	}
}
