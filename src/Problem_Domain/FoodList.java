package Problem_Domain;

import java.util.ArrayList;
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
	 * ������ ����Ʈ�� �߰�.  �� �� �޼��� ����
	 * @param newUser ���� �߰��� �����
	 * @param operatorName �߰� �۾��� �ϰ� �ִ� �����ڸ�
	 */
	public void add(Food newFood, String operatorName)
	{
		list.add(newFood);
		createUpdateMessage(UpdateMessageType.Addition, newFood.getName(), operatorName);
	}
	
	/**
	 * ����� ����Ʈ ��ġ�� �̿��� ����� ����
	 * @param idx ������ ������� ����Ʈ �� ��ġ
	 * @param operatorName ���� �۾��� �ϰ� �ִ� �����ڸ�
	 */
	public void delete(int idx, String operatorName)
	{
		Food deleted = list.remove(idx);
		createUpdateMessage(UpdateMessageType.Removal, deleted.getName(), operatorName);
		deleted = null;
	}
	
	/**
	 * ������Ʈ �޼��� ���� �� �޼��� ��Ͽ� �߰�
	 * @param t ������Ʈ �޼��� ����
	 * @param tgtFoodName ������Ʈ �ش��ϴ� ���� �̸�
	 * @param operatorName ������Ʈ�� �ϴ� ������ �̸�
	 */
	private void createUpdateMessage(UpdateMessageType t, String tgtFoodName, String operatorName)
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
}
