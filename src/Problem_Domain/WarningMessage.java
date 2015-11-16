package Problem_Domain;

import java.util.Calendar;

public class WarningMessage extends Message
{

	public WarningMessage(String detail, Calendar until)
	{
		super(MessageType.Warning, detail, until);
	}
	public WarningMessage(String detail)
	{
		this(detail, Calendar.getInstance());
	}
	
}
