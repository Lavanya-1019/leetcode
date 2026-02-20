import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

// TreeNode class (non-public so it can be in the same file)
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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go as left as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // Visit node
            current = stack.pop();
            result.add(current.val);
            // Go to right subtree
            current = current.right;
        }

        return result;
    }
}