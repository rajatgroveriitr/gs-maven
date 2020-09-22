package java9;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ExampleOptional9 {
    public static void main(String[] args) {

        Stream<Optional> emp = Stream.of(Optional.of(1), Optional.of(2), Optional.of(3));
        Stream empStream = emp.flatMap(Optional::stream);
        empStream.forEach(System.out::println);
        
        Optional<Integer> opt = Optional.of(4);
        opt.ifPresentOrElse( x -> System.out.println("Result found: " + x), () -> System.out.println("Not Found."));
        Optional<Integer> optEmpty = Optional.empty();
        optEmpty.ifPresentOrElse( x -> System.out.println("Result found: " + x), () -> System.out.println("Not Found."));

        Optional<String> opWithStr = Optional.of("Rams");
        Supplier<Optional<String>> supStr = () -> Optional.of("No Name");
        opWithStr.or(supStr);

        Optional<String> opEmptyStr = Optional.empty();
        opEmptyStr.or(supStr);

    }
}
