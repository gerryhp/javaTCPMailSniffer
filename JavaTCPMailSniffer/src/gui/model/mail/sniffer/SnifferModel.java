package gui.model.mail.sniffer;

public class SnifferModel {

	private String start;
	private String stop;
	
	public SnifferModel() {
		this.start = "Start";
		this.stop = "Stop";
	}
	
	public String getStartButton() {
		return this.start;
	}
	
	public String getStopButton() {
		return this.stop;
	}
	
	
}
