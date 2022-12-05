import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Analysis {
    public static void main(String[] args) {
        System.out.println("PROBLEM 1 (reverse 1k)\n----------------------");
        sortArray("files/1 - Reverse Order (1,000).txt", 1000);

        System.out.println("\nPROBLEM 2 (sorted 1k)\n---------------------");
        sortArray("files/2 - Already Sorted (1,000).txt", 1000);

        System.out.println("\nPROBLEM 3 (almost sorted 1k)\n----------------------------");
        sortArray("files/3 - Almost Sorted (1,000).txt", 1000);

        System.out.println("\nPROBLEM 4 (reverse 10k)\n-----------------------");
        sortArray("files/4 - Reverse Order (10,000).txt", 10000);

        System.out.println("\nPROBLEM 5 (random 10k)\n----------------------");
        sortArray("files/5 - Random Numbers (10,000).txt", 10000);
    }

    public static void sortArray(String filename, int size) {
        int[] nums = readFile(filename, size); //loads the file for whichever problem is currently being done
        int numOfBubbleInsert = 125;  //how many times bubble/insertion will run and average the times of
        int numOfMerge = 1000;  //how many times merge sort will run and average the times of
        int timeDivision = 1000000; //1000000 for nano -> milli. 1000000000 for nano -> seconds.

        double bubbleTime = 0;
        for (int i = 0; i < numOfBubbleInsert; ++i) {
            int[] toSort = nums.clone();
            double bubbleStart = System.nanoTime();
            Sorts.bubbleSort(toSort);
            double bubbleEnd = System.nanoTime();
            bubbleTime += (bubbleEnd - bubbleStart);
        }
        bubbleTime = (bubbleTime / numOfBubbleInsert) / timeDivision;

        double insertionTime = 0;
        for (int i = 0; i < numOfBubbleInsert; ++i) {
            int[] toSort = nums.clone();
            double insertionStart = System.nanoTime();
            Sorts.insertionSort(toSort);
            double insertionEnd = System.nanoTime();
            insertionTime += (insertionEnd - insertionStart);
        }
        insertionTime = (insertionTime / numOfBubbleInsert) / timeDivision;

        double mergeTime = 0;
        for (int i = 0; i < numOfMerge; ++i) {
            int[] toSort = nums.clone();
            double mergeStart = System.nanoTime();
            Sorts.mergeSort(toSort, 0, toSort.length - 1);
            double mergeEnd = System.nanoTime();
            mergeTime += (mergeEnd - mergeStart);
        }
        mergeTime = (mergeTime / numOfMerge) / timeDivision;

        System.out.printf("Bubble Sort took %fms on average to sort list of size %d.\n", bubbleTime, size);
        System.out.printf("Insertion Sort took %fms on average to sort list of size %d.\n", insertionTime, size);
        System.out.printf("Merge Sort took %fms on average to sort list of size %d.\n", mergeTime, size);
    }

    public static int[] readFile(String filename, int size) {
        int[] nums = new int[size];
        try {
            Scanner file = new Scanner(new File(filename));
            for (int i = 0; i < nums.length; ++i) {
                nums[i] = Integer.parseInt(file.next());
            }
        } catch (FileNotFoundException ignored) {
            System.out.println("\"" + filename + "\" not found! Make sure it's in the right directory and restart.");
            System.exit(0);
        }
        return nums;
    }
}