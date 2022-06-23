package Topics;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        mapExample();
    }

    private void filterExample() {
        // Filters the numbers, so only 0 & 100 are left.
        List<Integer> filteredNumbers = numbers.stream().filter((number) -> number.equals(0) || number.equals(100)).collect(Collectors.toList());
        System.out.println("filteredNumbers: " + filteredNumbers);
    }

    private void sortExample() {
        List<Integer> sortedNumbers = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println("sortedNumbers: " + sortedNumbers);
    }

    private void mapExample() {
        Set<String> people = new HashSet<>();

        people.add("Reliable and team oriented, Bacchus can assist you in software development.");
        people.add("Hardworking, reliable and team oriented, Oriana is good at math and can assist in software development");
        people.add("Social and team oriented, Lilith can assist you in software development and help unite a team");
        people.add("Reliable and unrelenting, Erattius can assist you in software development and debugging");

        Map<String, String> peopleMapping = people.stream().collect(Collectors.toMap(x -> UUID.randomUUID().toString(), personDescription -> personDescription));
        peopleMapping.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
