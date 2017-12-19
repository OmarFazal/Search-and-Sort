package codes;

import java.util.Scanner;

public class SearchAndSort {
	private static final int SIZE = 100;
	private static int[] manta = new int[SIZE];

	private static void swap(int x, int y) {
		manta[x] += manta[y];
		manta[y] = manta[x] - manta[y];
		manta[x] -= manta[y];
	}

	private static void shift(int array[], int shiftFrom) {
		for (int x = array.length - 1; x > shiftFrom; x--)
			array[x] = array[x - 1];
	}

	private static int rng(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

	public static void populateRandomly() {
		for (int x = 0; x < SIZE; x++)
			manta[x] = rng(1, SIZE);
	}

	public static void populateSequentially() {
		for (int x = 0; x < SIZE; x++)
			manta[x] = x + 1;
	}

	public static void display() {
		for (int x = 0; x < SIZE; x++) {
			System.out.print(manta[x] + "	");
			if ((x + 1) % 10 == 0) {
				System.out.println();
			}
		}
	}

	public static boolean checkSort() {
		for (int x = 0; x < SIZE - 1; x++) {
			if (manta[x] > manta[x + 1]) {
				System.out.println("This list is not sorted");
				return false;
			}
		}
		System.out.println("This list is sorted");
		return true;
	}

	private static void Shuffle() {
		int rand;
		for (int x = 0; x < SIZE; x++) {
			rand = rng(0, SIZE - 1);
			if (rand != x) {
				swap(x, rand);
			}
		}
	}

	private static void linearSearch(int number) {
		for (int x = 0; x < SIZE; x++) {
			if (number == manta[x])
				System.out.println("your number is in position:" + (x + 1));
		}
	}

	private static void bubbleSort() {
		for (int x = 0; x < SIZE - 1; x++) {
			for (int y = x + 1; y < SIZE; y++)
				if (manta[y] < manta[x]) {
					swap(x, y);
				}
		}
	}

	private static void insertionSort() {
		int y = 1;
		boolean broke;
		int[] temp = new int[SIZE];
		temp[0] = manta[0];
		for (int x = 0; x < SIZE; x++) {
			broke = false;
			for (int i = 0; i < SIZE - 1; i++) {
				if (manta[x] < temp[i]) {
					shift(temp, i);
					temp[i] = manta[x];
					broke = true;
					break;
				} else if (manta[x] < temp[i + 1]) {
					shift(temp, i + 1);
					temp[i + 1] = manta[x];
					broke = true;
					break;
				}
			}
			if (!broke)
				temp[x] = manta[x];
		}
		manta = temp;
	}

	private static void quickSort(int max, int min) {
		int pivot = max;
		int leftSlider = min;
		int rightSlider = max;

		while (leftSlider != rightSlider) {
			while (manta[leftSlider] < manta[pivot] && leftSlider < pivot)
				leftSlider++;
			while (manta[rightSlider] > manta[pivot] && rightSlider > pivot)
				rightSlider--;
			if (leftSlider != rightSlider) {
				manta[leftSlider] += manta[rightSlider];
				manta[rightSlider] = manta[leftSlider] - manta[rightSlider];
				manta[leftSlider] -= manta[rightSlider];

				if (rightSlider == pivot)
					pivot = leftSlider;
				else if (leftSlider == pivot)
					pivot = rightSlider;
			}
		}
		if (rightSlider < max - 1)
			quickSort(max, rightSlider + 1);
		if (leftSlider > min + 1)
			quickSort(rightSlider - 1, min);
	}

	private static void binarySearch(int min, int max, int target) {
		int pivot = (min + max) / 2;
		if (pivot == target) {
			System.out.println("Your number is in position: " + pivot);
			return;
		}
		while (target != pivot) {
			if (target < pivot) {
				max = pivot;
				pivot = (min + max) / 2;
			}
			else if (target > pivot) {
				min = pivot;
				pivot = (min + max) / 2;
			}
		}
		System.out.println("Your number is in position: " + pivot);
	}
	private static void selectionSort(){
		int lowest = 0;
		for(int x = 0; x < SIZE; x++){
			if(manta[x] < lowest)
				lowest = manta[x];
		}
			
	}
	
	private static int digitAt(int i, int digit){
		int x = (int)Math.pow(10,digit);
		i /= x;
		return i % 10;
	}
	private static void radixSort(){
		int[] temp = new int [SIZE];
		int count = 0;
		int x = SIZE;
		int length = 0;
		while(x > 0){
			x /= 10;
			length++;
		}
		int times = 0;
		while(times < length ){
			count = 0;
			for(int i = 0; i < 10; i++){
				for(int j = 0; j < SIZE; j++){
					if(digitAt(manta[j], times) == i){
						temp[count] = manta[j];
						count++;
					}
				}
			}
			times++;
			for(int i = 0; i < SIZE; i++){
				manta[i] = temp[i];
			}
		}
	}

	private static void menuOptions() {
		System.out.println("1. Populate Randomly");
		System.out.println("2. Populate Sequentially");
		System.out.println("3. Display the Array");
		System.out.println("4. Check if the list is sorted");
		System.out.println("5. Shuffle Array");
		System.out.println("6. Linear Search");
		System.out.println("7. Bubble Sort");
		System.out.println("8. Insertion Sort");
		System.out.println("9. Quick Sort");
		System.out.println("10. Binary Search");
		System.out.println("11. Selection Sort");
		System.out.println("12. Radix Sort");
		System.out.println("0. Leave menu");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int option = 0;
		do {
			menuOptions();
			option = scan.nextInt();
			if (option == 1)
				populateRandomly();
			else if (option == 2)
				populateSequentially();
			else if (option == 3)
				display();
			else if (option == 4)
				checkSort();
			else if (option == 5)
				Shuffle();
			else if (option == 6) {
				System.out.println("Insert the nubmer you would like to find.");
				int number = scan.nextInt();
				linearSearch(number);
			} else if (option == 7)
				bubbleSort();
			else if (option == 8)
				insertionSort();
			else if (option == 9)
				quickSort(SIZE - 1, 0);
			else if (option == 10) {
				System.out.println("What number are you looking for?");
				int target = scan.nextInt();
				binarySearch(0, SIZE - 1, target);
			}
			else if (option == 11)
				selectionSort();
			else if (option == 12)
				radixSort();
		} while (option != 0);

	}
}
