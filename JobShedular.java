import java.util.*;

public class JobShedular {

    public static void main(String[] args) {
        // Test case 1
        List<Job> jobs1 = Arrays.asList(
                new Job("Job1", 4, Arrays.asList("CPU"), new ArrayList<>(), 3),
                new Job("Job2", 2, Arrays.asList("GPU"), new ArrayList<>(), 2),
                new Job("Job3", 3, Arrays.asList("CPU", "GPU"), new ArrayList<>(), 4),
                new Job("Job4", 1, Arrays.asList("CPU"), Arrays.asList("Job1"), 1),
                new Job("Job5", 3, Arrays.asList("GPU"), Arrays.asList("Job2"), 3),
                new Job("Job6", 2, Arrays.asList("CPU", "GPU"), Arrays.asList("Job4"), 2));
        jobScheduler(jobs1);

        // Test case 2
        List<Job> jobs2 = Arrays.asList(
                new Job("Job1", 6, Arrays.asList("CPU"), new ArrayList<>(), 3),
                new Job("Job2", 4, Arrays.asList("GPU"), new ArrayList<>(), 2),
                new Job("Job3", 8, Arrays.asList("CPU", "GPU"), new ArrayList<>(), 4),
                new Job("Job4", 3, Arrays.asList("CPU"), Arrays.asList("Job1"), 1),
                new Job("Job5", 5, Arrays.asList("GPU"), Arrays.asList("Job2"), 3),
                new Job("Job6", 7, Arrays.asList("CPU", "GPU"), Arrays.asList("Job4"), 2),
                new Job("Job7", 2, Arrays.asList("CPU"), new ArrayList<>(), 5),
                new Job("Job8", 4, Arrays.asList("GPU"), new ArrayList<>(), 3),
                new Job("Job9", 6, Arrays.asList("CPU", "GPU"), Arrays.asList("Job7", "Job8"), 2),
                new Job("Job10", 3, Arrays.asList("CPU"), Arrays.asList("Job1", "Job7"), 1),
                new Job("Job11", 5, Arrays.asList("GPU"), Arrays.asList("Job2", "Job8"), 3),
                new Job("Job12", 4, Arrays.asList("CPU"), Arrays.asList("Job10"), 2),
                new Job("Job13", 6, Arrays.asList("GPU"), Arrays.asList("Job5"), 4),
                new Job("Job14", 3, Arrays.asList("CPU", "GPU"), Arrays.asList("Job12", "Job13"), 1),
                new Job("Job15", 7, Arrays.asList("CPU"), Arrays.asList("Job3", "Job6"), 3),
                new Job("Job16", 5, Arrays.asList("GPU"), Arrays.asList("Job3", "Job9"), 2),
                new Job("Job17", 4, Arrays.asList("CPU", "GPU"), Arrays.asList("Job11", "Job14"), 4),
                new Job("Job18", 3, Arrays.asList("CPU"), Arrays.asList("Job10", "Job12"), 1),
                new Job("Job19", 6, Arrays.asList("GPU"), Arrays.asList("Job13", "Job16"), 3),
                new Job("Job20", 2, Arrays.asList("CPU", "GPU"), Arrays.asList("Job17", "Job18"), 2));
        jobScheduler(jobs2);
    }

    static class Job {
        String name;
        int executionTime;
        List<String> requiredResources;
        List<String> dependencies;
        int importance;

        public Job(String name, int executionTime, List<String> requiredResources, List<String> dependencies,
                int importance) {
            this.name = name;
            this.executionTime = executionTime;
            this.requiredResources = requiredResources;
            this.dependencies = dependencies;
            this.importance = importance;
        }
    }

    public static void jobScheduler(List<Job> jobs) {
        // Sort jobs by importance in descending order
        jobs.sort(Comparator.comparingInt(Job::getImportance).reversed());

        // Create a queue to hold the jobs
        Queue<Job> jobQueue = new LinkedList<>(jobs);

        // Create a map to track available resources
        Map<String, Boolean> availableResources = new HashMap<>();
        for (Job job : jobs) {
            for (String resource : job.requiredResources) {
                availableResources.put(resource, true);
            }
        }

        // Create a map to track completed jobs
        Map<String, Boolean> completedJobs = new HashMap<>();

        // Create a map to store the start time of each job
        Map<String, Integer> jobStartTime = new HashMap<>();

        // Track the current time
        int currentTime = 0;

        while (!jobQueue.isEmpty()) {
            // Get the next job from the queue
            Job currentJob = jobQueue.peek();

            // Check if the job's dependencies are met
            boolean dependenciesMet = currentJob.dependencies.stream().allMatch(completedJobs::containsKey);

            // Check if all required resources are available
            boolean resourcesAvailable = currentJob.requiredResources.stream().allMatch(availableResources::get);

            if (dependenciesMet && resourcesAvailable) {
                // Remove the job from the queue
                jobQueue.poll();

                // Mark the job as completed
                completedJobs.put(currentJob.name, true);

                // Record the start time of the job
                jobStartTime.put(currentJob.name, currentTime);

                // Allocate resources for the job
                for (String resource : currentJob.requiredResources) {
                    availableResources.put(resource, false);
                }

                // Advance the current time
                currentTime += currentJob.executionTime;

                // Release resources after job execution
                for (String resource : currentJob.requiredResources) {
                    availableResources.put(resource, true);
                }

                // Print the job information
                System.out.println(currentJob.name + " started at " + jobStartTime.get(currentJob.name)
                        + " seconds using " + currentJob.requiredResources);
            } else {
                // If dependencies are not met or resources are not available, move the job to
                // the end of the queue
                jobQueue.offer(currentJob);
            }
        }
    }
}
