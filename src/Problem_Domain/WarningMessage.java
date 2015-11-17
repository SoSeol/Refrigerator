package Problem_Domain;

import java.util.Calendar;

public class WarningMessage extends Message
{
	/**
	 * Ư�� �������ڸ� ������ ��� ����� ������
	 * @param detail �޼��� ����
	 * @param created �޼��� ������
	 * @param messageUntil �Խø�������
	 */
	public WarningMessage(String detail, String created, Calendar until)
	{
		super(detail, until, created);
	}
	
	/**
	 * Ư�� �������ڸ� �������� ���� ��� ����� ������
	 * @param detail �޼��� ����
	 * @param created �޼��� ������
	 */
	public WarningMessage(String detail, String created)
	{
		this(detail, created, getAfterDay());
	}
	
}
