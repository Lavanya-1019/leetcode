import java.util.Arrays;

public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];

        for (char task : tasks) {
            freq[task - 'A']++;
        }

        Arrays.sort(freq);
        int maxFreq = freq[25];
        int idleTime = (maxFreq - 1) * n;

        for (int i = 24; i >= 0 && idleTime > 0; i--) {
            idleTime -= Math.min(freq[i], maxFreq - 1);
        }

        idleTime = Math.max(0, idleTime);
        return tasks.length + idleTime;
    }
}