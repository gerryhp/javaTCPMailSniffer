package pcap.mail.sniffer;

public class PacketCaptured {
	private String protocol;
	private int from;
	private int to;
	
	public PacketCaptured(String protocol, int from, int to) {
		this.protocol = protocol;
		this.from = from;
		this.to = to;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}	
	
}
