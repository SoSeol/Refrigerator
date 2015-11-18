package Problem_Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Vector;

public class User
{
	MessageDigest hasher;		//�޼��� ��ȣȭ ���� ����
	private String name;
	private String ID;
	private byte[] PW;
	//private UserPrevilege previlege;
	private Vector<Message> unreadMessages;
	
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
	public boolean checkPassword(String str) { return PW == hasher.digest(str.getBytes()); }
	public String checkMessage()
	{
		StringBuffer buf = new StringBuffer();
		for(int i = 1; i <= unreadMessages.size(); ++i)
			buf.append(i + " : " + unreadMessages.get(i - 1));
		return buf.toString();
	}
	
	public String toString()
	{
		return "User " + name + "\n\tID" + ID;
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
	public void registerItem(String newName, int newQuantity, int newWeight, int newCalories, boolean bFreezer, int row, int col, Calendar newExpirationDate)
	{
		Food newfood = new Food(newName, col, col, col, bFreezer, col, col, newExpirationDate);
		RefrigeratorSystem.getFoodList().add(newfood, this.getName());
		FoodList.createUpdateMessage(UpdateMessageType.Addition, name, this.getName());
	}
	
	
	/**
	 * ���� ����
	 * @param idx �����ϰ��� �ϴ� ���� �ε���
	 */
	public void DeleteItem(int idx)
	{
		RefrigeratorSystem.getFoodList().delete(idx, this.getName());
	}
	
	/**
	 * Ǫ�� ����Ʈ ���
	 */
	public void searchItem()
	{
		FoodList flist = RefrigeratorSystem.getFoodList();
		flist.showList();
	}
	
	/**
	 * ���� ����� ���� edit
	 * @param newName
	 * @param newQuantity
	 * @param newWeight
	 * @param newCalories
	 * @param bFreezer
	 * @param row
	 * @param col
	 * @param newExpirationDate
	 */
	public void editItem(String newName, int newQuantity, int newWeight, int newCalories, boolean bFreezer, int row, int col, Calendar newExpirationDate)
	{
		registerItem(newName, col, col, col, bFreezer, col, col, newExpirationDate);
	}
	
	/**
	 * ���� ������ ���� edit
	 * @param idx
	 */
	public void editItem(int idx)
	{
		DeleteItem(idx);
	}
}
