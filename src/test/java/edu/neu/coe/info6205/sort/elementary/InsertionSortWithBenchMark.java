package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.*;
import edu.neu.coe.info6205.util.Timer;
import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author Achyuth Varma Penmetcha
 */

public class InsertionSortWithBenchMark {

    @Test
    public void random() {
        int size = 1000;
        final List<Integer> list = new ArrayList<Integer>(Arrays.asList(getRandomArrayNumbers(size)));
        Integer xs[] = list.toArray(new Integer[0]);
        final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("Insertion Sort with Benchmark", list.size(), config);
//        Integer[] xs = helper.random(Integer.class, r -> r.nextInt(1000));
        helper.init(list.size());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
        sorter.preProcess(xs);
        Integer[] sortedTest = sorter.sort(xs);
        final Timer timer = new Timer();
        final double mean = timer.repeat(10, () -> xs, (t) -> {
            sorter.sort(t);
            return null;
        });
        System.out.println("Random: "+mean);
        assertTrue(helper.sorted(sortedTest));
        sorter.postProcess(sortedTest);
    }

    @Test
    public void partiallyOrdered(){
        int size = 1000;
        final List<Integer> list = new ArrayList<Integer>(Arrays.asList(getPartiallyOrderedArray(size, 1000)));
        Integer xs[] = list.toArray(new Integer[0]);
        final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("Insertion Sort with Benchmark", list.size(), config);
        helper.init(list.size());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
        sorter.preProcess(xs);
        Integer[] sortedTest = sorter.sort(xs);
        final Timer timer = new Timer();
        final double mean = timer.repeat(10, () -> xs, (t) -> {
            sorter.sort(t);
            return null;
        });
        System.out.println("Partially Ordered: "+mean);
        assertTrue(helper.sorted(sortedTest));
        sorter.postProcess(sortedTest);
    }

    @Test
    public void reversed(){
        int size = 1000;
        final List<Integer> list = new ArrayList<Integer>(Arrays.asList(getArrayOfNumbersInReverseOrder(size)));
        Integer xs[] = list.toArray(new Integer[0]);
        final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("Insertion Sort with Benchmark", list.size(), config);
        helper.init(list.size());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
        sorter.preProcess(xs);
        Integer[] sortedTest = sorter.sort(xs);
        final Timer timer = new Timer();
        final double mean = timer.repeat(10, () -> xs, (t) -> {
            sorter.sort(t);
            return null;
        });
        System.out.println("Reversed: "+mean);
        assertTrue(helper.sorted(sortedTest));
        sorter.postProcess(sortedTest);
    }
    @Test
    public void ordered(){
        int size = 1000;
        final List<Integer> list = new ArrayList<Integer>(Arrays.asList(getArrayOfNumbersInOrder(size)));
        Integer xs[] = list.toArray(new Integer[0]);
        System.out.println("Size of the list: "+list.size());
        final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("Insertion Sort with Benchmark", list.size(), config);
        helper.init(list.size());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        SortWithHelper<Integer> sorter = new InsertionSort<>(helper);
        sorter.preProcess(xs);
        Integer[] sortedTest = sorter.sort(xs);
        final Timer timer = new Timer();
        final double mean = timer.repeat(10, () -> xs, (t) -> {
            sorter.sort(t);
            return null;
        });
        System.out.println("Ordered: "+mean);
        assertTrue(helper.sorted(sortedTest));
        sorter.postProcess(sortedTest);
    }


    /**
     * Generate Array with random numbers with range upto 1000
     * @param size:  Give Size of array
     * @return Returns
     */
    public Integer[] getRandomArrayNumbers(int size){

        Random random = new Random();
        Integer[] theArray = new Integer[size];

        for(int i = 0; i < size; i++){
            theArray[i] = random.nextInt(1000);
        }

        return theArray;
    }

    /**
     * Get arrays in sorted order
     * @param size size of array
     * @return sorted array
     */
    public Integer[] getArrayOfNumbersInOrder(int size){

        Integer[] arrayList = getRandomArrayNumbers(size);

        Arrays.sort(arrayList);

        return arrayList;
    }

    /**
     * Get Array of Numbers in Reverse Order
     * @param size of the array
     * @return Array with the given size in reverse order
     */
    public Integer[] getArrayOfNumbersInReverseOrder(int size){
        Integer[] arrayList = getArrayOfNumbersInOrder(size);

        Integer[] reverseList = new Integer[size];

        int temp = size;
        for(int i = 0; i < size; i++){
            reverseList[temp - 1] = arrayList[i];
            temp -= 1;
        }

        return reverseList;
    }

    /**
     * Used to get the Random Array Numbers by giving range
     * @param size Size of array
     * @param minimum minimum value of range
     * @param maximum maximum value of range
     * @return Integer array with the given size
     */
    public Integer[] getRandomArrayNumbersWithRange(int size, int minimum, int maximum){

        Integer[] theArray = new Integer[size];

        for(int i = 0; i < size; i++){
            theArray[i] = getRandomNumberInRange(minimum, maximum);
        }

        return theArray;
    }

    /**
     * Used to get random value of given range
     * @param minimum Give minimum range
     * @param maximum Give maximum range
     * @return Get Integer from given range
     */
    public Integer getRandomNumberInRange(int minimum, int maximum){
        Random random = new Random();
        return random.nextInt(maximum - minimum) + minimum;
    }

    /**
     * Generate Partially ordered array (First half is ordered and second half is random)
     * @param size Give the size of array
     * @return Partially Sorted Array
     */

    public Integer[] getPartiallyOrderedArray(int size, int maxBound){

        Integer[] orderedArray = getArrayOfNumbersInOrder(size/2);

        Integer largestElement = orderedArray[(size/2) - 1];

        Integer[] randomArray = getRandomArrayNumbersWithRange(size/2, largestElement, maxBound);

        int orderedArrayLength = orderedArray.length;
        int randomArrayLength = randomArray.length;

        Integer[] partiallyOrdered = new Integer[orderedArrayLength+ randomArrayLength];

        for(int i = 0; i < orderedArrayLength; i++){
            partiallyOrdered[i] = orderedArray[i];
        }

        for(int i = 0; i < randomArrayLength; i++){
            partiallyOrdered[orderedArrayLength + i] = randomArray[i];
        }

        return partiallyOrdered;

    }

//    @Test
//    public void testRandomArray(){
//        Integer[] randomArray = getPartiallyOrderedArray(20, 1000);
////        Integer[] randomArray = getArrayOfNumbersInReverseOrder(20);
//
//        for(int i = 0; i < randomArray.length; i++){
//            System.out.println(randomArray[i]);
//        }
//    }

}
