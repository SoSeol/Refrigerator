package Problem_Domain;

public class Administrator extends User {
	
	public Administrator(String newName, String newID, String newPW)
	{
		super(newName, newID, newPW);
		
	}
		
	public String searchUser()
	{		
		return RefrigeratorSystem.getUserList().showList();
	}
	
	private void editUser()
	{
	     
	}
		
	private void registerUser(UserPrevilege prev, String name, String id, String pw)
	{
		User newUser = null;
		switch(prev)
		{
		case Administrator:
			newUser = new Administrator(name, id, pw);
			break;
		case Normal:
			newUser = new NormalUser(name, id, pw);
			break;
		}
		
		RefrigeratorSystem.getUserList().add(newUser, this.getName());
	}
	
	public void updateUser(UserPrevilege prev, String name, String id, String pw)
	{
		registerUser(prev, name, id, pw);
	}
	
	public void deleteOldMessages()
	{
		
	}
	
	public String toString()
	{
		return "Administrator, ID : " + getID() + ", Name : " + getName();
	}
	
	
}
