package Problem_Domain;

import java.util.Vector;

public class UserList
{
	private Vector<User> list;
	public UserList() { list = new Vector<User>(); }
	
	/**
	 * 리스트를 문자열로 변환해줌
	 * @return 문자열화 된 리스트
	 */
	public String showList()
	{
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < list.size(); ++i)
			buf.append((i + 1) + list.elementAt(i).toString() + '\n');
		return buf.toString();
	}
	
	/**
	 * 리스트에 해당하는 아이디가 있으면 User 객체를 넘겨주고 없으면 null을 넘겨줌
	 * @param ID 찾고자 하는 ID
	 * @return 찾은 경우 해당 사용자, 찾지 못한 경우 null
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
	 * 중복 체크부터 함. 그 후 사용자를 리스트에 추가.  마지막으로 메세지 생성
	 * @param newUser 새로 추가할 사용자
	 * @param operatorName 추가 작업을 하고 있는 관리자명
	 */
	public void add(User newUser, String operatorName)
	{
		//해당 ID가 존재할 경우 예외를 던지는데 예외 새로 만들어 줘야 함
		if(checkID(newUser.getID()) != null) ; 
		list.add(newUser);
		createUpdateMessage(UpdateMessageType.Addition, newUser.getName(), operatorName);
	}
	
	/**
	 * 사용자 리스트 위치를 이용해 사용자 삭제
	 * @param idx 제거할 사용자의 리스트 내 위치
	 * @param operatorName 삭제 작업을 하고 있는 관리자명
	 */
	public void delete(int idx, String operatorName)
	{
		User deleted = list.remove(idx);
		createUpdateMessage(UpdateMessageType.Removal, deleted.getName(), operatorName);
		deleted = null;
	}
		
	/**
	 * 업데이트 메세지 생성 후 메세지 목록에 추가
	 * @param t 업데이트 메세지 종류
	 * @param tgtUserName 업데이트 해당하는 사용자 이름
	 * @param operatorName 업데이트를 하는 관리자 이름
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
