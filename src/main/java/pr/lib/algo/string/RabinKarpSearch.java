package pr.lib.algo.string;

public class RabinKarpSearch {
    int q = 101;  // prime
    int d = 256;  // number of letters in alphabet

    void rabinKarpSearch(String txt, String pat) {
        int n = txt.length();
        int m = pat.length();

        int h = (int)pow(d, m-1, q);

        int t = 0, p = 0;
        for (int i = 0; i < m; i++) {
            t = (t * d + txt.charAt(i)) % q;
            p = (p * d + pat.charAt(i)) % q;
        }

        for (int i = 0; i <= n-m; i++) {
            if (t == p) {
                // check individual character
                int j = 0;
                while (j < m && txt.charAt(i + j) == pat.charAt(j)) {
                    j++;
                }

                if (j == m) {
                    System.out.println("Found at index " + i);
                }
            }

            if (i < n - m) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;
                if (t < 0) t += q;
            }
        }
    }

    public static void main(String[] args) {
        String txt = "GEEKS FOR GEEKS";
        String pat = "GEEK";
        RabinKarpSearch rabinKarpSearch = new RabinKarpSearch();
        rabinKarpSearch.rabinKarpSearch(txt, pat);

    }

    long pow(long a, long b, long m) {
        a %= m;
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = res * a % m;
            }
            a = a * a % m;
            b >>= 1;
        }

        return res;
    }
}
