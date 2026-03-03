import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course); // edge: prereq -> course
        }
        
        int[] visited = new int[numCourses]; // 0: unvisited, 1: visiting, 2: visited
        List<Integer> orderList = new ArrayList<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycleDFS(adj, visited, i, orderList)) {
                    return new int[0]; // cycle detected → impossible
                }
            }
        }
        
        // Reverse post-order gives topological order
        Collections.reverse(orderList);
        int[] order = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            order[i] = orderList.get(i);
        }
        return order;
    }
    
    private boolean hasCycleDFS(List<List<Integer>> adj, int[] visited, int node, List<Integer> order) {
        visited[node] = 1; // visiting
        for (int neighbor : adj.get(node)) {
            if (visited[neighbor] == 1) return true; // cycle detected
            if (visited[neighbor] == 0 && hasCycleDFS(adj, visited, neighbor, order)) return true;
        }
        visited[node] = 2; // visited
        order.add(node); // add to post-order
        return false;
    }
    
    // Optional main method to test
    public static void main(String[] args) {
        Solution sol = new Solution();

        int numCourses1 = 2;
        int[][] prerequisites1 = {{1,0}};
        System.out.println(Arrays.toString(sol.findOrder(numCourses1, prerequisites1))); // [0,1]

        int numCourses2 = 4;
        int[][] prerequisites2 = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(sol.findOrder(numCourses2, prerequisites2))); // [0,2,1,3] or [0,1,2,3]

        int numCourses3 = 1;
        int[][] prerequisites3 = {};
        System.out.println(Arrays.toString(sol.findOrder(numCourses3, prerequisites3))); // [0]
    }
}