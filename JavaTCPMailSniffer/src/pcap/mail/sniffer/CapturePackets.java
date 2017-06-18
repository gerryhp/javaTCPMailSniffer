package pcap.mail.sniffer;

import java.io.IOException;
import java.util.ArrayList;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.TextFormatter;

public class CapturePackets {
	
	public void capture() {
		int snaplen = 64*1024; //capture all packets, no trucation
		int flags = Pcap.MODE_PROMISCUOUS; //capture all packets;
		int timeout = 10 * 1000;
		
		ArrayList<PcapIf> alldevs = new NetworkInterfaces().getDevices();
		StringBuilder errbuf = new StringBuilder();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Pcap pcap = Pcap.openLive(alldevs.get(2).getName(), snaplen, flags, timeout, errbuf);
				
				if (pcap == null) {
					System.err.println("Error");
				}
								

				PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
					
					@Override
					public void nextPacket(PcapPacket packet, String user) {
//						System.out.println(packet.size());	
//						System.out.println(packet.getCaptureHeader().getStructName());
						TextFormatter text = new TextFormatter(System.out);
					
						try {
							text.format(packet);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
									
					}
					
				};
							
				pcap.loop(-1, jpacketHandler, "jNetPcap");					
				pcap.close();
			}
		}).start();
	}

}
