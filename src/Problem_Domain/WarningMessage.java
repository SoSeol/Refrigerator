package Problem_Domain;

import java.util.Calendar;

public class WarningMessage extends Message
{
	/**
	 * 특정 종료일자를 지정할 경우 사용할 생성자
	 * @param detail 메세지 내용
	 * @param created 메세지 제작자
	 * @param messageUntil 게시만료일자
	 */
	public WarningMessage(String detail, String created, Calendar until)
	{
		super(detail, until, created);
	}
	
	/**
	 * 특정 종료일자를 지정하지 않을 경우 사용할 생성자
	 * @param detail 메세지 내용
	 * @param created 메세지 제작자
	 */
	public WarningMessage(String detail, String created)
	{
		this(detail, created, getAfterDay());
	}
	
}
