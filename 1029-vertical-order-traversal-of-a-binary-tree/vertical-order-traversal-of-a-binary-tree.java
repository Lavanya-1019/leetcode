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

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.offer(new NodeInfo(root, 0, 0));

        while (!queue.isEmpty()) {
            NodeInfo current = queue.poll();
            TreeNode node = current.node;
            int row = current.row;
            int col = current.col;

            map.putIfAbsent(col, new TreeMap<>());
            map.get(col).putIfAbsent(row, new PriorityQueue<>());
            map.get(col).get(row).offer(node.val);

            if (node.left != null) queue.offer(new NodeInfo(node.left, row + 1, col - 1));
            if (node.right != null) queue.offer(new NodeInfo(node.right, row + 1, col + 1));
        }

        List<List<Integer>> result = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> rows : map.values()) {
            List<Integer> colList = new ArrayList<>();
            for (PriorityQueue<Integer> pq : rows.values()) {
                while (!pq.isEmpty()) colList.add(pq.poll());
            }
            result.add(colList);
        }

        return result;
    }

    private static class NodeInfo {
        TreeNode node;
        int row;
        int col;

        NodeInfo(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
}