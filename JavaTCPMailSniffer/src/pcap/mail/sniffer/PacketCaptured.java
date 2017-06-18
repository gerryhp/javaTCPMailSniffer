package pcap.mail.sniffer;

public class PacketCaptured {
	private String protocol;
	private String from;
	private String to;
	private String payload;
		
	public PacketCaptured(String protocol, String from, String to, String payload) {
		this.protocol = protocol;
		this.from = from;
		this.to = to;
		this.payload = payload;
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

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}	
	
}
