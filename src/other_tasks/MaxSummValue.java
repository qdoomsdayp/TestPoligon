package other_tasks;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class MaxSummValue {
    private int arr[];

    public MaxSummValue() {
    }

    public void maxSummValue(int value) {
        int firstEl = 0, secondEl = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] + arr[j] == value) {
                    firstEl = arr[i];
                    secondEl = arr[j];
                    System.out.println(firstEl + " + " + secondEl + " = " + value);
                }
            }
        }

    }

    public void maxSummValueSet(int value) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> setEl = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        for (Integer el : set) {
            int raz = value - el;
            if (set.contains(raz) && !setEl.contains(el)) {
                System.out.println(el + " + " + raz + "=" + value);
                setEl.add(el);
                setEl.add(raz);
            }
        }
    }

    public void exampleSummValue(int n) {
        long start = 0, finish = 0;
        Random r = new Random(n);
        HashSet<Integer> set = new HashSet<>();

        while (set.size() < n){
            int qwe = r.nextInt(n);
            set.add(qwe);
        }
        this.arr = new int[n];
        Object o[] = set.toArray();

        for (int i = 0; i < o.length; i++) {
            this.arr[i] =(int) o[i];
        }
        start = System.currentTimeMillis();
        maxSummValue(n);
        finish = System.currentTimeMillis();
        long time2For = finish - start;
        System.out.println("--------------------------------------------");
        start = System.currentTimeMillis();
        maxSummValueSet(n);
        finish = System.currentTimeMillis();
        long timeSet = finish - start;

        System.out.println("Time 2 for: " + time2For);
        System.out.println("Time set: " + timeSet);


    }

}
