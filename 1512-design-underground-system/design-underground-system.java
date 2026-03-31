import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {
    // Maps customer ID to {StationName, CheckInTime}
    private Map<Integer, CheckInInfo> checkInMap;
    // Maps "StartStation-EndStation" to {TotalTime, TripCount}
    private Map<String, TravelStats> travelDataMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        travelDataMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckInInfo info = checkInMap.remove(id);
        String route = info.stationName + "-" + stationName;
        int duration = t - info.checkInTime;

        TravelStats stats = travelDataMap.getOrDefault(route, new TravelStats(0, 0));
        stats.totalTime += duration;
        stats.count++;
        
        travelDataMap.put(route, stats);
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "-" + endStation;
        TravelStats stats = travelDataMap.get(route);
        return (double) stats.totalTime / stats.count;
    }

    // Helper classes for cleaner data management
    private static class CheckInInfo {
        String stationName;
        int checkInTime;
        CheckInInfo(String stationName, int checkInTime) {
            this.stationName = stationName;
            this.checkInTime = checkInTime;
        }
    }

    private static class TravelStats {
        long totalTime;
        int count;
        TravelStats(long totalTime, int count) {
            this.totalTime = totalTime;
            this.count = count;
        }
    }
}