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
	
	public Packets(boolean showConsole) {
		this.showConsole = showConsole;
	}

	@Override
	public void nextPacket(PcapPacket packet, String user) {
		this.appendPacket(packet);
	}
	
	private void showInConsole(PcapPacket packet) {
		try {
			textformatter.format(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private synchronized void appendPacket(PcapPacket packet) {
		this.list.add(packet);
		
		if (showConsole) {
			showInConsole(packet);
		}
	}
	
	public synchronized ArrayList<PcapPacket> getPackets() {
		return new ArrayList<>(list);
	}
	
}
