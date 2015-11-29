package Problem_Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Vector;

import Essential.Pair;

public class User
{
	MessageDigest hasher;		//�޼��� ��ȣȭ ���� ����
	private String name;
	private String ID;
	private byte[] PW;
	
	
	/**
	 * ����� ������
	 * @param newName ����� �̸�
	 * @param newID ����� ID
	 * @param newPW ����� ��й�ȣ
	 * @param prev 
	 */
	protected User(String newName, String newID, String newPW)
	{
		try
		{
			// ��ȣȭ �ʱ�ȭ.
			// �ڼ��� ������� https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html ���� �ٶ�.
			hasher = MessageDigest.getInstance("SHA-256");
			name = newName;
			ID = newID;
			PW = hasher.digest(newPW.getBytes());
		}
		catch (NoSuchAlgorithmException e)	{ }
	}
	
	/**
	 * ���� �ٲ� ��й�ȣ ���ڿ��� �޾Ƽ� ����.<p>
	 * ��й�ȣ�� �ٲ� �� ���� üũ�� ���� ����
	 * @param newPW �� ��й�ȣ ���ڿ�
	 */
	public void changePassword(String newPW) { PW = hasher.digest(newPW.getBytes()); }
	public void setName(String name){	this.name = name;}
	public void setID(String ID){ this.ID = ID;}
	public void setPW(String PW){
		this.PW = hasher.digest(PW.getBytes());
	}
	public String getName() { return name; }
	public String getID() { return ID; }
	//public boolean checkPassword(String str) { return PW == hasher.digest(str.getBytes()); }
	public boolean checkPassword(String inputPW) {
		return PW.equals(hasher.digest(inputPW.getBytes()));
	}
	
	
	@Override
	public String toString()
	{
		return name+" ("+ID+")";		
	}
	
	/**
	 * ���� ���
	 * @param newName
	 * @param newQuantity
	 * @param newWeight
	 * @param newCalories
	 * @param bFreezer
	 * @param row
	 * @param col
	 * @param newExpirationDate
	 */
	
	private boolean registerFood(String newName, int newQuantity, int newWeight, int newCalories, boolean bFreezer, String newfloor, Calendar newExpirationDate)
	{
		
		Food newfood = new Food(newName, newQuantity, newWeight, newCalories, bFreezer, newfloor , newExpirationDate);
		if(newfood.isProhibited())
		{
			WarningMessage msg = new WarningMessage(name + "tried to put in forbidden item \"" + newfood.getName() + '\"', "System");
			RefrigeratorSystem.getMessageList().add(msg);
			return false;
		}
		
		RefrigeratorSystem.getFoodList().updateList(newfood, UpdateUserAction.REGISTER);
		return true;
	}
	
	
	/**
	 * ���� ����
	 * @param idx �����ϰ��� �ϴ� ���� �ε���
	 */
	private void deleteFood(int idx)
	{
		
		Food delete_food;
		delete_food = RefrigeratorSystem.getFoodList().elementAt(idx);
		RefrigeratorSystem.getFoodList().updateList(delete_food,UpdateUserAction.DELETE);
	}
	
	private void modifyFood(FoodEditType type, int idx, String editData)
	{
		Food modify_food = RefrigeratorSystem.getFoodList().elementAt(idx);
		Food tgt = RefrigeratorSystem.getFoodList().elementAt(idx);
		switch(type)
		{
			case FreezerCooler:
				switch(editData)
				{
				case "Freezer":
					if(!tgt.isFreezerItem()) tgt.toggleFreezerCooler();
					break;
				case "Cooler":
					if(tgt.isFreezerItem()) tgt.toggleFreezerCooler();
					break;
				}
				break;
			case Location:
				tgt.setFloor(editData);
				break;
			case Quantity:
				tgt.setQuantity(Integer.parseInt(editData));
				break;
			case Weight:
				tgt.setWeight(Integer.parseInt(editData));
				break;
		}
		RefrigeratorSystem.getFoodList().updateList(modify_food, UpdateUserAction.EDIT);
	
	}
	
	/**
	 * Ǫ�� ����Ʈ ���
	 */
	public String searchItem()
	{
		FoodList flist = RefrigeratorSystem.getFoodList();
		return flist.showList();
	}
	
	/**
	 * ��ĥ ���� ����, ����Ʈ ��ġ, ��ĥ ������ �޾� �ش� ���� ���� ����
	 * @param act ����.  FreezerCooler / Location / Quantity / Weight
	 * @param idx �ش� ���� ��ġ
	 * @param str ��ĥ ����
	 */
	
	
	
}
