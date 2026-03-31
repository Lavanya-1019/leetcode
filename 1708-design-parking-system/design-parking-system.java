class ParkingSystem {
    // Array to store the count of available slots for [big, medium, small]
    // Using size 4 so we can use carType (1, 2, 3) as direct indices
    private int[] count;

    public ParkingSystem(int big, int medium, int small) {
        count = new int[]{0, big, medium, small};
    }

    public boolean addCar(int carType) {
        // If the slot count for the specific carType is greater than 0, park the car
        if (count[carType] > 0) {
            count[carType]--;
            return true;
        }
        // No slots available
        return false;
    }
}