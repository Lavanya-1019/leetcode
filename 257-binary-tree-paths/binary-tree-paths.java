import java.util.*;

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

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        dfs(root, "", result);
        return result;
    }

    private void dfs(TreeNode node, String path, List<String> result) {
        if (node == null) return;

        // Build the current path
        if (path.isEmpty()) {
            path += node.val;
        } else {
            path += "->" + node.val;
        }

        // If leaf node, add path to result
        if (node.left == null && node.right == null) {
            result.add(path);
        } else {
            // Recurse on left and right children
            dfs(node.left, path, result);
            dfs(node.right, path, result);
        }
    }
}