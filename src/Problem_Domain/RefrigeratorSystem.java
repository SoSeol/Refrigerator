package Problem_Domain;

import java.util.Vector;

public class RefrigeratorSystem
{
	private Message tempMessage = null;
	private static Vector<String> prohibitedList;
	private UserList ulist;
	private FoodList flist;
	
	public void setTempMessage(Message msg) { tempMessage = msg; ulist = new UserList(); flist = new FoodList();}

	public static Vector<String> getProhibitedList()
	{
		return prohibitedList;
	}
}
