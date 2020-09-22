package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExampleStream {


    public static void main(String[] args) {

        List<Integer> counting99 = new ArrayList<>();
        for(int i=0; i<100; i++)
            counting99.add(i);

        //sequential stream
        counting99.stream()
                .filter(p -> p > 90)
                .skip(5)    //discarding the first n elements
                .forEach(p -> System.out.println("High Nums sequential=" + p));

        //parallel stream
        counting99.parallelStream()
                .filter(p -> p > 90)
                .limit(5)   //truncated to be no longer than maxSize in length
                .forEach(p -> System.out.println("High Nums parallel=" + p));
        //parallel stream
        counting99.parallelStream()
                .filter(p -> p > 90)
                .forEachOrdered(p -> System.out.println("High Nums parallel Ordered=" + p)); // guarantees that the order of elements would be same as the source

        List<String> nameList = Arrays.asList("Melisandre","Sansa",null,"Jon","Daenerys",null,"Joffery");
        nameList.stream()
                .filter(Objects::nonNull)
                .filter(str -> str.length() > 6 && str.length() < 8) //Multiple conditions
                .map(str -> str + " Grover")
                .collect(Collectors.toSet()) // collecting in a set
                .forEach(System.out::println);

        System.out.println("Average of counting99 is: "+counting99.stream()
                .collect(Collectors.averagingInt(n->n))); //using averagingInt
        System.out.println("Average of counting99 is: "+counting99.stream()
                .reduce(0,Integer::sum)); //accumulating by reduce

        Map<Integer, String> hmap = new HashMap<>();
        hmap.put(1, "ABC");
        hmap.put(2, "XCB");
        hmap.put(3, "ABB");
        hmap.put(4, "ZIO");

        hmap.entrySet()
                .stream()
                .filter(p -> p.getKey().intValue() <= 2) //filter by key
                .filter(map -> map.getValue().startsWith("A")) //filter by value
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue())) // collecting in a map
                .forEach((key,value)->System.out.println(key+" - "+value));


        List<String> names =
                Arrays.asList("Jon", "Ajeet", "Steve",
                        "Ajeet", "Jon", "Ajeet");

        names.stream().collect(
                Collectors.groupingBy(  //grouping the elements of a list
                        Function.identity(), Collectors.counting()
                        )
                )
                .forEach((key,value)->System.out.println(key+" - "+value));

        isPrime(987);

        StringJoiner sj = new StringJoiner("=>");
        sj.add("one").add("two").add("three").add("four").add("five");
        System.out.println(sj);
    }

    private static boolean isPrime(int number) {
        IntPredicate isDivisible = index -> number % index == 0;
        return number > 1
                && IntStream.range(2, number).noneMatch(isDivisible);
    }
}
