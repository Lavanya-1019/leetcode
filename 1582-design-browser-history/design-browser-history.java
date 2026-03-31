class BrowserHistory {
    private List<String> history;
    private int curr;
    private int last;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        curr = 0;
        last = 0;
    }
    
    public void visit(String url) {
        curr++;
        // If we are at the end of the list, add a new element
        if (curr == history.size()) {
            history.add(url);
        } else {
            // Otherwise, overwrite existing forward history
            history.set(curr, url);
        }
        // After visit, forward history is cleared
        last = curr;
    }
    
    public String back(int steps) {
        // Stay within bounds (at least index 0)
        curr = Math.max(0, curr - steps);
        return history.get(curr);
    }
    
    public String forward(int steps) {
        // Stay within bounds (at most index last)
        curr = Math.min(last, curr + steps);
        return history.get(curr);
    }
}