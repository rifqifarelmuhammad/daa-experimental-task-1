import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Counting Sort menggunakan zero-based indexing
public class CountingSort {
    static InputReader in;
    static PrintWriter out;
    static int[] A;
    static int[] B;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int lotsOfData = in.nextInt();
        A = new int[lotsOfData];
        B = new int[lotsOfData];
        for (int i = 0; i < lotsOfData; i++) {
            A[i] = in.nextInt();
        }
        
        long startTime = System.currentTimeMillis();
        // long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        int maxValue = findMaxValue();
        countingSort(maxValue);
        // long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long executionTime = System.currentTimeMillis() - startTime;
        // double executionMemory = startMemory - endMemory;

        out.println("Jumlah data: " + lotsOfData);
        out.println("Waktu eksekusi: " + executionTime + " ms");
        // out.println("Memory eksekusi: " + executionMemory);
        out.println("Data yang telah terurut menggunakan Counting Sort:");

        for (int i = 0; i < lotsOfData; i++) {
            out.println(B[i]);
        }

        out.close();
    }

    static int findMaxValue() {
        int maxValue = A[0];

        for (int i = 1; i < A.length; i++) {
            if (A[i] > maxValue) {
                maxValue = A[i];
            }
        }

        return maxValue;
    }

    static void countingSort(int maxValue) {
        int[] C = new int[maxValue + 1];

        for (int i = 0; i < A.length; i++) {
            C[A[i]] = C[A[i]] + 1;
        }

        for (int i = 1; i < C.length; i++) {
            C[i] = C[i] + C[i-1];
        }

        for (int i = A.length - 1; i >= 0; i--) {
            B[C[A[i]] - 1] = A[i];
            C[A[i]] = C[A[i]] - 1;
        }
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
