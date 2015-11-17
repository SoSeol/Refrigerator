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
	 * 메세지 객체 생성자.
	 * @param detail 내용
	 * @param until 게시 만료일자
	 * @param created 메세지 생성한 사용자
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
	 * 현재 날짜에서 cnt일 후의 날짜를 계산해서 반환함
	 * @param cnt 몇일 후 지정
	 * @return 계산한 날짜
	 */
	protected static Calendar getAfterDay(int cnt)
	{
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, cnt);
		return date;
	}
	
	/**
	 * 기본 만료일 후의 날짜를 계산해서 반환함
	 * @return 기본 만료일 후의 날짜
	 */
	protected static Calendar getAfterDay() { return getAfterDay(DEFAULT_EXPIRE_DATE); }
}
