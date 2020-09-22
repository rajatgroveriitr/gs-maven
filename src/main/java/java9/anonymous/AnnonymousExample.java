package java9.anonymous;

public class AnnonymousExample {
    public static void main(String[] args) {
        AbstractExample<Integer> obj = new AbstractExample<>() {
            Integer add(Integer x, Integer y) {
                return x+y;
            }
        };
        Integer sum = obj.add(100,101);
        System.out.println(sum);
    }
}
