package Net.CS_Log;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class Client
{
	private static final int SERVER_PORT = 30000;

	private Socket socket;
	private PrintStream ps;
	private BufferedReader brServer;
	private BufferedReader keyIn;

	public void init()
	{
		try
		{
			// ��ʼ���������̵�������
			keyIn = new BufferedReader(new InputStreamReader(System.in));
			// ���ӵ�������
			socket = new Socket("127.0.0.1", SERVER_PORT);
			// ��ȡ��Socket��Ӧ���������������
			ps = new PrintStream(socket.getOutputStream());
			brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tip = "";
			// ����ѭ�����ϵص����Ի���Ҫ�������û���
			while (true)
			{
				String userName = JOptionPane.showInputDialog(tip + "�����û���");
				// ���û�������û�����ǰ������Э���ַ�������
				ps.println(customProtocol.USER_ROUND + userName + customProtocol.USER_ROUND);
				// ��ȡ����������Ӧ
				String result = brServer.readLine();
				// ����û��ظ�����ʼ�´�ѭ��
				if (result.equals(customProtocol.NAME_REP))
				{
					tip = "�û����ظ���������";
					continue;
				}
				// ������������ص�½�ɹ�������ѭ��
				if (result.equals(customProtocol.LOGIN_SUCCESS))
				{
					break;
				}
			}
		}
		// ��׽���쳣���ر�������Դ�����˳��ó���
		catch (UnknownHostException ex)
		{
			System.out.println("�Ҳ���Զ�̷���������ȷ���������Ѿ�������");
			closeRs();
			System.exit(1);
		} catch (IOException ex)
		{
			System.out.println("�����쳣�������µ�½��");
			closeRs();
			System.exit(1);
		}
		// �Ը�Socket��Ӧ������������ClientThread�߳�
		new ClientThread(brServer).start();
	}

	// ����һ����ȡ����������������緢�͵ķ���
	private void readAndSend()
	{
		try
		{
			// ���϶�ȡ��������
			String line = null;
			while ((line = keyIn.readLine()) != null)
			{
				// ������͵���Ϣ����ð�ţ�����//��ͷ������Ϊ�뷢��˽����Ϣ
				if (line.indexOf(":") > 0 && line.startsWith("//"))
				{
					line = line.substring(2);
					// ð��֮ǰ����˽���û���ð��֮�����������Ϣ
					ps.println(customProtocol.PRIVATE_ROUND + line.split(":")[0] + customProtocol.SPLIT_SIGN
							+ line.split(":")[1] + customProtocol.PRIVATE_ROUND);
				} else
				{
					ps.println(customProtocol.MSG_ROUND + line + customProtocol.MSG_ROUND);
				}
			}
		}
		// ��׽���쳣���ر�������Դ�����˳��ó���
		catch (IOException ex)
		{
			System.out.println("����ͨ���쳣�������µ�½��");
			closeRs();
			System.exit(1);
		}
	}

	// �ر�Socket����������������ķ���
	private void closeRs()
	{
		try
		{
			if (keyIn != null)
			{
				ps.close();
			}
			if (brServer != null)
			{
				ps.close();
			}
			if (ps != null)
			{
				ps.close();
			}
			if (socket != null)
			{
				keyIn.close();
			}
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		Client client = new Client();
		client.init();
		client.readAndSend();
	}
}