package ustc.AAI;

/**
 * Author: Wangwei
 * Date: 2016/7/22
 * Desc:
 */
public class Test {

    public static void main(String[] args) {
        int[] start = {1,5,8,6,3,2,4,7,0};// 初始状态
        int[] target = {1,2,3,4,5,6,7,8,0};// 目标状态
        int cost1 = 0,cost2=0;
//        for (int i = 0;i<1;i++) {
//            cost1 += testWx(start,target);;
//        }
        for (int i = 0;i<1;i++) {
            cost2 += testPx(start,target);
        }
        System.out.println("*************************************性能统计结果*******************************************************");
        System.out.println("h=W(n)耗时:" + cost1 + "毫秒");
        System.out.println("h=P(n)耗时:" + cost2 + "毫秒");
    }

    // 测试W(n)
    private static long testWx(int[] start,int[] target) {
        long startTime = System.currentTimeMillis();
        new EightNum(start,target,Heuristic.NOT_IN_POSITION).reslove();
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    // 测试P(n)
    private static long testPx(int[] start,int[] target) {
        long startTime = System.currentTimeMillis();
        new EightNum(start,target,Heuristic.MANHATTAN_DISTANCE).reslove();
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }
}
