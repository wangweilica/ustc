package ustc.AAI;

public class Entity {
    int num[] = new int[9];//状态
    Entity parent;//父节点
    Entity next;//open表或closed表中的后一个节点
    int fvalue;//总路径
    int gvalue;//实际路径
    int hvalue;//节点到达目标状态的艰难程度

    /**
     * 重写hashcode和equals方法
     */
    @Override
    public int hashCode() {
        return this.hvalue;
    }

    @Override
    public boolean equals(Object obj) {
        Boolean flag = true;
        if (obj instanceof Entity) {
            Entity p = (Entity) obj;
            for (int i = 0; i < 9; i++) {
                if (p.num[i] != this.num[i])
                    flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) {
        int i = 0;
        int index = 3;
        int d = Math.abs(i/3-index/3)+Math.abs(i%3-index%3);
        System.out.println(d);
    }
}