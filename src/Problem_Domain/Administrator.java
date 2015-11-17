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
	 *  유저 엑션에 따라 유저 리스트에서 modify 할지, delete 할지 결정함.
	 * @param action 선택 종류
	 * @param name 수정하고 싶은 유저 이름 설정
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
	 * id에 해당하는 유저를 삭제함. 실패시 false 
	 * @param id 삭제할 유저 id
	 */
	private boolean DeleteUser(String id)
	{
		UserList ulist = RefrigeratorSystem.getUserList();
		int idx = ulist.checkIdx(id);
		// 삭제할 유저 존재 하지 않을 시.
		if(idx == -1)
		{
			return false;
		}
		ulist.delete(idx, this.getName());
		return true;
	}
	
	/**
	 * 사용자의 입력을 받아 유저 수정.
	 * @param id 수정하고자 하는 유저 id
	 */
	private void ModifyUser(String id)
	{
		UserList ulist = RefrigeratorSystem.getUserList();
		User target = ulist.checkID(id);
		String changed;
		
		//유저 존재 하지 않을 때.
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
	 * 유저 등록휴 메세지 출력
	 * @param prev 등록하는 유저 타입
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
	 * 업데이트 유저( 사용자 등록  )
	 * 만약 이미 ID존재시, Alarm.
	 * @param prev 권한
	 * @param name 이름
	 * @param id
	 * @param pw
	 */
	public void updateUser(UserPrevilege prev, String name, String id, String pw)
	{
		// 유저가 존재하는지 checking
		UserList list = RefrigeratorSystem.getUserList();
		if(list.checkID(id)!= null)
		{
			registerUser(prev, name, id, pw);
		}
		// 등록하려는 유저가 이미 존재한다면 워닝 메세지
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
