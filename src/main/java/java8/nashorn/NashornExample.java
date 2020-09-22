package java8.nashorn;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

public class NashornExample {

    public static void main(String[] args) throws Exception{
        // Creating script engine
        ScriptEngine ee = new ScriptEngineManager().getEngineByName("Nashorn");
        // Reading Nashorn file
        ee.eval(new FileReader("src/main/java/java8/nashorn/js/hello.js"));
        ee.eval("print('Hello Nashorn!');");

        //You can pass value to JavaScript variable in the Java file. binding and passing variable to JavaScript file.

        //Binding script and Define scope of script
        Bindings bind = ee.getBindings(ScriptContext.ENGINE_SCOPE);
        bind.put("name", "Nashorn");
        // Reading Nashorn file
        ee.eval(new FileReader("src/main/java/java8/nashorn/js/arg.js"));

        // Java provides a facility to import Java package inside the JavaScript code.
        ee.eval(new FileReader("src/main/java/java8/nashorn/js/import.js"));

        // can import multiple packages at the same time.
        ee.eval(new FileReader("src/main/java/java8/nashorn/js/multiimport.js"));

        //can call JavaScript function inside the Java file.
        ee.eval(new FileReader("src/main/java/java8/nashorn/js/function.js"));
        Invocable invocable = (Invocable)ee;
        // calling a function
        invocable.invokeFunction("functionDemo1");
        // calling a function and passing variable as well.
        invocable.invokeFunction("functionDemo2","Nashorn");
    }
}
