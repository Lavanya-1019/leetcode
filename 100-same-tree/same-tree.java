class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { 
        this.val = val; 
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {

    // Function to check if two trees are the same
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true; // Both nodes are null, same
        }
        if (p == null || q == null) {
            return false; // One is null, the other is not
        }
        if (p.val != q.val) {
            return false; // Node values differ
        }
        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Example usage
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: [1,2,3] and [1,2,3]
        TreeNode p1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(sol.isSameTree(p1, q1)); // true

        // Example 2: [1,2] and [1,null,2]
        TreeNode p2 = new TreeNode(1, new TreeNode(2), null);
        TreeNode q2 = new TreeNode(1, null, new TreeNode(2));
        System.out.println(sol.isSameTree(p2, q2)); // false

        // Example 3: [1,2,1] and [1,1,2]
        TreeNode p3 = new TreeNode(1, new TreeNode(2), new TreeNode(1));
        TreeNode q3 = new TreeNode(1, new TreeNode(1), new TreeNode(2));
        System.out.println(sol.isSameTree(p3, q3)); // false
    }
}