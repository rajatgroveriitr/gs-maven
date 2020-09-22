package java8.interfaces;

@FunctionalInterface
public interface InterfaceB {
    //Functional has only one abstract non-Object method
    void methodB();

    default void log(String str){
        System.out.println("InterfaceB logging::" + str);
    }

    // It can contain any number of Object class public methods.
    int hashCode();
    String toString();
    boolean equals(Object obj);

    // Not functional because method Object.clone is not public
    // Object clone();
}

//interface X { Iterable m(Iterable<String> arg); }
//interface Y { Iterable<String> m(Iterable arg); }
//interface Z extends X, Y {}
// Functional: Y.m is a subsignature & return-type-substitutable