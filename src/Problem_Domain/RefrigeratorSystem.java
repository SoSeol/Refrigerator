package Problem_Domain;

public class RefrigeratorSystem
{
	private UserList ulist;
	private FoodList flist;
	private static MessageList mlist;
	
	public RefrigeratorSystem()
	{
		ulist = new UserList();
		flist = new FoodList();
		mlist = new MessageList();
	}
	
	public static void addMessage(Message m) { mlist.add(m); }

}
