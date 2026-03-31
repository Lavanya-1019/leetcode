class ThroneInheritance {
    // Map to store parent -> list of children
    private Map<String, List<String>> familyTree;
    // Set to track dead members
    private Set<String> deadSet;
    private String king;

    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.familyTree = new HashMap<>();
        this.deadSet = new HashSet<>();
        familyTree.put(kingName, new ArrayList<>());
    }
    
    public void birth(String parentName, String childName) {
        // Since parentName is guaranteed to exist, add child to their list
        familyTree.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);
    }
    
    public void death(String name) {
        deadSet.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order);
        return order;
    }

    private void dfs(String current, List<String> order) {
        // If the person is alive, add them to the order
        if (!deadSet.contains(current)) {
            order.add(current);
        }
        
        // Recursively visit children in the order they were born
        List<String> children = familyTree.get(current);
        if (children != null) {
            for (String child : children) {
                dfs(child, order);
            }
        }
    }
}