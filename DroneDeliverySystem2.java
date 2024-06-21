import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class DroneDeliverySystem2 {

    private static final int NUM_POINTS = 1000;

    public static void main(String[] args) {

        // Generate random delivery points
        List<Point> points = generatePoints(NUM_POINTS);

        // Calculate the closest pair of points using brute-force approach
        long bruteForceStartTime = System.nanoTime();
        Point[] closestPairBruteForce = findClosestPairBruteForce(points);
        long bruteForceEndTime = System.nanoTime();
        double bruteForceTimeTaken = (bruteForceEndTime - bruteForceStartTime) / 1000000.0; // in milliseconds

        // Calculate the closest pair of points using divide-and-conquer approach
        long divideAndConquerStartTime = System.nanoTime();
        Point[] closestPairDivideAndConquer = findClosestPairDivideAndConquer(points);
        long divideAndConquerEndTime = System.nanoTime();
        double divideAndConquerTimeTaken = (divideAndConquerEndTime - divideAndConquerStartTime) / 1000000.0; // in
                                                                                                              // milliseconds

        // Simulate the drone delivery system using brute-force approach
        double bruteForceTotalDistance = simulateDelivery(points, closestPairBruteForce);

        // Simulate the drone delivery system using divide-and-conquer approach
        double divideAndConquerTotalDistance = simulateDelivery(points, closestPairDivideAndConquer);

        // Print the results
        System.out.println("======================================================");
        System.out.println("Brute Force Approach:");
        System.out.println("Closest Pair: (" + closestPairBruteForce[0].x + ", " + closestPairBruteForce[0].y + "), ("
                + closestPairBruteForce[1].x + ", " + closestPairBruteForce[1].y + ")");
        System.out.println("Total Distance: " + bruteForceTotalDistance);
        System.out.println("Time Taken: " + bruteForceTimeTaken + " milliseconds");
        System.out.println("======================================================");
        System.out.println("Divide-and-Conquer Approach:");
        System.out
                .println("Closest Pair: (" + closestPairDivideAndConquer[0].x + ", " + closestPairDivideAndConquer[0].y
                        + "), (" + closestPairDivideAndConquer[1].x + ", " + closestPairDivideAndConquer[1].y + ")");
        System.out.println("Total Distance: " + divideAndConquerTotalDistance);
        System.out.println("Time Taken: " + divideAndConquerTimeTaken + " milliseconds");
        System.out.println("======================================================");
    }

    // Generate a list of random delivery points
    private static List<Point> generatePoints(int numPoints) {
        List<Point> points = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numPoints; i++) {
            int x = random.nextInt(100);
            int y = random.nextInt(100);
            points.add(new Point(x, y));
        }
        return points;
    }

    // Find the closest pair of points using brute-force approach
    private static Point[] findClosestPairBruteForce(List<Point> points) {
        Point[] closestPair = new Point[2];
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double distance = calculateDistance(points.get(i), points.get(j));
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPair[0] = points.get(i);
                    closestPair[1] = points.get(j);
                }
            }
        }
        return closestPair;
    }

    // Find the closest pair of points using divide-and-conquer approach
    private static Point[] findClosestPairDivideAndConquer(List<Point> points) {
        // Sort the points by x-coordinate
        Collections.sort(points, Comparator.comparingDouble(p -> p.x));
        return findClosestPairDivideAndConquer(points, 0, points.size() - 1);
    }

    // Recursive helper function for divide-and-conquer approach
    private static Point[] findClosestPairDivideAndConquer(List<Point> points, int left, int right) {
        // Base case: If there are only two points left, return them as the closest pair
        if (right - left <= 1) {
            return new Point[] { points.get(left), points.get(right) };
        }

        // Divide the points into two halves
        int mid = (left + right) / 2;
        Point[] leftPair = findClosestPairDivideAndConquer(points, left, mid);
        Point[] rightPair = findClosestPairDivideAndConquer(points, mid + 1, right);

        // Find the minimum distance between the two halves
        double minDistance = Math.min(calculateDistance(leftPair[0], leftPair[1]),
                calculateDistance(rightPair[0], rightPair[1]));
        Point[] closestPair = new Point[] { leftPair[0], leftPair[1] };

        // Create a list of points that are within minDistance of the midpoint
        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points.get(i).x - points.get(mid).x) < minDistance) {
                strip.add(points.get(i));
            }
        }

        // Sort the strip by y-coordinate
        Collections.sort(strip, Comparator.comparingDouble(p -> p.y));

        // Find the closest pair within the strip
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < minDistance; j++) {
                double distance = calculateDistance(strip.get(i), strip.get(j));
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPair[0] = strip.get(i);
                    closestPair[1] = strip.get(j);
                }
            }
        }
        return closestPair;
    }

    // Calculate the distance between two points
    private static double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    // Simulate the drone delivery system
    private static double simulateDelivery(List<Point> points, Point[] closestPair) {
        // Calculate the total distance traveled by the drone
        double totalDistance = 0;
        List<Point> visitedPoints = new ArrayList<>();
        visitedPoints.add(closestPair[0]);
        visitedPoints.add(closestPair[1]);
        while (visitedPoints.size() < points.size()) {
            // Find the closest point to the last visited point
            Point closestPoint = findClosestPoint(visitedPoints.get(visitedPoints.size() - 1), points, visitedPoints);
            visitedPoints.add(closestPoint);
            totalDistance += calculateDistance(visitedPoints.get(visitedPoints.size() - 2),
                    visitedPoints.get(visitedPoints.size() - 1));
        }
        return totalDistance;
    }

    // Find the closest point to a given point
    private static Point findClosestPoint(Point point, List<Point> points, List<Point> visitedPoints) {
        Point closestPoint = null;
        double minDistance = Double.MAX_VALUE;
        for (Point p : points) {
            if (!visitedPoints.contains(p)) {
                double distance = calculateDistance(point, p);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint = p;
                }
            }
        }
        return closestPoint;
    }

    // Point class
    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
