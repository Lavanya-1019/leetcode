import java.util.*;

class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) return result;

        int[] countP = new int[26];
        int[] window = new int[26];

        // Count characters in p
        for (char c : p.toCharArray()) {
            countP[c - 'a']++;
        }

        int windowSize = p.length();

        for (int i = 0; i < s.length(); i++) {
            // Add current char to window
            window[s.charAt(i) - 'a']++;

            // Remove left char if window exceeds size
            if (i >= windowSize) {
                window[s.charAt(i - windowSize) - 'a']--;
            }

            // Compare arrays
            if (Arrays.equals(countP, window)) {
                result.add(i - windowSize + 1);
            }
        }

        return result;
    }
}