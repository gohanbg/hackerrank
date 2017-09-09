package algorithms.help.structures.string;

import java.util.Arrays;

/**
 * Created by mihail on 9/9/2017.
 */
public class Suffix {

    private static SuffixArray[] createSortedSuffixArray(String string) {
        SuffixArray[] result = new SuffixArray[string.length()];
        for(int i = 0; i<result.length; i++) {
            result[i] = new SuffixArray(string, i);
        }
        Arrays.sort(result);
        return result;
    }

    private static class SuffixArray implements Comparable<SuffixArray> {
        private final String text;
        private final int index;

        private SuffixArray(String text, int index) {
            this.text = text;
            this.index = index;
        }
        private int length() {
            return text.length() - index;
        }
        private char charAt(int i) {
            return text.charAt(index + i);
        }

        public int compareTo(SuffixArray that) {
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

    private static int[] createLcpArray(SuffixArray[] bla) {
        int[] result = new int[bla.length];
        for(int i = 1; i<bla.length; i++) {
            result[i] = lcpSuffix(bla[i], bla[i-1]);
        }
        return result;
    }

    private static int lcpSuffix(SuffixArray s, SuffixArray t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) return i;
        }
        return n;
    }
}
