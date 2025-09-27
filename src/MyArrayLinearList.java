

import dataStructures.ArrayLinearList;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.*;

public class MyArrayLinearList extends ArrayLinearList {

    public MyArrayLinearList() {
    }

    public Object max() {
        Object max = new Object();

        max = this.get(0);

        for (int i = 0; i < this.size(); i++) {
            if ((int) max < (Integer) this.get(i)) {
                max = this.get(i);
            }
        }
        return max;
    }

    public Object min() {
        Object min = new Object();
        min = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if ((int) min > (Integer) this.get(i)) {
                min = this.get(i);
            }
        }
        return min;
    }

    public Object sum() {
        int sum = 0;

        for (int i = 0; i < this.size(); i++) {
            sum += (int) this.get(i);
        }
        return sum;
    }

    public Object average() {

        int sum = 0;

        for (int i = 0; i < this.size(); i++) {
            sum += (int) this.get(i);
        }

        float num = (float) sum / (float) this.size();
        return num;

    }

    public Object removeOdd() {
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {
            if ((Integer) this.get(i) % 2 == 0) {
                numbers.add((Integer) this.get(i));
            }
        }

        return numbers;
    }

    public ArrayList<Integer> sort() {
        ArrayList<Integer> sorted = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {
            sorted.add((int) this.get(i));
        }

        for (int i = 0; i < sorted.size(); i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                if (sorted.get(j) > sorted.get(j + 1)) {
                    int temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        return sorted;
    }


    public static void main(String arg[]) {

        Scanner sc = new Scanner(System.in);
        MyArrayLinearList x = new MyArrayLinearList();

        System.out.println("Massived heden too orulah ve: ");
        int non = sc.nextInt();

        for (int i = 0; i < non; i++) {
            System.out.println("Enter your " + (i + 1) + ": ");
            int numbers = sc.nextInt();
            x.add(i, numbers);
        }

        System.out.println("Max " + (int) x.max());
        System.out.println("Min " + (int) x.min());
        System.out.println("Sum " + (int) x.sum());
        System.out.println("Averege " + (float) x.average());
        System.out.println("Removed odd numbers.");

        ArrayList<Integer> even = (ArrayList<Integer>) x.removeOdd();
        for (int a : even) {
            System.out.print(a + " ");
        }
        System.out.println();
        System.out.println("Sorted.");

        ArrayList<Integer> sorted = (ArrayList<Integer>) x.sort();
        for (int n : sorted) {
            System.out.print(n + " ");
        }
    }
}