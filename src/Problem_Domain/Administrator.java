package Problem_Domain;

import java.util.Scanner;

public class Administrator extends User {
	
	public Administrator(String newName, String newID, String newPW)
	{
		super(newName, newID, newPW);
	}
		
	public String searchUser()
	{		
		return RefrigeratorSystem.getUserList().showList();
	}
	
	/**
	 *  ���� ���ǿ� ���� ���� ����Ʈ���� modify ����, delete ���� ������.
	 * @param action ���� ����
	 * @param name �����ϰ� ���� ���� �̸� ����
	 */
	private void editUser(UserEditAction action,String id)
	{
	     switch(action)
	     {
	     case Modify:
	    	 ModifyUser(id);
	    	 break;
	     case Delete:
	    	 DeleteUser(id);
	    	 break;
	     }
	}
	
	/**
	 * id�� �ش��ϴ� ������ ������. ���н� false 
	 * @param id ������ ���� id
	 */
	private boolean DeleteUser(String id)
	{
		UserList ulist = RefrigeratorSystem.getUserList();
		int idx = ulist.checkIdx(id);
		// ������ ���� ���� ���� ���� ��.
		if(idx == -1)
		{
			return false;
		}
		ulist.delete(idx, this.getName());
		return true;
	}
	
	/**
	 * ������� �Է��� �޾� ���� ����.
	 * @param id �����ϰ��� �ϴ� ���� id
	 */
	private void ModifyUser(String id)
	{
		UserList ulist = RefrigeratorSystem.getUserList();
		User target = ulist.checkID(id);
		String changed;
		
		//���� ���� ���� ���� ��.
		if(target == null)
			return;
		
		System.out.println("-----Menu-----");
		System.out.println("1. modify id");
		System.out.println("2. modify pw");
		System.out.println("3. modify name");
		System.out.print(">");
		
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		switch(choice)
		{
		case 1:
			System.out.print("input chaged id: ");
			changed = input.nextLine();
			target.setID(changed);
			break;
		case 2:
			System.out.print("input chaged pw: ");
			changed = input.nextLine();
			target.setPW(changed);
			break;
		case 3:
			System.out.print("input changed name: ");
			changed = input.nextLine();
			target.setName(changed);
			break;
		default:
			break;
		}
	}
	/**
	 * ���� ����� �޼��� ���
	 * @param prev ����ϴ� ���� Ÿ��
	 * @param name
	 * @param id
	 * @param pw
	 */
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
		UserList.createUpdateMessage(UpdateMessageType.Addition, name, this.getName());
	}
	
	/**
	 * ������Ʈ ����( ����� ���  )
	 * ���� �̹� ID�����, Alarm.
	 * @param prev ����
	 * @param name �̸�
	 * @param id
	 * @param pw
	 */
	public void updateUser(UserPrevilege prev, String name, String id, String pw)
	{
		// ������ �����ϴ��� checking
		UserList list = RefrigeratorSystem.getUserList();
		if(list.checkID(id)!= null)
		{
			registerUser(prev, name, id, pw);
		}
		// ����Ϸ��� ������ �̹� �����Ѵٸ� ���� �޼���
		else
		{
			UserList.createWarningMessage(WarningMessageType.ExistUser, this.getName());
		}
	}
	public void updateUser(UserEditAction action,String id)
	{
		editUser(action,id);
	}
	
	public void deleteOldMessages()
	{
		
	}
	
	public String toString()
	{
		return "Administrator, ID : " + getID() + ", Name : " + getName();
	}

}
