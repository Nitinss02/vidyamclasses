import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Job {
    String name;
    int executionTime;
    List<String> requiredResources;
    List<String> dependencies;
    int importance;

    public Job(String name, int executionTime, List<String> requiredResources,
            List<String> dependencies, int importance) {
        this.name = name;
        this.executionTime = executionTime;
        this.requiredResources = requiredResources;
        this.dependencies = dependencies;
        this.importance = importance;
    }
}

public class JobScheduler {

    public static void main(String[] args) {
        List<Job> jobs1 = new ArrayList<>();
        jobs1.add(new Job("Job1", 4, List.of("CPU"), List.of(), 3));
        jobs1.add(new Job("Job2", 2, List.of("GPU"), List.of(), 2));
        jobs1.add(new Job("Job3", 3, List.of("CPU", "GPU"), List.of(), 4));
        jobs1.add(new Job("Job4", 1, List.of("CPU"), List.of("Job1"), 1));
        jobs1.add(new Job("Job5", 3, List.of("GPU"), List.of("Job2"), 3));
        jobs1.add(new Job("Job6", 2, List.of("CPU", "GPU"), List.of("Job4"), 2));

        jobScheduler(jobs1);

        System.out.println("\n\n");

        List<Job> jobs2 = new ArrayList<>();
        jobs2.add(new Job("Job1", 6, List.of("CPU"), List.of(), 3));
        jobs2.add(new Job("Job2", 4, List.of("GPU"), List.of(), 2));
        jobs2.add(new Job("Job3", 8, List.of("CPU", "GPU"), List.of(), 4));
        jobs2.add(new Job("Job4", 3, List.of("CPU"), List.of("Job1"), 1));
        jobs2.add(new Job("Job5", 5, List.of("GPU"), List.of("Job2"), 3));
        jobs2.add(new Job("Job6", 7, List.of("CPU", "GPU"), List.of("Job4"), 2));
        jobs2.add(new Job("Job7", 2, List.of("CPU"), List.of(), 5));
        jobs2.add(new Job("Job8", 4, List.of("GPU"), List.of(), 3));
        jobs2.add(new Job("Job9", 6, List.of("CPU", "GPU"), List.of("Job7", "Job8"), 2));
        jobs2.add(new Job("Job10", 3, List.of("CPU"), List.of("Job1", "Job7"), 1));
        jobs2.add(new Job("Job11", 5, List.of("GPU"), List.of("Job2", "Job8"), 3));
        jobs2.add(new Job("Job12", 4, List.of("CPU"), List.of("Job10"), 2));
        jobs2.add(new Job("Job13", 6, List.of("GPU"), List.of("Job5"), 4));
        jobs2.add(new Job("Job14", 3, List.of("CPU", "GPU"), List.of("Job12", "Job13"), 1));
        jobs2.add(new Job("Job15", 7, List.of("CPU"), List.of("Job3", "Job6"), 3));
        jobs2.add(new Job("Job16", 5, List.of("GPU"), List.of("Job3", "Job9"), 2));
        jobs2.add(new Job("Job17", 4, List.of("CPU", "GPU"), List.of("Job11", "Job14"), 4));
        jobs2.add(new Job("Job18", 3, List.of("CPU"), List.of("Job10", "Job12"), 1));
        jobs2.add(new Job("Job19", 6, List.of("GPU"), List.of("Job13", "Job16"), 3));
        jobs2.add(new Job("Job20", 2, List.of("CPU", "GPU"), List.of("Job17", "Job18"), 2));

        jobScheduler(jobs2);
    }

    public static void jobScheduler(List<Job> jobs) {
        // Priority queue for jobs, sorted by importance (descending)
        // PriorityQueue<Job> jobQueue = new PriorityQueue<>(Comparator.comparingInt(Job::getImportance).reversed());
        PriorityQueue<Job> jobQueue = new PriorityQueue<>(Comparator.reverseOrder(Job));

        // Map to track the dependencies of each job
        Map<String, List<String>> dependencyMap = new HashMap<>();

        // Initialize dependency map
        for (Job job : jobs) {
            dependencyMap.put(job.name, job.dependencies);
        }

        // Set to track available resources
        Set<String> availableResources = new HashSet<>();

        // Initialize available resources
        for (Job job : jobs) {
            availableResources.addAll(job.requiredResources);
        }

        // Map to track job start times
        Map<String, Integer> jobStartTimes = new HashMap<>();

        // Current time
        int currentTime = 0;

        // Process jobs until the queue is empty
        while (!jobQueue.isEmpty() || !dependencyMap.isEmpty()) {
            // Add jobs to queue if their dependencies are met
            for (Job job : jobs) {
                if (job.dependencies.isEmpty() && !jobQueue.contains(job)) {
                    jobQueue.offer(job);
                }
            }

            // Get the highest priority job from the queue
            Job currentJob = jobQueue.poll();

            // If no job is available, increment time
            if (currentJob == null) {
                currentTime++;
                continue;
            }

            // Check if resources are available for the job
            if (availableResources.containsAll(currentJob.requiredResources)) {
                // Remove resources used by the job
                availableResources.removeAll(currentJob.requiredResources);

                // Start the job
                jobStartTimes.put(currentJob.name, currentTime);

                // Remove dependencies of the job
                dependencyMap.remove(currentJob.name);

                // Update dependency maps for jobs dependent on the current job
                for (List<String> dependencies : dependencyMap.values()) {
                    dependencies.remove(currentJob.name);
                }

                // Execute the job
                currentTime += currentJob.executionTime;

                // Release resources used by the job
                availableResources.addAll(currentJob.requiredResources);

                // Print job information
                System.out.println(currentJob.name + " started at " + jobStartTimes.get(currentJob.name)
                        + " seconds using " + currentJob.requiredResources);
            } else {
                // If resources are not available, increment time and re-enqueue the job
                currentTime++;
                jobQueue.offer(currentJob);
            }
        }
    }
}