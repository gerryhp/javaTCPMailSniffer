package pcap.mail.sniffer;

import java.io.IOException;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.TextFormatter;

public class Packets implements PcapPacketHandler<String> {
	
	private TextFormatter textformatter = new TextFormatter(System.out);
	private boolean showConsole;
	private CapturePackets capturePackets;
	
	public Packets(boolean showConsole, CapturePackets capturePackets) {
		this.showConsole = showConsole;
		this.capturePackets = capturePackets;
	}

	/**
	 * getting sniffed packet
	 */
	@Override
	public void nextPacket(PcapPacket packet, String user) {
		if (showConsole) {
			showInConsole(packet);			
		}
		
		capturePackets.packetRec(packet);
	}
	
	/**
	 * shows every sniffed packet in System.out
	 * @param packet
	 */
	private void showInConsole(PcapPacket packet) {
		try {
			textformatter.format(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
