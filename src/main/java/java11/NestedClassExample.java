package java11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NestedClassExample {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        NestedClassExample ob = new NestedClassExample();
        Method method = ob.getClass().getDeclaredMethod("myPrivate");
        method.invoke(ob);
    }


    public void myPublic() {
    }

    private void myPrivate() {
    }

    class Nested {

        public void nestedPublic() {
            myPrivate();
        }
    }
}
