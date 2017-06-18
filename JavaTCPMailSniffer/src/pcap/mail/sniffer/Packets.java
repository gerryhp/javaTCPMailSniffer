package pcap.mail.sniffer;

import java.io.IOException;
import java.util.ArrayList;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.TextFormatter;

public class Packets implements PcapPacketHandler<String> {
	
	private ArrayList<PcapPacket> list = new ArrayList<>();
	private TextFormatter textformatter = new TextFormatter(System.out);
	private boolean showConsole;
	private CapturePackets capturePackets;
	
	public Packets(boolean showConsole, CapturePackets capturePackets) {
		this.showConsole = showConsole;
		this.capturePackets = capturePackets;
	}

	@Override
	public void nextPacket(PcapPacket packet, String user) {
		this.appendPacket(packet);
		capturePackets.packetRec(packet);
	}
	
	private void showInConsole(PcapPacket packet) {
		try {
			textformatter.format(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void appendPacket(PcapPacket packet) {
		this.list.add(packet);
		
		if (showConsole) {
			showInConsole(packet);
		}
	}
	
	public ArrayList<PcapPacket> getPackets() {
		return new ArrayList<>(list);
	}
	
}
