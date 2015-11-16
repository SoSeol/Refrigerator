package Problem_Domain;

import java.util.Calendar;

public class Message
{
	private MessageType messageType;
	private String messageDetail;
	private Calendar createdDate;
	private Calendar messageUntil;
	
	protected Message(MessageType type, String detail, Calendar until)
	{
		messageType = type;
		messageDetail = detail;
		createdDate = Calendar.getInstance();
		messageUntil = until;
	}
	
	public boolean isExpired() { return messageUntil.before(Calendar.getInstance()); }
	
	public String toString()
	{
		return "[" + createdDate + "] " + messageType + " : " + messageDetail;
	}
}
