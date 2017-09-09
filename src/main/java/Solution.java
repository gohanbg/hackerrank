import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        System.out.println(Arrays.toString(createSortedSuffixArray("kurkuraaa")));
        System.out.println(Arrays.toString(createLcpArray(createSortedSuffixArray("kurkuraaa"))));

    }

    private static Suffix[] createSortedSuffixArray(String string) {
        Suffix[] result = new Suffix[string.length()];
        for(int i = 0; i<result.length; i++) {
            result[i] = new Suffix(string, i);
        }
        Arrays.sort(result);
        return result;
    }

    private static class Suffix implements Comparable<Suffix> {
        private final String text;
        private final int index;

        private Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }
        private int length() {
            return text.length() - index;
        }
        private char charAt(int i) {
            return text.charAt(index + i);
        }

        public int compareTo(Suffix that) {
            if (this == that) return 0;  // optimization
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return this.length() - that.length();
        }

        public String toString() {
            return text.substring(index);
        }
    }

    private static int lcpSuffix(Suffix s, Suffix t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) return i;
        }
        return n;
    }

    private static int[] createLcpArray(Suffix[] bla) {
        int[] result = new int[bla.length];
        for(int i = 1; i<bla.length; i++) {
            result[i] = lcpSuffix(bla[i], bla[i-1]);
        }
        return result;
    }

}