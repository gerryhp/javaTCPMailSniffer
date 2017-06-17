package sockets.mail.sniffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.jnetpcap.ByteBufferHandler;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.TextFormatter;

public class NetworkInterfaces {
	public void getDevices() {
		ArrayList<PcapIf> alldevs = new ArrayList<>();
		StringBuilder errbuf = new StringBuilder();
		
		Pcap.findAllDevs(alldevs, errbuf);
		
		for (PcapIf device: alldevs) {
			System.out.println(device.getName());
			System.out.println(device.getDescription());
		}
		
		int snaplen = 64*1024; //capture all packets, no trucation
		int flags = Pcap.MODE_PROMISCUOUS; //capture all packets;
		int timeout = 10 * 1000;
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Pcap pcap = Pcap.openLive(alldevs.get(3).getName(), snaplen, flags, timeout, errbuf);
				
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
