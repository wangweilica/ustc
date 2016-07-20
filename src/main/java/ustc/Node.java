package ustc;

public class Node {
    //此节点的八数码状态数组中'x'的行列值
    private int x;
    private int y;
    //此节点的八数码状态数组
    private int[][] state = new int[3][3];
    //此节点的父节点
    private Node father;
    //父节点移动生成此节点的方向
    private int dir;

    //判断此状态节点是否是最终的解状态
    public boolean isEndState() {
        int x = 1;
        for (int i = 0; i < state.length && x < 9; i++) {
            for (int j = 0; j < state.length && x < 9; j++) {
                if (state[i][j] != x)
                    return false;
                x++;
            }
        }
        return true;
    }

    //返回一个新的与此状态节点有相同八数码状态的节点
    public int[][] getNewSameState() {
        int[][] st = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                st[i][j] = state[i][j];
            }
        }
        return st;
    }

    //判断两个节点的八数码状态是否相同
    public boolean equals(Node node) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i][j] != node.getState()[i][j])
                    return false;
            }
        }
        return true;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public Node(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

}
