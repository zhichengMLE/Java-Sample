package Thread.synchronizedBlock;
public class TestDraw
{
	public static void main(String[] args)
	{
		// ����һ���˻�
		Account acct = new Account("1234567", 1000);
		// ģ�������̶߳�ͬһ���˻�ȡǮ
		new DrawThread("��", acct, 800).start();
		new DrawThread("��", acct, 800).start();
	}
}