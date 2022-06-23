package Topics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaStreamApi implements Runnable {

    private final List<Integer> numbers;

    public JavaStreamApi() {
        numbers = new ArrayList<>();
        for (int i = 65; i <= 100; i += 5) {
            numbers.add(i);
        }
        numbers.add(100);
        for (int i = 0; i < 56; i += 5) {
            numbers.add(i);
        }
    }

    @Override
    public void run() {
        System.out.println("Original numbers array: " + numbers);
        sortExample();

        filterExample();
    }

    private void filterExample() {
        // Filters the numbers, so only 0 & 100 are left.
        List<Integer> filteredNumbers = numbers.stream().filter((number) -> number.equals(0) || number.equals(100)).collect(Collectors.toList());
        System.out.println("filteredNumbers: "+filteredNumbers);
    }

    private void sortExample() {
        List<Integer> sortedNumbers = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println("sortedNumbers: " + sortedNumbers);
    }
}
