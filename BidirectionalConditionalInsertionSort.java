import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BidirectionalConditionalInsertionSort {
    static InputReader in;
    static PrintWriter out;
    static int[] A;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int lotsOfData = in.nextInt();
        A = new int[lotsOfData];
        for (int i = 0; i < lotsOfData; i++) {
            A[i] = in.nextInt();
        }

        long startTime = System.currentTimeMillis();
        // long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        bidirectionalConditionalInsertionSort();
        // long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long executionTime = System.currentTimeMillis() - startTime;
        // double executionMemory = startMemory - endMemory;

        out.println("Jumlah data: " + lotsOfData);
        out.println("Waktu eksekusi: " + executionTime);
        // out.println("Memory eksekusi: " + executionMemory);
        out.println("Data yang telah terurut menggunakan Bidirectional Conditional Insertion Sort:");
        
        for (int i = 0; i < lotsOfData; i++) {
            out.println(A[i]);
        }

        out.close();
    }

    public static void bidirectionalConditionalInsertionSort() {
        int left = 0;
        int right = A.length - 1;
        int sortedLeft = left;
        int sortedRight = right;

        while (sortedLeft < sortedRight) {
            if (A[sortedLeft] > A[sortedRight]) swap(sortedLeft, sortedRight);

            int idx = sortedLeft + 1;
            while (idx < sortedRight) {
                int currentElement = A[idx];
                if (currentElement <= A[sortedLeft]) {
                    A[idx] = A[sortedLeft + 1];
                    insertionSortLeft(currentElement, sortedLeft, left);
                    sortedLeft++;
                    idx++;
                } else if (currentElement >= A[sortedRight]) {
                    A[idx] = A[sortedRight - 1];
                    insertionSortRight(currentElement, sortedRight, right);
                    sortedRight--;
                } else idx++;
            }

            sortedLeft++;
            sortedRight--;
        }
    }

    public static void insertionSortLeft(int currentElement, int j, int left) {
        while (j >= left && currentElement < A[j]) {
            A[j + 1] = A[j];
            j--;
        }
        A[j + 1] = currentElement;
    }

    public static void insertionSortRight(int currentElement, int j, int right) {
        while (j <= right && currentElement > A[j]) {
            A[j - 1] = A[j];
            j++;
        }
        A[j - 1] = currentElement;
    }

    public static void swap(int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // taken from https://codeforces.com/submissions/Petr
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public char nextChar() {
            return next().charAt(0);
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
