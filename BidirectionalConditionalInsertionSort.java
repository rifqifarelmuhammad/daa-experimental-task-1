import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Bidirectional Conditional Insertion Sort menggunakan zero-based indexing
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
        out.println("Waktu eksekusi: " + executionTime + " ms");
        // out.println("Memory eksekusi: " + executionMemory);
        out.println("Data yang telah terurut menggunakan Bidirectional Conditional Insertion Sort:");
        
        for (int i = 0; i < lotsOfData; i++) {
            out.println(A[i]);
        }

        out.close();
    }

    public static void bidirectionalConditionalInsertionSort() {
        int sortedLeft = 0;
        int sortedRight = A.length - 1;

        while (sortedLeft < sortedRight) {
            swap(sortedRight, sortedLeft + (int)(sortedRight - sortedLeft)/2);
            
            if (A[sortedLeft] == A[sortedRight]) {
                if (isEqual(sortedLeft, sortedRight) == -1) return;
            }

            if (A[sortedLeft] > A[sortedRight]) swap(sortedLeft, sortedRight);

            if (sortedRight - sortedLeft >= 100) {
                for (int i = sortedLeft + 1; i < (int)(Math.sqrt(sortedRight - sortedLeft) + 1); i++) {
                    if (A[sortedRight] < A[i]) swap(sortedRight, i); 
                    else if (A[sortedLeft] > A[i]) swap(sortedLeft, i);
                }
            }

            int idx = sortedLeft + 1;
            int leftElement = A[sortedLeft];
            int rightElement = A[sortedRight];
            while (idx < sortedRight) {
                int currentElement = A[idx];
                if (currentElement <= leftElement) {
                    A[idx] = A[sortedLeft + 1];
                    insertionSortLeft(currentElement, sortedLeft, 0);
                    sortedLeft++;
                    idx++;
                } else if (currentElement >= rightElement) {
                    A[idx] = A[sortedRight - 1];
                    insertionSortRight(currentElement, sortedRight, A.length - 1);
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

    public static int isEqual(int sortedLeft, int sortedRight) {
        for (int k = sortedLeft + 1; k < sortedRight; k++) {
            if (A[k] != A[sortedLeft]) {
                swap(k, sortedLeft);
                return k;
            }
        }
        return -1;
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
