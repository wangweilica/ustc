package ustc;

import java.util.Arrays;

/**
 * Author: Wangwei
 * Date: 2016/7/20
 * Desc:
 */
public class AAI {

    private static final int ARRAY_LEN = 9;

    public static void main(String[] args) {
        int[] source = {2,8,3,1,6,4,7,0,5};
        int[] target = {1,2,3,8,0,4,7,6,5};
        System.out.println(hasSolution(source,target));
//        Arrays.stream(px(source)).forEach(n->System.out.print(n+","));
    }

    public static void resolve(int[] source,int[] target) {
        if (source.length ==  ARRAY_LEN && target.length == ARRAY_LEN) {


        }
    }

    /**
     * 判断奇偶性是否一致
     * @param r r
     * @param t t
     * @return boolean
     */
    private static boolean judgeParity(int r, int t) {
        return r%2==t%2;
    }
    /**
     * x数组中每个元素 ：x数所在位置前面的数比x小的数的个数
     * @param t 待处理数组
     * @return int[]
     */
    private static int[] px(int[] t) {
        int[] px = new int[ARRAY_LEN-2];// 去除第一个元素和0元素（即空格元素）
        int index = 0;// px数组index
        for (int i = 1;i<ARRAY_LEN;i++) {
            int tmp = 0;// 临时变量，比当前元素小的元素个数
            if (t[i] > 0) {// 排除空格元素　　
                for (int j=i-1;j>=0;j--) {// 循环当前元素前面的元素
                    if (t[j]>0 && t[j]<t[i]) {// 当前元素x所在位置前面的数比x小
                        tmp++;
                    }
                }
                px[index] = tmp;
                index++;
            }
        }
        return px;
    }

    /**
     * 判断是否有解
     * @param source 原始状态
     * @param target 目标状态
     * @return boolean
     */
    private static boolean hasSolution(int[] source,int[] target) {
        if (source.length ==  ARRAY_LEN && target.length == ARRAY_LEN) {
            int t = Arrays.stream(px(source)).sum();//初试状态px求和
            int r = Arrays.stream(px(target)).sum();// 目标状态px求和
            return judgeParity(r,t);
        }
        return false;
    }

}