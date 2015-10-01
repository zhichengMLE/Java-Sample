package Swing;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.awt.*;


public class testJTree4
{
	JFrame jf = new JFrame("���ݽڵ����Ͷ���ͼ��");

	JTree tree;
	//���弸����ʼ�ڵ�
	DefaultMutableTreeNode root = new DefaultMutableTreeNode(
		new NodeData(DBObjectType.ROOT , "���ݿ⵼��")); 
	DefaultMutableTreeNode salaryDb = new DefaultMutableTreeNode(
		new NodeData(DBObjectType.DATABASE , "��˾�������ݿ�"));
	DefaultMutableTreeNode customerDb = new DefaultMutableTreeNode(
		new NodeData(DBObjectType.DATABASE , "��˾�ͻ����ݿ�"));
	//����salaryDb�������ӽڵ�
	DefaultMutableTreeNode employee = new DefaultMutableTreeNode(
		new NodeData(DBObjectType.TABLE , "Ա����"));
	DefaultMutableTreeNode attend = new DefaultMutableTreeNode(
		new NodeData(DBObjectType.TABLE , "���ڱ�"));
	//����customerDb��һ���ӽڵ�
	DefaultMutableTreeNode contact = new DefaultMutableTreeNode(
		new NodeData(DBObjectType.TABLE , "��ϵ��ʽ��"));

	//����employee�������ӽڵ�
	DefaultMutableTreeNode id = new DefaultMutableTreeNode(
		new NodeData(DBObjectType.INDEX , "Ա��ID"));
	DefaultMutableTreeNode name = new DefaultMutableTreeNode(
		new NodeData(DBObjectType.COLUMN , "����"));
	DefaultMutableTreeNode gender = new DefaultMutableTreeNode(
		new NodeData(DBObjectType.COLUMN , "�Ա�"));

	public void init()throws Exception
	{
		//ͨ��add�����������ڵ�֮��ĸ��ӹ�ϵ
		root.add(salaryDb);
		root.add(customerDb);
		salaryDb.add(employee);
		salaryDb.add(attend);
		customerDb.add(contact);
		employee.add(id);
		employee.add(name);
		employee.add(gender);
		//�Ը��ڵ㴴����
		tree = new JTree(root);
		
		//���ø�JTreeʹ���Զ���Ľڵ������
		tree.setCellRenderer(new MyRenderer());
	
		//�����Ƿ���ʾ���ڵ�ġ�չ��/�۵���ͼ��,Ĭ����false
		tree.setShowsRootHandles(true);
		//���ýڵ��Ƿ�ɼ�,Ĭ����true
		tree.setRootVisible(true);

		//����ʹ��Windows������
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		//����JTree��UI���
		SwingUtilities.updateComponentTreeUI(tree);

		jf.add(new JScrollPane(tree));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

    public static void main(String[] args) throws Exception
    {
		new testJTree4().init();
    }
}
//����һ��NodeData�࣬���ڷ�װ�ڵ�����
class NodeData
{
	public int nodeType;
	public String nodeData;
	public NodeData(int nodeType , String nodeData)
	{
		this.nodeType = nodeType;
		this.nodeData = nodeData;
	}
	public String toString()
	{
		return nodeData;
	}
}
//����һ���ӿڣ��ýӿ���������ݿ�������͵ĳ���
interface DBObjectType
{
	int ROOT = 0;
	int DATABASE = 1;
	int TABLE = 2;
	int COLUMN = 3;
	int INDEX = 4;
}
class MyRenderer extends DefaultTreeCellRenderer
{
	//��ʼ��5��ͼ��
	ImageIcon rootIcon = new ImageIcon("icon/root.gif");
	ImageIcon databaseIcon = new ImageIcon("icon/database.gif");
	ImageIcon tableIcon = new ImageIcon("icon/table.gif");
	ImageIcon columnIcon = new ImageIcon("icon/column.gif");
	ImageIcon indexIcon = new ImageIcon("icon/index.gif");

	public Component getTreeCellRendererComponent(JTree tree, Object value,
		boolean sel, boolean expanded,  boolean leaf, int row, boolean hasFocus)
	{
		//ִ�и���Ĭ�ϵĽڵ���Ʋ���
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		NodeData data = (NodeData)node.getUserObject();
		//�������ݽڵ����nodeType���ݾ����ڵ�ͼ��
		ImageIcon icon = null;
		switch(data.nodeType)
		{
			case DBObjectType.ROOT:
				icon = rootIcon;
				break;
			case DBObjectType.DATABASE:
				icon = databaseIcon;
				break;
			case DBObjectType.TABLE:
				icon = tableIcon;
				break;
			case DBObjectType.COLUMN:
				icon = columnIcon;
				break;
			case DBObjectType.INDEX:
				icon = indexIcon;
				break;
		}
		//�ı�ͼ��
		this.setIcon(icon);
		return this;
	}
}