package Problem_Domain;

import java.util.Vector;

public class UserList
{
	private Vector<User> list;
	public UserList()
	{
		list = new Vector<User>();
	}	

	/**
	 * 리스트를 문자열로 변환해줌
	 * @return 문자열화 된 리스트
	 */
	public String showList()
	{
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < list.size(); ++i)
			buf.append((i + 1) + " : " + list.elementAt(i).toString() + "\n");
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
	 * 리스트에서 해당하는 아이디를 찾아 유저 존재시 가진 유저의 인덱스 ,그렇지 않다면 -1 을 넘겨준다. 
	 * @param ID
	 * @return idx값
	 */
	public User elementAt(int idx)
	{
		return list.elementAt(idx);
	}
	
	public boolean updateList(UpdateUserAction act, User usr, String operatorName, MessageList mList)
	{
		int idx;
		boolean bSuccess = false;
		switch(act)
		{
		case DELETE:
			idx = list.indexOf(usr);
			if(idx == -1)
			{
				bSuccess = false;
				break;
			}
			list.remove(idx);
			createUpdateMessage(UpdateMessageType.Removal, usr.getName(), operatorName, mList);
			bSuccess = true;
			break;
		case EDIT:
			idx = list.indexOf(usr);
			if(idx == -1)
			{
				bSuccess = false;
				break;
			}
			list.set(idx, usr);
			createUpdateMessage(UpdateMessageType.Modification, usr.getName(), operatorName, mList);
			bSuccess = true;
			break;
		case REGISTER:
			list.add(usr);
			createUpdateMessage(UpdateMessageType.Addition, usr.getName(), operatorName, mList);
			bSuccess = true;
			break;
		}
		return bSuccess;
	}
	
	/**
	 * 업데이트 메세지 생성 후 메세지 목록에 추가
	 * @param t 업데이트 메세지 종류
	 * @param tgtUserName 업데이트 해당하는 사용자 이름
	 * @param operatorName 업데이트를 하는 관리자 이름
	 *
	 */	
	private void createUpdateMessage(UpdateMessageType t, String tgtUserName, String operatorName, MessageList mList)
	{
		UpdateMessage newMessage = null;
		switch(t)
		{
		case Addition:
			newMessage = new UpdateMessage("New User " + tgtUserName + "added by " + operatorName, operatorName);
			break;
		case Modification:
			newMessage = new UpdateMessage("User " + tgtUserName + "modified by " + operatorName, operatorName);
			break;
		case Removal:
			newMessage = new UpdateMessage("User " + tgtUserName + "deleted by " + operatorName, operatorName);
			break;
		default: break;
		}
		mList.add(newMessage);
	}
}