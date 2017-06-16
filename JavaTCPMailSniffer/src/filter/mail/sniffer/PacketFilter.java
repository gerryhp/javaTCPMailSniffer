package filter.mail.sniffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.mail.Message;
import javax.mail.MessagingException;

public class PacketFilter {

	private int protcolSelected;
	private String searchExpr;
	private ArrayList<Message> matches;

	/**
	 * Three valid Email Protocol Ports to sniff, other protocols are not
	 * supported. This needs to be pushed to the Socket class!
	 */
//	public final int POP3 = 110;
//	public final int IMAP = 143;
//	public final int SMTP = 25;

	public PacketFilter(int protcolSelected) {
		super();
		this.protcolSelected = protcolSelected;
		this.searchExpr = "";
		this.matches = new ArrayList<>();
	}

	public PacketFilter(int protcolSelected, String searchExpr) {
		super();
		this.protcolSelected = protcolSelected;
		this.searchExpr = searchExpr;
		this.matches = new ArrayList<>();
	}

	public int getProtcolSelected() {
		return protcolSelected;
	}

	public void setProtcolSelected(int protcolSelected) {
		this.protcolSelected = protcolSelected;
		matches.clear();
	}

	public String getSearchExpr() {
		return searchExpr;
	}

	public void setSearchExpr(String searchExpr) {
		this.searchExpr = searchExpr;
		matches.clear();
	}

	public ArrayList<Message> getMatches() {
		return matches;
	}

	/**
	 * This is the filter function for any of the three protocols. Once the
	 * Socket extracts some Mail Traffic, it needs to create a new Session and a
	 * Message object out of the collected informations. This method checks, if
	 * the given message contains the @param searchExpr in the selected area (i.e. Email-Body).
	 * 
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void filter(Message message, SearchArea search) throws IOException, MessagingException {
		if (searchExpr.equals("")) {
			return;
		}
		switch (search) {
		case CONTENT:
			if (message.getContent().toString().contains(searchExpr)) {
				matches.add(message);
			}

			break;

		case FILE_NAME:
			if (message.getFileName().contains(searchExpr)) {
				matches.add(message);
			}
			break;

		case FROM:
			String from = Arrays.toString(message.getFrom());
			if (from.contains(searchExpr)) {
				matches.add(message);
			}
			break;

		case SENT_DATE:
			if (message.getSentDate().toString().contains(searchExpr)) {
				matches.add(message);
			}
			break;

		case SUBJECT:
			if (message.getSubject().toString().contains(searchExpr)) {
				matches.add(message);
			}
			break;

		case TO:
			String to = Arrays.toString(message.getReplyTo());
			if (to.contains(searchExpr)) {
				matches.add(message);
			}
			break;

		default:
			break;
		}
	}
}
