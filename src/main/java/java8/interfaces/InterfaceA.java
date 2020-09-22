package java8.interfaces;

public interface InterfaceA {
    void methodA(String str);

    //These are accessible through the instance of the implementing class and can be overridden.
    default void log(String str){
        System.out.println("InterfaceA logging::" + str);
    }

    static void print(String str){
        System.out.println("InterfaceA Printing " + str);
    }

    //trying to override Object method gives compile-time error as
    //"A default method cannot override a method from java.lang.Object"

//	default String toString(){
//		return "iA";
//	}
}
