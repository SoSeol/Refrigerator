package Problem_Domain;

import java.util.Iterator;
import java.util.Vector;

public class UserList extends GenericList
{
	private Vector<User> list;
	public UserList() { list = new Vector<User>(); }
	
	public String showList()
	{
		StringBuffer buf = new StringBuffer();
		Iterator<User> iter = list.iterator();
		while(iter.hasNext())
			buf.append(iter.next());
		
		return buf.toString();
	}
	
	public void add(User newUser, String operatorName)
	{
		list.add(newUser);
		createUpdateMessage(UpdateMessageType.Addition, newUser.getName(), operatorName);
	}
	
	public void replace(User modifiedUser, String operatorName)
	{
		
	}
		
	protected UpdateMessage createUpdateMessage(UpdateMessageType t, String newItemName, String operatorName)
	{
		return new UpdateMessage("New user " + newItemName + "added by " + operatorName);
	}
}
