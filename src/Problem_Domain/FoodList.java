package Problem_Domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class FoodList {
	private static ArrayList<String> prohibitedList;
	private Vector<Food> list;

	public FoodList() {
		prohibitedList = new ArrayList<String>();
		prohibitedList.add("����");
		prohibitedList.add("����ȭ��");
		prohibitedList.add("����");
		prohibitedList.add("�ʷ���");
		prohibitedList.add("�븶��");
		prohibitedList.add("û�갡��");
		list = new Vector<Food>();
	}

	public static ArrayList<String> getProhibitedList() {
		return prohibitedList;
	}

	/**
	 * ����Ʈ�� ���ڿ��� ��ȯ����
	 * 
	 * @return ���ڿ�ȭ �� ����Ʈ
	 */
	public String showList() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < list.size(); ++i)
			buf.append((i + 1) + " : " + list.elementAt(i).toString() + '\n');
		return buf.toString();
	}

	public String searchList(String name) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < list.size(); ++i) {
			if (list.elementAt(i).getName().compareTo(name) == 0)
				buf.append((i + 1) + " : " + list.elementAt(i).toString()
						+ '\n');
		}
		return buf.toString();
	}

	public void checkProhibited(MessageList mList) {
		for (Food tmp : list) {
			if (tmp.isProhibited())
				createWarningMessage(WarningMessageType.ForbiddenFood, tmp,
						"System", mlist);
		}
	}

	/**
	 * ������� �����ų� 3�� ���Ϸ� ���Ҵ��� Ȯ���� �� ��� �޼��� ����.
	 * <p>
	 */
	public void checkExpired(MessageList mList) {
		for (Food tmp : list) {
			if (tmp.isExpired())
				createWarningMessage(WarningMessageType.FoodExpired, tmp,
						"System");
			else if (Calendar.getInstance().compareTo(tmp.getExpirationDate()) <= 3)
				createWarningMessage(WarningMessageType.FoodNearExpiration,
						tmp, "System");
		}
	}

	public boolean updateList(UpdateUserAction act, Food food, String operatorName, MessageList mList)
	{
		int idx;
		boolean bSuccess = false;
		switch(act)
		{
		case DELETE:
			idx = list.indexOf(food);
			if(idx == -1)
			{
				bSuccess = false;
				break;
			}
			list.remove(idx);
			createUpdateMessage(UpdateMessageType.Removal, food.getName(), operatorName, mList);
			bSuccess = true;
			break;
		case EDIT:
			idx = list.indexOf(food);
			if(idx == -1)
			{
				bSuccess = false;
				break;
			}
			list.set(idx, food);
			createUpdateMessage(FoodEditType type, food.getName(), operatorName, mList);
			bSuccess = true;
			break;
		case REGISTER:
			list.add(food);
			createUpdateMessage(UpdateMessageType.Addition, food.getName(), operatorName, mList);
			bSuccess = true;
			break;
		}
		return bSuccess;
	}

	/**
	 * ������Ʈ �޼��� ���� �� �޼��� ��Ͽ� �߰�
	 * 
	 * @param t
	 *            ������Ʈ �޼��� ����
	 * @param tgtFoodName
	 *            ������Ʈ �ش��ϴ� ���� �̸�
	 * @param operatorName
	 *            ������Ʈ�� �ϴ� ������ �̸�
	 */
	private void createUpdateMessage(UpdateMessageType t, String tgtFoodName,
			String operatorName, MessageList messagelist) {
		UpdateMessage newMessage = null;
		switch (t) {
		case Addition:
			newMessage = new UpdateMessage("New Food " + tgtFoodName
					+ "stored by " + operatorName, operatorName);
		case Removal:
			newMessage = new UpdateMessage("Food " + tgtFoodName + "taken by "
					+ operatorName, operatorName);
			break;
		default:
			break;
		}
		if (null != newMessage)
			messagelist.add(newMessage);
	}

	/**
	 * ���� ���� ������Ʈ �޼��� ���� �� �޼��� ��Ͽ� �߰�
	 * 
	 * @param act
	 *            ������ �׸�
	 * @param tgtFoodName
	 *            ������ ���ĸ�
	 * @param operatorName
	 *            ������ ��� �̸�
	 */
	private void createUpdateMessage(FoodEditType type, String tgtFoodName,
			String operatorName, MessageList messagelist) {
		UpdateMessage newMessage = null;
		switch (type) {
		case FreezerCooler:
		case Location:
			newMessage = new UpdateMessage("Food " + tgtFoodName
					+ " was moved by " + operatorName, operatorName);
			break;
		case Weight:
			newMessage = new UpdateMessage("Food " + tgtFoodName
					+ "'s weight was modified by " + operatorName, operatorName);
			break;
		case Quantity:
			newMessage = new UpdateMessage("Food " + tgtFoodName
					+ "'s quantity was modified by " + operatorName,
					operatorName);
			break;
		}
		if (null != newMessage)
			messagelist.add(newMessage);
	}

	/**
	 * warning �޼��� ������ �޼��� ��Ͽ� �߰�
	 * 
	 * @param t
	 *            ���׸޼��� ����
	 * @param FoodName
	 *            �˶� ��� ����
	 * @param tgtUserName
	 *            ���� ���� ���
	 */
	private void createWarningMessage(WarningMessageType t, Food tgtFood, String tgtUserName,MessageList list)
	{
		WarningMessage newMessage = null;
		switch(t)
		{
		case FoodExpired:
			newMessage= new WarningMessage("Food expired : Name -> " + tgtFood.getName() +
			         						", Location : " + (tgtFood.isFreezerItem()? "Freezer" : "Cooler") + 
			        						", Floor " + tgtFood.getFloor(), tgtUserName);
			break;
		case FoodNearExpiration:
			newMessage = new WarningMessage("Food near to expired in "+ Calendar.getInstance().compareTo(tgtFood.getExpirationDate()) + 
			           						" days : Name -> " + tgtFood.getName() + ", Location : " +
			           						(tgtFood.isFreezerItem()? "Freezer" : "Cooler") ", Floor " + tgtFood.getFloor(), tgtUserName);
			break;
		case ForbiddenFood:
			newMessage = new WarningMessage("Prohibited food in refrigerator : " + tgtFood.getName(), tgtUserName); 
		default: break;
		}
		if(newMessage != null)
			list.add(newMessage);
	}

}
