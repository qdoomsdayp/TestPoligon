import java.util.Collections;

public class Pyramid {
    private int maxLenghtPyramid;
    private long timePyramid7, timePyramid8, timePyramid8Invert;

    public Pyramid() {
    }

    public int getMaxLenghtPyramid() {
        return maxLenghtPyramid;
    }

    public void setMaxLenghtPyramid(int maxLenghtPyramid) {
        this.maxLenghtPyramid = maxLenghtPyramid;
    }

    public void examplePyramid(int n) {
        long start = 0, finish = 0;
        setMaxLenghtPyramid(n);


        start = System.currentTimeMillis();
        pyramid7(getMaxLenghtPyramid());
        finish = System.currentTimeMillis();
        long timePyramid7 = finish - start;

        start = System.currentTimeMillis();
        pyramid8(getMaxLenghtPyramid());
        finish = System.currentTimeMillis();
        long timePyramid8 = finish - start;

        start = System.currentTimeMillis();
        pyramid8Invert(getMaxLenghtPyramid());
        finish = System.currentTimeMillis();
        long timePyramid8Invert = finish - start;

        System.out.println("Time pyramid7: " + timePyramid7);
        System.out.println("Time pyramid8: " + timePyramid8);
        System.out.println("Time pyramid8Invert: " + timePyramid8Invert);
    }

    //java 7
    public void pyramid7(int n) {
        int midl = n / 2;
        int count = 1;
        while (midl >= 0) {
            for (int i = 0; i < midl; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < count; i++) {
                System.out.print("*");
            }
            System.out.println();
            midl--;
            count += 2;
        }
    }

    //java 8
    public void pyramid8(int n) {
        for (int i = 0; i < n / 2 + 1; i++) {
            System.out.println(String.join("", Collections.nCopies(n / 2 - i, " "))
                    + String.join("", Collections.nCopies(2 * i + 1, "*")));
        }
    }

    //java 8 Invert Pyramid
    public void pyramid8Invert(int n) {
        for (int i = n / 2; i >= 0; i--) {
            System.out.println(String.join("", Collections.nCopies(n / 2 - i, " "))
                    + String.join("", Collections.nCopies(2 * i + 1, "*")));
        }
    }


}
