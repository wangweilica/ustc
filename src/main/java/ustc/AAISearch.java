package ustc;

/**
 * Author: Wangwei
 * Date: 2016/7/20
 * Desc:
 */
public class AAISearch {

    public static void resolve(int[] source,int[] target) {
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
