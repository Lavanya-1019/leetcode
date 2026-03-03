import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // Step 1: assign unique group IDs for items with group[i] == -1
        int groupId = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) group[i] = groupId++;
        }

        // Step 2: build graphs
        Map<Integer, List<Integer>> itemGraph = new HashMap<>();
        Map<Integer, List<Integer>> groupGraph = new HashMap<>();
        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[groupId];

        // Initialize graphs
        for (int i = 0; i < n; i++) itemGraph.put(i, new ArrayList<>());
        for (int g = 0; g < groupId; g++) groupGraph.put(g, new ArrayList<>());

        // Build edges
        for (int i = 0; i < n; i++) {
            for (int pre : beforeItems.get(i)) {
                itemGraph.get(pre).add(i);
                itemIndegree[i]++;
                if (group[pre] != group[i]) { // inter-group dependency
                    groupGraph.get(group[pre]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // Step 3: topological sort groups and items
        List<Integer> sortedGroups = topoSort(groupGraph, groupIndegree, groupId);
        if (sortedGroups.isEmpty()) return new int[0]; // cycle detected

        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for (int i = 0; i < n; i++) {
            groupToItems.computeIfAbsent(group[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> result = new ArrayList<>();
        for (int g : sortedGroups) {
            List<Integer> items = groupToItems.getOrDefault(g, new ArrayList<>());
            if (!items.isEmpty()) {
                List<Integer> sortedItems = topoSortSubGraph(items, itemGraph, itemIndegree);
                if (sortedItems.isEmpty()) return new int[0]; // cycle detected
                result.addAll(sortedItems);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // Topological sort for general graph
    private List<Integer> topoSort(Map<Integer, List<Integer>> graph, int[] indegree, int size) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < size; i++) if (indegree[i] == 0) q.offer(i);
        List<Integer> sorted = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            sorted.add(node);
            for (int nei : graph.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0) q.offer(nei);
            }
        }
        return sorted.size() == size ? sorted : new ArrayList<>();
    }

    // Topological sort for subset of nodes (items in a group)
    private List<Integer> topoSortSubGraph(List<Integer> items, Map<Integer, List<Integer>> graph, int[] indegree) {
        Queue<Integer> q = new LinkedList<>();
        for (int node : items) if (indegree[node] == 0) q.offer(node);
        List<Integer> sorted = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            sorted.add(node);
            for (int nei : graph.get(node)) {
                indegree[nei]--;
                if (items.contains(nei) && indegree[nei] == 0) q.offer(nei);
            }
        }
        return sorted.size() == items.size() ? sorted : new ArrayList<>();
    }

    // Optional main method for testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n1 = 8, m1 = 2;
        int[] group1 = {-1,-1,1,0,0,1,0,-1};
        List<List<Integer>> beforeItems1 = Arrays.asList(
            Arrays.asList(),
            Arrays.asList(6),
            Arrays.asList(5),
            Arrays.asList(6),
            Arrays.asList(3,6),
            Arrays.asList(),
            Arrays.asList(),
            Arrays.asList()
        );
        System.out.println(Arrays.toString(sol.sortItems(n1, m1, group1, beforeItems1)));
        // Possible Output: [6,3,4,1,5,2,0,7]

        int n2 = 8, m2 = 2;
        int[] group2 = {-1,-1,1,0,0,1,0,-1};
        List<List<Integer>> beforeItems2 = Arrays.asList(
            Arrays.asList(),
            Arrays.asList(6),
            Arrays.asList(5),
            Arrays.asList(6),
            Arrays.asList(3),
            Arrays.asList(),
            Arrays.asList(4),
            Arrays.asList()
        );
        System.out.println(Arrays.toString(sol.sortItems(n2, m2, group2, beforeItems2)));
        // Output: []
    }
}