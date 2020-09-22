package java9;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExampleStream9 {
    public static void main(String[] args) {

        Predicate<? super Integer> predicate = x -> x < 4; // Element must be less than 4
        Stream<Integer> orderedStream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        printTakeWhile(orderedStream, predicate);

        Stream<Integer> unorderedStream = Stream.of(1,2,4,5,3,6,7,8,9,10);
        printTakeWhile(unorderedStream, predicate); // returns first two elements which matches our Predicate.

        printDropWhile(orderedStream, predicate);
        printDropWhile(unorderedStream, predicate); // drop first two elements only which matches our Predicate and returns rest of the elements into resulted Stream.

        IntStream.iterate(2, x -> x < 20, x -> x * x).forEach(System.out::println);

//        IntStream.iterate(2, x -> x * x).filter(x -> x < 20).forEach(System.out::println); // Java 8 Way

//        Stream<Integer> s = Stream.ofNullable(1);
        Stream<Integer> s = Stream.ofNullable(null);
        s.forEach(System.out::println);
    }

    private static void printTakeWhile(Stream<Integer> stream, Predicate<? super Integer> predicate) {
        // returns longest prefix elements which matches the Predicate condition.
        stream.takeWhile(predicate)
                .forEach(a -> System.out.println(a));
    }

    private static void printDropWhile(Stream<Integer> stream, Predicate<? super Integer> predicate) {
        // drops the longest prefix elements which matches the Predicate and returns the rest of elements.
        stream.dropWhile(predicate)
                .forEach(a -> System.out.println(a));
    }
}
