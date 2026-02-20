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

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> output = new Stack<>(); // To reverse the process
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.push(node); // Push root to output stack

            // Push left first, then right
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        // Pop from output stack to get postorder
        while (!output.isEmpty()) {
            result.add(output.pop().val);
        }

        return result;
    }
}