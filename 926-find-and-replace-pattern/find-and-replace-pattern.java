import java.util.*;

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            if (matches(word, pattern)) {
                result.add(word);
            }
        }
        
        return result;
    }
    
    private boolean matches(String word, String pattern) {
        if (word.length() != pattern.length()) return false;
        
        Map<Character, Character> map = new HashMap<>();
        Set<Character> used = new HashSet<>();
        
        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            
            if (map.containsKey(p)) {
                if (map.get(p) != w) return false; // inconsistent mapping
            } else {
                if (used.contains(w)) return false; // bijection violated
                map.put(p, w);
                used.add(w);
            }
        }
        
        return true;
    }
}