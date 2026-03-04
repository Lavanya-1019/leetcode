class Solution {
    public boolean halvesAreAlike(String s) {
        int n = s.length();
        int count1 = 0;
        int count2 = 0;
        
        for (int i = 0; i < n / 2; i++) {
            if (isVowel(s.charAt(i))) {
                count1++;
            }
        }
        
        for (int i = n / 2; i < n; i++) {
            if (isVowel(s.charAt(i))) {
                count2++;
            }
        }
        
        return count1 == count2;
    }
    
    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
               ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }
}