package Problem_Domain;

public class RefrigeratorSystem
{
	private static UserList ulist;
	private static FoodList flist;
	private static MessageList mlist;
	
	public RefrigeratorSystem()
	{
		ulist = new UserList();
		flist = new FoodList();
		mlist = new MessageList();
	}
	
	public static MessageList getMessageList() { return mlist; } 
	public static UserList getUserList() { return ulist; }
	public static FoodList getFoodList() { return flist; }
}
