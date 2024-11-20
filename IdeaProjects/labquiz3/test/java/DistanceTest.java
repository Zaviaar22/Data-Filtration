import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceTest {

    @Test
    void chebyshevDistance() {
        assertEquals(5, chebyshevDistance(new int[]{1, 2}, new int[]{6, 7})); // Regular case
        assertEquals(0, chebyshevDistance(new int[]{0, 0}, new int[]{0, 0})); // Same points
        assertEquals(10, chebyshevDistance(new int[]{-5, 3}, new int[]{5, -7})); // Negative coordinates
        assertEquals(Integer.MAX_VALUE, chebyshevDistance(
                new int[]{Integer.MAX_VALUE, 0}, new int[]{0, Integer.MIN_VALUE})); // Large inputs
        assertThrows(IllegalArgumentException.class, () -> chebyshevDistance(
                new int[]{1, 2}, new int[]{1})); // Mismatched dimensions
    }

    @Test
    void squaredDistance() {
        assertEquals(50, squaredDistance(new int[]{1, 2}, new int[]{6, 7})); // Regular case
        assertEquals(0, squaredDistance(new int[]{0, 0}, new int[]{0, 0})); // Same points
        assertEquals(200, squaredDistance(new int[]{-5, 3}, new int[]{5, -7})); // Negative coordinates
        assertThrows(ArithmeticException.class, () -> squaredDistance(
                new int[]{0, 0}, new int[]{Integer.MAX_VALUE, 0})); // Large inputs causing overflow
        assertThrows(IllegalArgumentException.class, () -> squaredDistance(
                new int[]{1, 2}, new int[]{1})); // Mismatched dimensions
    }

    @Test
    void euclideanDistance() {
        assertEquals(7.071, euclideanDistance(new int[]{1, 2}, new int[]{6, 7}), 0.001); // Regular case
        assertEquals(0, euclideanDistance(new int[]{0, 0}, new int[]{0, 0})); // Same points
        assertEquals(14.142, euclideanDistance(new int[]{-5, 3}, new int[]{5, -7}), 0.001); // Negative coordinates

        // Extremely large values
        assertEquals(Math.sqrt(Math.pow((double) Integer.MAX_VALUE - Integer.MIN_VALUE, 2)),
                euclideanDistance(new int[]{Integer.MAX_VALUE, 0}, new int[]{Integer.MIN_VALUE, 0}), 0.001);

        // Mismatched dimensions
        assertThrows(IllegalArgumentException.class, () -> euclideanDistance(
                new int[]{1, 2}, new int[]{1}));
    }



    // Distance method implementations (for testing purposes)
    int chebyshevDistance(int[] point1, int[] point2) {
        if (point1.length != point2.length) {
            throw new IllegalArgumentException("Points must have the same dimensions.");
        }

        int maxDifference = 0;
        for (int i = 0; i < point1.length; i++) {
            maxDifference = Math.max(maxDifference, Math.abs(point1[i] - point2[i]));
        }
        return maxDifference;
    }

    int squaredDistance(int[] point1, int[] point2) {
        if (point1.length != point2.length) {
            throw new IllegalArgumentException("Points must have the same dimensions.");
        }

        long sumOfSquares = 0; // Use `long` to prevent overflow
        for (int i = 0; i < point1.length; i++) {
            long difference = (long) point1[i] - point2[i]; // Cast to long
            sumOfSquares += difference * difference;
        }

        if (sumOfSquares > Integer.MAX_VALUE) {
            throw new ArithmeticException("Result exceeds maximum integer value.");
        }

        return (int) sumOfSquares;
    }

    double euclideanDistance(int[] point1, int[] point2) {
        if (point1.length != point2.length) {
            throw new IllegalArgumentException("Points must have the same dimensions.");
        }

        double sumOfSquares = 0;
        for (int i = 0; i < point1.length; i++) {
            double difference = (double) point1[i] - point2[i];
            sumOfSquares += difference * difference;
        }

        return Math.sqrt(sumOfSquares);
    }

}
