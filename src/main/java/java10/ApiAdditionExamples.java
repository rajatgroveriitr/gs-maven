package java10;

import java.util.List;
import java.util.stream.Collectors;

public class ApiAdditionExamples {
    public static void main(String[] args) {
        List<Integer> someIntList = List.of(1, 3, 5, 7, 9, 8, 6, 4, 2, 0);
        whenListContainsInteger_OrElseThrowReturnsInteger(someIntList);

        whenModifyCopyOfList_thenThrowsException(someIntList);

        whenModifyToUnmodifiableList_thenThrowsException(someIntList);
    }

    public static void whenListContainsInteger_OrElseThrowReturnsInteger(List<Integer> someIntList) {
        Integer firstEvenInList = someIntList.stream()
                .filter(i -> i % 2 == 0)
                .findFirst()
                .orElseThrow();
        System.out.println("firstEvenInList : " + firstEvenInList);
    }

    public static void whenModifyCopyOfList_thenThrowsException(List<Integer> someIntList) {
        List<Integer> copyList = List.copyOf(someIntList); // unmodifiable copy
//        copyList.add(4); // UnsupportedOperationException
    }

    public static void whenModifyToUnmodifiableList_thenThrowsException(List<Integer> someIntList) {
        List<Integer> evenList = someIntList.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableList());
//        evenList.add(4); //UnsupportedOperationException
    }
}
