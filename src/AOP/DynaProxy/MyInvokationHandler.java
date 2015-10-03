package AOP.DynaProxy;

import java.lang.reflect.*;

public class MyInvokationHandler implements InvocationHandler
{
	// ��Ҫ�������Ķ���
	private Object target;

	public void setTarget(Object target)
	{
		this.target = target;
	}

	// ִ�ж�̬������������з���ʱ�����ᱻ�滻��ִ�����µ�invoke����
	public Object invoke(Object proxy, Method method, Object[] args) throws Exception
	{
		DogUtil du = new DogUtil();
		// ִ��DogUtil�����е�method1��
		du.method1();
		// ��target��Ϊ������ִ��method����
		Object result = method.invoke(target, args);
		// ִ��DogUtil�����е�method2��
		du.method2();
		return result;
	}
}