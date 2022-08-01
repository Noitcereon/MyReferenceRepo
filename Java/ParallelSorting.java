package Topics;

import Helpers.ConsoleHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelSorting implements Runnable{
    @Override
    public void run() {
        // Parallel Sort utilizes multithreading to sort an array faster than normal.
        // It works by splitting an array into chunks and processing those chunks.
        ConsoleHelper.printHeadline("Parallel Sorting");

        Integer[] numbers = {1, 3, 5, 28, 82, 1, 520};

        List<Integer> numberList = new ArrayList<>();
        numberList.add(8820);
        numberList.add(3);
        numberList.add(3);
        numberList.add(28);
        numberList.add(82);
        numberList.add(1);
        numberList.add(12);

        convertedListExample(numberList);

        plainArrayExample(numbers);
    }

    /**
     * Takes an array and sorts it with parallelSort.
     * @param numbers Array of numbers
     */
    private void plainArrayExample(Integer[] numbers) {
        Arrays.stream(numbers).forEach(number -> System.out.print(number + " "));
        System.out.println();
        Arrays.parallelSort(numbers);
        Arrays.stream(numbers).forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    /**
     * Takes in a List object, converts it to an array and sorts it with parallelSort in ascending order.
     * @param numberList a collection of integer numbers
     */
    private void convertedListExample(List<Integer> numberList) {
        Integer[] arrayOfNumbers = numberList.toArray(new Integer[0]);
        Arrays.stream(arrayOfNumbers).forEach(number -> System.out.print(number + " "));
        System.out.println();
        Arrays.parallelSort(arrayOfNumbers);
        Arrays.stream(arrayOfNumbers).forEach(number -> System.out.print(number + " "));
        System.out.println();
    }
}
