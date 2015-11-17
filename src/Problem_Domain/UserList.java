package Problem_Domain;

import java.util.Vector;

public class UserList
{
	private Vector<User> list;
	public UserList() { list = new Vector<User>(); }
	
	/**
	 * ����Ʈ�� ���ڿ��� ��ȯ����
	 * @return ���ڿ�ȭ �� ����Ʈ
	 */
	public String showList()
	{
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < list.size(); ++i)
			buf.append((i + 1) + list.elementAt(i).toString() + '\n');
		return buf.toString();
	}
	
	/**
	 * ����Ʈ�� �ش��ϴ� ���̵� ������ User ��ü�� �Ѱ��ְ� ������ null�� �Ѱ���
	 * @param ID ã���� �ϴ� ID
	 * @return ã�� ��� �ش� �����, ã�� ���� ��� null
	 */
	public User checkID(String ID)
	{
		for(int i = 0; i < list.size(); ++i)
		{
			if(list.elementAt(i).getID() == ID)
				return list.elementAt(i);
		}
		return null;
	}
	
	/**
	 * �ߺ� üũ���� ��. �� �� ����ڸ� ����Ʈ�� �߰�.  ���������� �޼��� ����
	 * @param newUser ���� �߰��� �����
	 * @param operatorName �߰� �۾��� �ϰ� �ִ� �����ڸ�
	 */
	public void add(User newUser, String operatorName)
	{
		//�ش� ID�� ������ ��� ���ܸ� �����µ� ���� ���� ����� ��� ��
		if(checkID(newUser.getID()) != null) ; 
		list.add(newUser);
		createUpdateMessage(UpdateMessageType.Addition, newUser.getName(), operatorName);
	}
	
	/**
	 * ����� ����Ʈ ��ġ�� �̿��� ����� ����
	 * @param idx ������ ������� ����Ʈ �� ��ġ
	 * @param operatorName ���� �۾��� �ϰ� �ִ� �����ڸ�
	 */
	public void delete(int idx, String operatorName)
	{
		User deleted = list.remove(idx);
		createUpdateMessage(UpdateMessageType.Removal, deleted.getName(), operatorName);
		deleted = null;
	}
		
	/**
	 * ������Ʈ �޼��� ���� �� �޼��� ��Ͽ� �߰�
	 * @param t ������Ʈ �޼��� ����
	 * @param tgtUserName ������Ʈ �ش��ϴ� ����� �̸�
	 * @param operatorName ������Ʈ�� �ϴ� ������ �̸�
	 */
	private void createUpdateMessage(UpdateMessageType t, String tgtUserName, String operatorName)
	{
		UpdateMessage newMessage = null;
		switch(t)
		{
		case Addition:
			newMessage = new UpdateMessage("New User " + tgtUserName + "added by " + operatorName, operatorName);
		case Removal:
			newMessage = new UpdateMessage("User " + tgtUserName + "deleted by " + operatorName, operatorName);
			break;
		default: break;
		}
		RefrigeratorSystem.addMessage(newMessage);
	}
}
