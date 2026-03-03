import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] redGraph = new List[n];
        List<Integer>[] blueGraph = new List[n];
        
        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }
        
        for (int[] e : redEdges) redGraph[e[0]].add(e[1]);
        for (int[] e : blueEdges) blueGraph[e[0]].add(e[1]);
        
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        answer[0] = 0;
        
        boolean[][] visited = new boolean[n][2]; // visited[node][0:red,1:blue]
        
        Queue<int[]> q = new LinkedList<>();
        // {node, lastColor, distance}
        q.offer(new int[]{0, 0, 0}); // coming from red (but starting node)
        q.offer(new int[]{0, 1, 0}); // coming from blue (starting node)
        visited[0][0] = visited[0][1] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0], lastColor = cur[1], dist = cur[2];
            
            // determine next edges based on alternating color
            List<Integer>[] nextGraph = (lastColor == 0) ? blueGraph : redGraph;
            int nextColor = 1 - lastColor;
            
            for (int nei : nextGraph[node]) {
                if (!visited[nei][nextColor]) {
                    visited[nei][nextColor] = true;
                    if (answer[nei] == -1 || answer[nei] > dist + 1) {
                        answer[nei] = dist + 1;
                    }
                    q.offer(new int[]{nei, nextColor, dist + 1});
                }
            }
        }
        
        return answer;
    }
    
    // Optional main to test
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n1 = 3;
        int[][] red1 = {{0,1},{1,2}};
        int[][] blue1 = {};
        System.out.println(Arrays.toString(sol.shortestAlternatingPaths(n1, red1, blue1)));
        // Output: [0,1,-1]

        int n2 = 3;
        int[][] red2 = {{0,1}};
        int[][] blue2 = {{2,1}};
        System.out.println(Arrays.toString(sol.shortestAlternatingPaths(n2, red2, blue2)));
        // Output: [0,1,-1]
    }
}