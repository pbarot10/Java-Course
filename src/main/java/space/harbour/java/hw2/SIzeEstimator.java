public class SizeEstimator {

    public static void main(String[] args){
        System.gc();

        int i;  // int
        Integer integer;    // reference
        String string;      // string
        Object object;      // object

        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().freeMemory());
    }
}
