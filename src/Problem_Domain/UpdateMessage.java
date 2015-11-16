package Problem_Domain;

import java.util.Calendar;

public class UpdateMessage extends Message
{
	public UpdateMessage(String detail, Calendar messageUntil)
	{
		super(MessageType.Update, detail, messageUntil);
	}
	
	public UpdateMessage(String detail)
	{
		this(detail, getAfterDay(1));
	}
	
	private static Calendar getAfterDay(int cnt)
	{
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, cnt);
		return date;
	}
}
