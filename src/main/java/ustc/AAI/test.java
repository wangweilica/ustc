package ustc.AAI;

import java.util.Scanner;
public class test 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Node first = new Node();
		Node open = null;
		Node close = null;
		System.out.print("请输入初始状态的8数码（按每行从左往右依次输入,用0表示空格）：\n");
	    for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				first.a[i][j]=scanner.nextInt();
	    first.parent = null;
	    first.next = null;
	    first.deep = 0;
	    first.heuristicValue = first.getHeuristicValue(first);
		open=Node.insertOpen(open, close, first);
		Node temp = new Node();
		if(open != null)
			temp = open;
		open = open.next;
		int number=0;
		while(temp != null)
		{
			if(Node.isEqual(temp, Node.targetDigital))
			{
				System.out.println("find the target");
				break;
			}
			close = Node.insertClose(close, temp);
			open = Node.extendNode(open, close, temp);
			if(open == null)
			{
				System.out.println("open is null");
				return;
			}
			temp = open;
			open = open.next;
			number++;
			if(open != null && open.deep == 25) //控制搜素深度不超过25
			{
				System.out.println("deep is 25");
				return;
			}
		}
		if(temp != null)
		{
			number=1;
			Node head;
			/*while(temp != null)
			{
				System.out.println("---------"+number+++"---------");
				Node.print(temp);
				temp = temp.parent;
			}*/
			temp.next = null;
			while(temp.parent != null)
			{
				head = temp.parent;
				head.next = temp;
				temp = head;
			}
			while(temp!= null)
			{
				System.out.println("---------"+number+++"---------");
				Node.print(temp);
				temp = temp.next;
			}
		}
		
	}
}
