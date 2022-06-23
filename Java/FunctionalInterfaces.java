package Topics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FunctionalInterfaces implements Runnable {
    @Override
    public void run() {
        // Functional Interfaces are what defines Predicates, Consumer and Supplier in Java.
        // Consumer, Predicate, Supplier and other are annotated with @FunctionalInterface
        // Consumer is similar to the C# Action<T>, which has no output.
        // Supplier is similar to Func<out T>, which has a return type of <T> usage could be as a Factory.

        List<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(1);
        numbers.add(62);
        numbers.add(33);
        System.out.println(numbers);
        // Example of Comparator (another Functional Interface)
        numbers.sort((num1, num2) -> {
            if (num1.equals(num2))
                return 0;
            else if (num1 > num2)
                return 1;
            else
                return -1;
        });
        System.out.println(numbers);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("25");
        arrayList.add("33");
        arrayList.add("");

        System.out.println(arrayList.size());
        arrayList.removeIf(str -> str.length() == 0); // Lambda expression, which is shorthand for a Predicate<T> function
        System.out.println(arrayList.size());
        arrayList.forEach(x -> System.out.println(x + " This is a consumer function"));

        Func<String, String, String> combineStrings = (str, str2) -> str + str2; // This could alternatively have been written as an argument directly (instead of defining combineString variable)
        Action<Integer, Integer> addNumbers = (num1, num2) -> System.out.println(num1 + num2); // This could alternatively have been written as an argument directly

        ExampleClassUsingFunctionalInterfaces funcInterfaceExample = new ExampleClassUsingFunctionalInterfaces(combineStrings, addNumbers);
        String outcome = funcInterfaceExample.doTheThingDefinedByTheFunc("Hello", "World");
        System.out.println("outcome: " + outcome);
        funcInterfaceExample.noReturnTypeFunction(2, 2); // does the thing as specifed by the passed in Action<Integer, Integer> (addNumbers)
    }

    // Below code can maybe be used as an example of usage?

}

// Me trying to do something similar to C# Func<T, T2, T3>
@FunctionalInterface
interface Func<T, T2, T3> {
    T perform(T2 t2, T3 t3);
}

@FunctionalInterface
interface Action<T, T2> {
    void perform(T t, T2 t2);
}

class ExampleClassUsingFunctionalInterfaces {

    private final Func<String, String, String> functionToPerform;
    private final Action<Integer, Integer> actionToPerform;

    public ExampleClassUsingFunctionalInterfaces(Func<String, String, String> flexibleFunctionPassing, Action<Integer, Integer> actionToPerform) {
        this.functionToPerform = flexibleFunctionPassing;
        this.actionToPerform = actionToPerform;
    }

    public void noReturnTypeFunction(Integer num1, Integer num2) {
        actionToPerform.perform(num1, num2);
    }

    public String doTheThingDefinedByTheFunc(String stringOne, String stringTwo) {
        return functionToPerform.perform(stringOne, stringTwo);
    }
}