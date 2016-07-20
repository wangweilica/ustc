package ustc.AAI;

//北京工业大学 QQ 1208706282
public class Node 
{
	static int targetDigital[][]=
		{
		  {1,2,3},
		  {8,0,4},
		  {7,6,5},
		};
	public int[][]a=new int[3][3];
	int  heuristicValue;
    Node parent;
	Node next;
	int deep;
	public static int getHeuristicValue(Node node)
	{
	    //计算s与目标状态的差距值
	 	int sum = 0;
		boolean flag = false;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
 			{
 				flag = true;
 				for(int m=0;m<3 && flag;m++)
 					for(int n=0;n<3;n++)
 						if(node.a[m][n] == targetDigital[i][j])
 						{
 							sum += Math.abs(i-m)+Math.abs(j-n);
 							flag = false;
 							break;
 						}
 			}
 		}
	 	return sum+node.deep;
	}
	static Node extendNode(Node open,Node close,Node node)
	{
		Node temp;
		temp = createUpNode(node);
		if(temp != null)
			open = insertOpen(open,close,temp);
		temp = createDownNode(node);
		if(temp != null)
			open = insertOpen(open,close,temp);
		temp = createLeftNode(node);
		if(temp != null)
			open = insertOpen(open,close,temp);
		temp = createRightNode(node);
		if(temp != null)
			open = insertOpen(open,close,temp);
		return open;  
		 
	 }
	 static Node createUpNode(Node node)
	 {
		 Node temp = new Node();
		 copyArray(temp.a,node.a);
		 for(int i=0;i<3;i++)
		 { 
			 for(int j=0;j<3;j++)
			 {
				  
				 if(node.a[i][j]==0)
				 {
					 
					 if(i == 0)
						 return null;
					 else
					 {
						temp.a[i][j]=temp.a[i-1][j];
						temp.a[i-1][j]=0;
						temp.deep = node.deep+1;
						temp.heuristicValue = getHeuristicValue(temp);
						temp.parent = node;
						temp.next = null;
						return temp;
					 }
				}
			}
		  }
		  return null;
	  }	    
	  static Node createDownNode(Node node)
	  {
		  Node temp = new Node();
		  copyArray(temp.a,node.a);
		  for(int i=0;i<3;i++)
		  {
			  for(int j=0;j<3;j++)
			  {
				  if(node.a[i][j]==0)
				  {
					 if(i == 2)
						 return null;
					 else
					 {
						temp.a[i][j]=temp.a[i+1][j];
						temp.a[i+1][j]=0;
						temp.deep = node.deep+1;
						temp.heuristicValue = getHeuristicValue(temp);
						temp.parent = node;
						temp.next = null;
						return temp;
					 }
				}
			}
		  }
		  return null;
	  }	 	 
	  static Node createLeftNode(Node node)
 	  {
 		  Node temp = new Node();
 		  copyArray(temp.a,node.a);
 		  for(int i=0;i<3;i++)
 		  {
 			  for(int j=0;j<3;j++)
 			  {
 				  if(node.a[i][j]==0)
 				  {
 					 if(j == 0)
 						 return null;
 					 else
 					 {
 						temp.a[i][j]=temp.a[i][j-1];
 						temp.a[i][j-1]=0;
 						temp.deep = node.deep+1;
 						temp.heuristicValue = getHeuristicValue(temp);
 						temp.parent = node;
 						temp.next = null;
 						return temp;
 					 }
 				}
 			}
 		  }
 		  return null;
 	  }	 	
	  static Node createRightNode(Node node)
 	  {
 		  Node temp = new Node();
 		  copyArray(temp.a,node.a);
 		  for(int i=0;i<3;i++)
 		  {
 			  for(int j=0;j<3;j++)
 			  {
 				  if(node.a[i][j]==0)
 				  {
 					 if(j == 2)
 						 return null;
 					 else
 					 {
 						temp.a[i][j]=temp.a[i][j+1];
 						temp.a[i][j+1]=0;
 						temp.deep = node.deep+1;
 						temp.heuristicValue = getHeuristicValue(temp);
 						temp.parent = node;
 						temp.next = null;
 						return temp;
 					 }
 				}
 			}
 		  }
 		  return null;
 	  }	 	
	  static void print(Node node)
 	  {
 		  for(int i=0;i<3;i++)
 			  for(int j=0;j<3;j++)
 				  System.out.print(node.a[i][j]+" ");
 		  System.out.print("\n");
 	  }	 	  
	  static void copyArray(int des[][],int src[][])
 	  {
 		  for(int i=0;i<3;i++)
 			  for(int j=0;j<3;j++)
 				  des[i][j] = src[i][j];
 	  }
 	  static boolean isEqual(Node src,Node des)
 	  {
 		  for(int i=0;i<3;i++)
 			  for(int j=0;j<3;j++)
 				  if(src.a[i][j] != des.a[i][j])
 					  return false;
 		  return true;
 	  }	 	  
 	 static boolean isEqual(Node src,int des[][])
	  {
		  for(int i=0;i<3;i++)
			  for(int j=0;j<3;j++)
				  if(src.a[i][j] != des[i][j])
					  return false;
		  return true;
	  }
	  static Node insertClose(Node close,Node node)
	  {
		  if(close == null)
		  {
			  close = node;
		  }else
		  {
			  node.next = close;
			  close = node;
		  }
		  return close;
	  }	 	 
	  static Node insertOpen(Node open,Node close,Node node)
 	  {
 		  Node temp = close;
		  while(temp != null)
		  {
			 if(isEqual(temp,node))
				  return open;
			  temp = temp.next;
		  }
 		  if(open == null)
 		  {
 			  open = node;
 			  return open;
 		  }else
 		  {
 			  temp = open;
 			  while(temp != null)
 			  {
 				  if(isEqual(temp,node))
 					  return open;
 				  temp = temp.next;
 			  }
 			  temp = open;
			  if(temp.heuristicValue > node.heuristicValue)
			  {
				  node.next = temp;
				  open = node;
				  return open;
			  }
			  if(temp.next == null)
			  {
				  open.next = node;
				  node.next = null;
				  return open;
			  }
			  temp = open;
			  while(temp.next != null)//
			  {
				  if(temp.next.heuristicValue > node.heuristicValue)
				  {
					  node.next = temp.next;
					  temp.next = node;
					  return open;
				  }
				  temp = temp.next;
			  }
			  if(temp != null)
			  {
				  temp.next = node;
				  node.next = null;
			  }
 		  }
 		  return open;
 	  }	 	  
}
