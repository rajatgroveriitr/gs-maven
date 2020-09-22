package java11;

import java.util.ArrayList;
import java.util.List;

public class CollectionToArrayExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("alex");
        names.add("brian");
        names.add("charles");

        String[] namesArr1 = names.toArray(new String[names.size()]);       //Before Java 11

        String[] namesArr2 = names.toArray(String[]::new);                  //Since Java 11
    }
}
