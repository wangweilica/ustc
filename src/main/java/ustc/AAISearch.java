package ustc;

/**
 * Author: Wangwei
 * Date: 2016/7/20
 * Desc:八数码问题
 */
public class AAISearch {

    /**
     * 启发函数：W(n)不在位的将牌数
     * @param source 初试状态
     * @param target 目标状态
     */
    public static void resolveWithW(int[] source,int[] target) {
        if (source.length ==  AAIUtil.ARRAY_LEN && target.length == AAIUtil.ARRAY_LEN) {


        }
    }

    /**
     * 启发函数：P(n)每一个将牌与其目标位置之间距离的(不考虑夹在其间的将牌)总和
     * @param source 初试状态
     * @param target 目标状态
     */
    public static void resolveWithP(int[] source,int[] target) {
        if (source.length ==  AAIUtil.ARRAY_LEN && target.length == AAIUtil.ARRAY_LEN) {


        }
    }




    public static void main(String[] args) {
        int[] source = {2,8,3,1,6,4,7,0,5};
        int[] target = {1,2,3,8,0,4,7,6,5};
        System.out.println(AAIUtil.hasSolution(source,target));
//        Arrays.stream(px(source)).forEach(n->System.out.print(n+","));
    }
}
