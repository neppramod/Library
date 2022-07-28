package pr.lib.algo.string;

public class KMPPatternSearch {
    void buildLPS(int[] lps, String p) {
        int m = p.length();
        int len = 0, i = 1;
        lps[0] = 0;

        while (i < m) {
            // two cases, when characters of pattern match (suffix with prefix), starting from 0 to 1
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;   // increase length first
            } else {
                // when they don't match, check whether there was already a match or not
                // if there was a match, try to get a new value of len by looking at previous index (len-1) from lsp, if not just move to next character, with lps[i] = 0
                if (len > 0) {
                    len = lps[len-1];
                } else {
                    lps[i++] = 0;
                }
            }
        }
    }

    void kmpSearch(String t, String p) {
        int m = p.length();
        int n = t.length();
        int[] lps = new int[m];
        buildLPS(lps, p);

        int i = 0, j = 0;
        while (i < n) {
            if (t.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println("Found at: " + (i - j));
                j = lps[j-1];
            } else if (i < n && t.charAt(i) != p.charAt(j)) {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j-1];
                }
            }
        }
    }

    public static void main(String[] args) {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        KMPPatternSearch kmpPatternSearch = new KMPPatternSearch();
        kmpPatternSearch.kmpSearch(txt, pat);
    }
}
