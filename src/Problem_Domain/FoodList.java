package Problem_Domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class FoodList
{
	private static ArrayList<String> prohibitedList;
	private Vector<Food> list;
	
	public FoodList()
	{
		prohibitedList = new ArrayList<String>();
		list = new Vector<Food>();
	}
	
	public static ArrayList<String> getProhibitedList() { return prohibitedList; }
	
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
	 * 음식을 리스트에 추가.  그 후 메세지 생성
	 * @param newUser 새로 추가할 사용자
	 * @param operatorName 추가 작업을 하고 있는 관리자명
	 */
	public void add(Food newFood, String operatorName)
	{
		list.add(newFood);
		createUpdateMessage(UpdateMessageType.Addition, newFood.getName(), operatorName);
	}
	
	/**
	 * 사용자 리스트 위치를 이용해 사용자 삭제
	 * @param idx 제거할 사용자의 리스트 내 위치
	 * @param operatorName 삭제 작업을 하고 있는 관리자명
	 */
	public void delete(int idx, String operatorName)
	{
		Food deleted = list.remove(idx);
		createUpdateMessage(UpdateMessageType.Removal, deleted.getName(), operatorName);
		deleted = null;
	}
	
	/**
	 * 업데이트 메세지 생성 후 메세지 목록에 추가
	 * @param t 업데이트 메세지 종류
	 * @param tgtFoodName 업데이트 해당하는 음식 이름
	 * @param operatorName 업데이트를 하는 관리자 이름
	 */
	static void createUpdateMessage(UpdateMessageType t, String tgtFoodName, String operatorName)
	{
		UpdateMessage newMessage = null;
		switch(t)
		{
		case Addition:
			newMessage = new UpdateMessage("New Food " + tgtFoodName + "stored by " + operatorName, operatorName);
		case Removal:
			newMessage = new UpdateMessage("Food " + tgtFoodName + "taken by " + operatorName, operatorName);
			break;
		case Modification:
			break;
		default: break;
		}
		RefrigeratorSystem.getMessageList().add(newMessage);
	}
	
	/**
	 * warning 메세지 생성후 메세지 목록에 추가
	 * @param t 워닝메세지 종류
	 * @param FoodName 알람 대상 음식
	 * @param tgtUserName 음식 넣은 사람
	 */
	static void createWarningMessage(WarningMessageType t, String FoodName, String tgtUserName)
	{
		WarningMessage newMessage = null;
		switch(t)
		{
		case ForbiddenFood:
			newMessage = new WarningMessage(FoodName+" is forbidden food",tgtUserName);
			break;
			
		default: break;
		}
		RefrigeratorSystem.getMessageList().add(newMessage);
	}
	
	public void checkExpired()
	{
		for(Food tmp: list) //하나하나 들어감
		{
			WarningMessage msg = null;
			if(tmp.isExpired())
			{
				msg	= new WarningMessage("Food expired : Name -> " + tmp.getName() +
						", Location : " + (tmp.isFreezerItem()? "Freezer" : "Cooler") + 
						", Row " + tmp.getLocation().first + ", Column " + tmp.getLocation().second, "System");
			
			}
			else if(Calendar.getInstance().compareTo(tmp.getExpirationDate()) <= 3)
			{
				msg = new WarningMessage("Food near to expired in "+ Calendar.getInstance().compareTo(tmp.getExpirationDate()) + 
						" days : Name -> " + tmp.getName() +
						", Location : " + (tmp.isFreezerItem()? "Freezer" : "Cooler") + 
						", Row " + tmp.getLocation().first + ", Column " + tmp.getLocation().second, "System");
			}
			
			if(msg != null) 
				RefrigeratorSystem.getMessageList().add(msg);
		}
	}
}
