package pr.lib.algo.string;

public class ManachersSearch {

    static void findLongestPalindromicString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append("#").append(s.charAt(i));
        }
        printManacher(sb.toString() + "#", s);
    }

    static void printManacher(String s, String s1) {

        int n = s.length();
        s = "$" + s + "^";
        int[] p = new int[n+2];
        int l = 1, r = 1;
        int start = 0, maxLen = 0;

        for (int i = 1; i <= n; i++) {
            p[i] = Math.max(0, Math.min(r - i, p[r - i + l]));

            while (s.charAt(i - p[i]) == s.charAt(i + p[i])) {
                p[i]++;
            }

            if (i + p[i] > r) {
                r = i + p[i];
                l = i - p[i];
            }

            if (p[i] > maxLen) {
                maxLen = p[i];
                start = (i - p[i] - 1) / 2;
            }
        }

        System.out.println("i: " + l + ", r: " +  r);
        System.out.println(s1.substring(start, start + maxLen));
    }

    public static void main(String[] args) {
        String text = "babcbabcbaccba";
        findLongestPalindromicString(text);
    }
}
