package Problem_Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
}
