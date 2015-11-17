package Problem_Domain;

import java.util.Calendar;

public class Message
{
	private final static byte DEFAULT_EXPIRE_DATE = 3;
	private String messageDetail;
	private Calendar createdDate;
	private Calendar messageUntil;
	private String createdUserName;
	
	/**
	 * �޼��� ��ü ������.
	 * @param detail ����
	 * @param until �Խ� ��������
	 * @param created �޼��� ������ �����
	 */
	protected Message(String detail, Calendar until, String created)
	{
		//messageType = type;
		messageDetail = detail;
		createdDate = Calendar.getInstance();
		messageUntil = until;
		createdUserName = created;
	}
	
	public boolean isExpired() { return messageUntil.before(Calendar.getInstance()); }
	public String getCreatedUserName() { return createdUserName; }
	public Calendar getCreatedDate() { return createdDate; }
	public Calendar getEndDate() { return messageUntil; }
	public String getMessageDetail() { return messageDetail; }

	
	/**
	 * ���� ��¥���� cnt�� ���� ��¥�� ����ؼ� ��ȯ��
	 * @param cnt ���� �� ����
	 * @return ����� ��¥
	 */
	protected static Calendar getAfterDay(int cnt)
	{
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, cnt);
		return date;
	}
	
	/**
	 * �⺻ ������ ���� ��¥�� ����ؼ� ��ȯ��
	 * @return �⺻ ������ ���� ��¥
	 */
	protected static Calendar getAfterDay() { return getAfterDay(DEFAULT_EXPIRE_DATE); }
}
