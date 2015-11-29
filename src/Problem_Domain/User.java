package Problem_Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Vector;

import Essential.Pair;

public class User
{
	MessageDigest hasher;		//메세지 암호화 위한 변수
	private String name;
	private String ID;
	private byte[] PW;
	
	
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
	 * 음식 삭제
	 * @param idx 삭제하고자 하는 음식 인덱스
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
	 * 푸드 리스트 출력
	 */
	public String searchItem()
	{
		FoodList flist = RefrigeratorSystem.getFoodList();
		return flist.showList();
	}
	
	/**
	 * 고칠 내용 종류, 리스트 위치, 고칠 내용을 받아 해당 음식 정보 수정
	 * @param act 종류.  FreezerCooler / Location / Quantity / Weight
	 * @param idx 해당 음식 위치
	 * @param str 고칠 내용
	 */
	
	
	
}
