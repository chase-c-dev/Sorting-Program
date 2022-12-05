public class Sorts {
    public static void main(String[] args) {

        int[] nums = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        //bubbleSort(nums);
        //selectionSort(nums);
        //insertionSort(nums);
        //mergeSort(nums, 0, nums.length - 1);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void bubbleSort(int[] nums) {
        int n = nums.length -1;
        boolean swapped = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n-i; j++) {
                if (nums[j] > nums[j+1]) {
                    swap(nums, j, j+1);
                    swapped = true;
                }
            }
            if (swapped = false) break;
        }

    }

    public static void insertionSort(int[] nums) {
        int n = nums.length;

        //Starting at i=1 because we assume the first value is already in place.
        for (int i = 1; i < n; ++i) {
            int current = nums[i];
            int j = i - 1; //the value to compare to is in the index immediately to the left of i.

            //Swap leftwards as long as it's less than whatever is to its left. Stop if reaching the left edge.
            while (j >= 0 && nums[j] > current) {
                nums[j + 1] = nums[j];
                --j;
            }

            nums[j + 1] = current;
        }
    }

    public static void selectionSort(int[] nums) {
       int n = nums.length;
       for (int i = 0; i < n-1; i++) {
           int min_index = i;
           for (int j = i+1; j < n; j++) {
               if (nums[j] < nums[min_index]) {
                   min_index = j;
               }
           }
           swap(nums, min_index, i);
       }
    }

    public static void mergeSort(int[] nums, int low, int high) {
        if(low < high) {
            int mid = (low + high) / 2; //calculating midpoint
            mergeSort(nums, low, mid); //calling mergeSort on the left half
            mergeSort(nums, mid+1, high); //calling mergeSort on the right half
            merge(nums, low, mid, high); //merging the 2 halves
        }
    }

    public static void merge(int[] nums, int low, int mid, int high) {
        int[] left = new int[mid-low+1];
        int[] right = new int[high-mid];


        for(int i = 0; i < left.length; ++i) {
            left[i] = nums[low+i];
        }

        for(int i = 0; i < right.length; ++i) {
            right[i] = nums[mid+1+i];
        }

        int leftPoint = 0, rightPoint = 0, numsPoint = low;

        //Main merging loop: this continues until leftPoint or rightPoint reach the end of left/right.
        while(leftPoint < left.length && rightPoint < right.length) {
            //If the left array has the next largest number, copy it into nums and increment pointers.
            if(left[leftPoint] < right[rightPoint]) {
                nums[numsPoint] = left[leftPoint];
                leftPoint++;
            //If the right array has the next largest number, copy it into nums and increment pointers.
            } else {
                nums[numsPoint] = right[rightPoint];
                rightPoint++;
            }
            numsPoint++;
        }


        while(leftPoint < left.length) {
            nums[numsPoint] = left[leftPoint];
            leftPoint++;
            numsPoint++;
        }

        while(rightPoint < right.length) {
            nums[numsPoint] = right[rightPoint];
            rightPoint++;
            numsPoint++;
        }
    }
}