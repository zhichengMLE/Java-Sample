package Thread.condition;

public class DrawThread extends Thread
{
	// ģ���û��˻�
	private Account account;
	// ��ǰȡǮ�߳���ϣ��ȡ��Ǯ��
	private double drawAmount;

	public DrawThread(String name, Account account, double drawAmount)
	{
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}

	// �������߳��޸�ͬһ����������ʱ�����漰�����ݰ�ȫ���⡣
	public void run()
	{
		for (int i = 0; i < 100; i++)
		{
			account.draw(drawAmount);
		}
	}
}