package Problem_Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

public class User
{
	MessageDigest hasher;		//�޼��� ��ȣȭ ���� ����
	private String name;
	private String ID;
	private byte[] PW;
	private UserPrevilege previlege;
	private Vector<Message> unreadMessages;
	
	protected User(String newName, String newID, String newPW, UserPrevilege prev)
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
	
	public void changePassword(String newPW)
	{
		PW = hasher.digest(newPW.getBytes());
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
		return "User " + name + "\n\tID" + ID + "\n\tPrevilege : " + previlege;
	}
}
