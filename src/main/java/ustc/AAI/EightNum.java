package ustc.AAI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EightNum {
    List<Entity> open = new ArrayList<>();//open表
    List<Entity> closed = new ArrayList<>();//closed表
    List<Entity> spring = new ArrayList<>();//spring表

    int start[] = new int[9];
    int target[] = new int[9];
    Entity startEntity = new Entity();//初始状态
    Entity targetEntity = new Entity();//目标状态

    // 统计信息
    int expand = 0,generate = 0;
    /**
     * 构造方法
     * @param start 初始状态
     * @param target 目标状态
     */
    public EightNum(int[] start, int[] target){
        this.start = start;
        this.target = target;
    }

    public static void main(String[] args) {
        int[] start = {2,8,3,1,6,4,7,0,5};
        int[] target = {1,2,3,8,0,4,7,6,5};
        long startTime = System.currentTimeMillis();
        new EightNum(start,target).reslove();
        System.out.println("耗时:" + (System.currentTimeMillis()-startTime) + "毫秒");
    }

    private void init() {
        int i;
        for (i = 0; i < 9; i++) {
            startEntity.num[i] = start[i];
        }
        startEntity.gvalue = 0;
        startEntity.hvalue = calcHValue(startEntity);
        startEntity.fvalue = startEntity.gvalue + startEntity.hvalue;// f = g + h
        startEntity.parent = null;
        startEntity.next = null;
        open.add(startEntity);//初始状态加入open表中

        for (i = 0; i < 9; i++) {
            targetEntity.num[i] = target[i];
        }
        targetEntity.hvalue = calcHValue(targetEntity);
    }


    public void reslove() {
        int numcount = 1;
        Entity getOfOpen = null;
        boolean flag = false;
        init();
        // 是否有解
        if (!canResolve()) {
            System.out.println("无解！！！");
            System.exit(0);
        }
        System.out.println("********************结点的状态及相应的值********************");
        while (!open.isEmpty()) {
            getOfOpen = open.get(0);
            closed.add(getOfOpen);
            open.remove(0);//移去加入到closed表中的结点

            System.out.print("第" + numcount++ + "个状态(g="+getOfOpen.gvalue+",h="+getOfOpen.hvalue+",f="+getOfOpen.fvalue+"):");
            outputStatus(getOfOpen);
            System.out.println();
            if (getOfOpen.equals(targetEntity)) {
                flag = true;
                break;
            }
            getNexts(getOfOpen);//产生后继结点
            expand ++;

            while (!spring.isEmpty()) {
                //得到spring表中的结点
                Entity struct = spring.get(0);
                if (open.contains(struct)) {
                    //得到open表中相同的结点,注意这里重写了equals和hashcode方法
                    Entity structInOpen = open.get(open.indexOf(struct));
                    //改变open表中节点的parent指针及相关参数
                    if (struct.gvalue < structInOpen.gvalue) {
                        structInOpen.parent = struct.parent;
                        structInOpen.fvalue = struct.fvalue;
                        structInOpen.gvalue = struct.gvalue;
                        //在这里是不是应该重新排序open表
                        Collections.sort(open, new CustomComparator());
                    }
                    //删除spring表中的该节点
                    spring.remove(struct);
                } else if (closed.contains(struct)) {
                    //得到closed表中相同的结点,注意这里重写了equals和hashcode方法
                    Entity structInClosed = closed.get(closed.indexOf(struct));
                    //改变closed表中节点的parent指针及相关参数
                    if (struct.gvalue < structInClosed.gvalue) {
                        structInClosed.parent = struct.parent;
                        structInClosed.fvalue = struct.fvalue;
                        structInClosed.gvalue = struct.gvalue;
                        //加入至open表中
                        add(structInClosed, open);
                    }
                    //删除spring表中的该节点
                    spring.remove(struct);
                } else {
                    add(struct, open);
                    spring.remove(struct);
                }
                generate ++;
            }
        }
        if (flag) {// 搜索到目标状态
            System.out.println("*************************************");
            System.out.println("路径长度为:" + getOfOpen.gvalue+"，扩展节点数："+expand+",生成节点数"+generate);
//            getPath(getOfOpen);
        }
    }

    /**
     * 计算启发函数H(n)值
     * @param status 状态
     * @return int
     */
    private int calcHValue(Entity status) {
        return calcWxHValue(status);
    }

    /**
     * 启发函数：H(n) = W(n)不在位的将牌数
     * @param status 状态
     * @return int
     */
    private int calcWxHValue(Entity status) {
        int i, num = 0;
        for (i = 0; i < 9; i++) {
            if (status.num[i] != target[i] && status.num[i]!=0)
                num++;
        }
        status.hvalue = num;
        return status.hvalue;
    }

    /**
     *  启发函数：H(n) = P(n)每一个将牌与其目标位置之间距离的(不考虑夹在其间的将牌)总和
     * @param status 状态
     * @return int
     */
    @SuppressWarnings("unused")
    private int calcPxHValue(Entity status) {
        return 0;
    }

    //将某个状态加入到open表中，需要按非递减排序的
    private void add(Entity status, List<Entity> list) {
        list.add(status);
        //需要构造新的比较器NewComparator
        Collections.sort(list, new CustomComparator());
    }

    //结点与其祖先结点是否有相同的状态
    private Boolean hasAnceSameStatus(Entity origin, Entity ancester) {
        boolean flag = false;
        while (ancester != null) {
            if (origin.equals(ancester)) {
                flag = true;
                break;
            }
            ancester = ancester.parent;//寻找祖先结点
        }
        return flag;
    }

    //把数组b的值复制给数组a
    private void copySnumToTnum(int a[], int b[]) {
        int len = b.length;
        System.arraycopy(b, 0, a, 0, len);
    }

    //移动后产生后继结点
    private void getShift(Entity status, int index, int pos) {
        int medium;//中介值
        Entity temp = new Entity();
        //temp.num = status.num;传的是地址

        //复制数组的值
        copySnumToTnum(temp.num, status.num);
        //outputStatus(status);

        //右移
        if (index == 1) {
            //交换位置
            medium = temp.num[pos];
            temp.num[pos] = temp.num[pos - 1];
            temp.num[pos - 1] = medium;
            //如果与父辈结点没有相同的状态
            addToSpring(temp, status);

        }
        //下移
        else if (index == 2) {
            //交换位置
            medium = temp.num[pos];
            temp.num[pos] = temp.num[pos - 3];
            temp.num[pos - 3] = medium;
            addToSpring(temp, status);
        }
        //左移
        else if (index == 3) {
            //交换位置
            medium = temp.num[pos];
            temp.num[pos] = temp.num[pos + 1];
            temp.num[pos + 1] = medium;
            addToSpring(temp, status);
        }
        //上移
        else {
            //交换位置
            medium = temp.num[pos];
            temp.num[pos] = temp.num[pos + 3];
            temp.num[pos + 3] = medium;
            addToSpring(temp, status);
        }
    }
    // 添加到子节点集
    private void addToSpring(Entity temp,Entity status) {
        if (!hasAnceSameStatus(temp, status.parent)) {
            temp.gvalue = status.gvalue + 1;
            temp.hvalue = calcHValue(temp);
            temp.fvalue = temp.gvalue + temp.hvalue;
            temp.parent = status;
            temp.next = null;
            //加入spring表中
            spring.add(0, temp);
        }
    }

    // 产生后继结点
    private void getNexts(Entity status) {
        int pos = 0;
        int i;
        // 找到空格位置
        for (i = 0; i < 9; i++) {
            if (status.num[i] == 0) {
                pos = i;
                break;
            }
        }
        //右移（指的是非0元素右移）
        if (pos % 3 != 0) {
            getShift(status, 1, pos);
        }
        //下移
        if (pos > 2) {
            getShift(status, 2, pos);
        }
        //左移
        if (pos % 3 != 2) {
            getShift(status, 3, pos);
        }
        //上移
        if (pos < 6) {
            getShift(status, 4, pos);
        }
    }

    //得到路径
    /*private void getPath(Entity status) {
        int deepnum = status.gvalue;
        if (status.parent != null) {
            getPath(status.parent);
        }
        System.out.println("第" + deepnum + "层状态为:");
        deepnum--;
        outputStatus(status);
    }*/

    //输出状态
    private void outputStatus(Entity status) {
        for (int i = 0; i < status.num.length; i++) {
            if (i%3==0)
                System.out.println();
            System.out.print("  " + status.num[i] + " ");
        }
        System.out.println();
    }

    // 判断初始状态到目标状态是否可达
    public Boolean canResolve() {
        int i, j;
        int resultOfStart = 0;
        int resultOfTarget = 0;
        for (i = 0; i < 9; i++) {
            for (j = 0; j < i; j++) {
                if (start[j] < start[i] && start[j] != 0)
                    resultOfStart++;
                if (target[j] < target[i] && target[j] != 0)
                    resultOfTarget++;
            }
        }
        // 判断奇偶性是否一致
        return (resultOfStart + resultOfTarget) % 2 == 0;
    }
}