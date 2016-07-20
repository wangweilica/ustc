package ustc;

import java.util.ArrayList;
import java.util.List;

public class BaShuMa {
    //方向数组
	int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
	//搜索表
	static Node[] all = new Node[1000000];
	//搜索成功后的搜索节点到初始节点的路径上的节点列表
	static List<Node> result = new ArrayList<Node>();
    //判断此节点的八数码状态是否在搜索表中已经存在
	public static boolean hasSame(Node node) {
		for (int i = 0; i < all.length; i++) {
			if (all[i] != null && node.equals(all[i]))
				return true;
		}
		return false;
	}
    //搜索八数码解的方法
	public void Search(Node node) {
		int i = 0;
		int cur = 0;
		// 把初始状态放到状态表中
		all[i++] = node;
		while (cur < 1000000) {
			if (node != null) {
				int x = 0;
				int y = 0;
				Node exNode = null;
				int[][] st = null;
				if (!node.isEndState()) {
					// 从四个方向去扩展八数码的状态
					for (int j = 0; j < 4; j++) {
						//计算移动后新的x，y坐标
						x = node.getX() + dir[j][0];
						y = node.getY() + dir[j][1];
						//产生一个新的状态节点
						exNode = new Node(x, y);
						//设置新的状态节点的八数码状态数组
						exNode.setState(node.getNewSameState());
						st = exNode.getState();
						if (x > -1 && x < 3 && y > -1 && y < 3) {
							//交换新的节点的八数码状态数组中的两个值(原位置和移动后的位置)
							int temp = st[node.getX()][node.getY()];
							st[node.getX()][node.getY()] = st[x][y];
							st[x][y] = temp;
							// 新扩展出来的状态在状态表中已经出现时，从下个方向扩展
							if (hasSame(exNode)) {
								continue;
							} else {
								// 将新扩展出来的状态加入到状态表中
								exNode.setDir(j);
								exNode.setFather(node);
								all[i++] = exNode;
								//查看此节点是不是目标节点
								if (exNode.isEndState()) {
									//把此目标节点到初始状态节点的路径上的节点依次存起来
									while (exNode != null) {
										result.add(exNode);
										exNode = exNode.getFather();

									}
									return;
								}
							}

						}
					}
				}
				//从搜索表中取出一个未扩展的节点
				node = all[++cur];
				//判断此节点是不是空，为空的话说明搜索表中的节点已经搜索完，则方法结束
				if (node != null){
					//查看此节点是不是目标节点
					if(node.isEndState()) {
						//把此目标节点到初始状态节点的路径上的节点依次存起来
						while (node != null) {
							result.add(exNode);
							node = node.getFather();
						}
						return;
					}
				} else {
					    System.out.println("没有搜索到正确解");
						return;
				}
			}

		}
	}

	public static void main(String[] args) {
		Node n = new Node(0, 2);
		n.getState()[0][0] = 1;
		n.getState()[0][1] = 2;
		n.getState()[0][2] = 9;
		n.getState()[1][0] = 5;
		n.getState()[1][1] = 6;
		n.getState()[1][2] = 3;
		n.getState()[2][0] = 4;
		n.getState()[2][1] = 7;
		n.getState()[2][2] = 8;
		// 开始搜索
		new BaShuMa().Search(n);
		System.out.println("八数码的初始状态如下:");
		printState(n);
		int finDex = result.size() - 2;
		System.out.println("八数码的移动步骤如下:");
		Node curNode = null;
		while (finDex > -1) {
			curNode = result.get(finDex);
			int dir = curNode.getDir();
			switch (dir) {
			case 0:
				curNode = result.get(finDex--);
				System.out.println("x向左  ");
				printState(curNode);
				break;
			case 1:
				curNode = result.get(finDex--);
				System.out.println("x向上  ");
				printState(curNode);
				break;
			case 2:
				curNode = result.get(finDex--);
				System.out.println("x向右  ");
				printState(curNode);
				break;
			case 3:
				curNode = result.get(finDex--);
				System.out.println("x向下  ");
				printState(curNode);
				break;
			}
		}
	}
	// 打印状态节点的对应的八数码状态
	public static void printState(Node node) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (node.getState()[i][j] == 9) {
					System.out.print('x' + " ");
				} else {
					System.out.print(node.getState()[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
