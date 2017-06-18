package pcap.mail.sniffer;

public class PacketCaptured {
	private String protocol;
	private String from;
	private String to;
	
	public PacketCaptured(String protocol, String from, String to) {
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}	
	
}
