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
	
	public static MessageList getMessageList() { return mlist; } 

}
