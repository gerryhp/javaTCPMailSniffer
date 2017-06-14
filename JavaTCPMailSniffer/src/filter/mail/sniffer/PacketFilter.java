package filter.mail.sniffer;

import java.util.ArrayList;
import javax.mail.Session;

public class PacketFilter {
	
	private int protcolSelected;
	private String searchExpr;
	private ArrayList<Session> matches;
	
	/**
	 * Three valid Email Protocol Ports to sniff, other protocols are not supported
	 */
	public final int POP3 = 110;
	public final int IMAP = 143;
	public final int SMTP = 25;
	
	public PacketFilter(int protcolSelected) {
		super();
		this.protcolSelected = protcolSelected;
		this.searchExpr = "";
		this.matches = new ArrayList<>();
	}

	public int getProtcolSelected() {
		return protcolSelected;
	}

	public void setProtcolSelected(int protcolSelected) {
		this.protcolSelected = protcolSelected;
		resetMatches();
	}

	public String getSearchExpr() {
		return searchExpr;
	}

	public void setSearchExpr(String searchExpr) {
		this.searchExpr = searchExpr;
		resetMatches();
	}
	
	/**
	 * Removes all old matches, which is necessary for a new searchExpr  
	 */
	private void resetMatches() {
		matches.clear();
	}
	/**
	 * This is the filter function for any of the three protocols.
	 * Session is the most important Class for Email in Java
	 * Once the Socket extracts some Mail Traffic, it needs
	 * to create a new Session out of the collected informations.
	 * This method checks, if any session contains the @param searchExpr. 
	 */
	public ArrayList<Session> filter(/*needs to be discussed*/) {
		if (searchExpr.equals("")) {
			return null;
		}
		// to do ...
		return matches;
	}	
}
