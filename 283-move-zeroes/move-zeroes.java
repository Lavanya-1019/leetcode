class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int insertPos = 0; 
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }
        
        while (insertPos < n) {
            nums[insertPos] = 0;
            insertPos++;
        }
    }
}