package Problem_Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Vector;

public class User
{
	MessageDigest hasher;		//메세지 암호화 위한 변수
	private String name;
	private String ID;
	private byte[] PW;
	//private UserPrevilege previlege;
	private Vector<Message> unreadMessages;
	
	/**
	 * 사용자 생성자
	 * @param newName 사용자 이름
	 * @param newID 사용자 ID
	 * @param newPW 사용자 비밀번호
	 * @param prev 
	 */
	protected User(String newName, String newID, String newPW)
	{
		try
		{
			// 암호화 초기화.
			// 자세한 사용방법은 https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html 참고 바람.
			hasher = MessageDigest.getInstance("SHA-256");

			name = newName;
			ID = newID;
			PW = hasher.digest(newPW.getBytes());
		}
		catch (NoSuchAlgorithmException e)	{ }
	}
	
	/**
	 * 새로 바꿀 비밀번호 문자열을 받아서 저장.<p>
	 * 비밀번호를 바꿀 떄 길이 체크는 하지 않음
	 * @param newPW 새 비밀번호 문자열
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
	 * 음식 등록
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
	 * 음식 삭제
	 * @param idx 삭제하고자 하는 음식 인덱스
	 */
	public void DeleteItem(int idx)
	{
		RefrigeratorSystem.getFoodList().delete(idx, this.getName());
	}
	
	/**
	 * 푸드 리스트 출력
	 */
	public void searchItem()
	{
		FoodList flist = RefrigeratorSystem.getFoodList();
		flist.showList();
	}
	
	/**
	 * 음식 등록을 위한 edit
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
	 * 음식 삭제를 위한 edit
	 * @param idx
	 */
	public void editItem(int idx)
	{
		DeleteItem(idx);
	}
}
