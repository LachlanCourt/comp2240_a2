/*******************************************************************************
 ****    COMP2240 Assignment 2
 ****    c3308061
 ****    Lachlan Court
 ****    29/08/2021
 ****    This class represents a Printer that manages the distribution of jobs
 ****    to concurrently running print heads. It has restrictions to only print
 ****    jobs of the same type at any one time (monochrome or colour). It is
 ****    also responsible for mutual exclusion of the PrintHeads by way of a
 ****    semaphore
 *******************************************************************************/

package P3;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Printer
{
    private String jobType;
    ArrayList<Job> jobs;
    int currentTime;
    ArrayList<PrintHead> printHeads;
    private Semaphore block;

    public Printer(ArrayList<Job> jobs_)
    {
        jobType = "none";
        jobs = jobs_;
        currentTime = 0;
        block = new Semaphore(1, true);
    }

    // Getter for semaphore
    public Semaphore getBlock()
    {
        return block;
    }

    /**
     * Allows the Printer to be aware of its print heads
     * @param printHeads_ an array list of concurrently running print heads
     */
    public void setPrintHeads(ArrayList<PrintHead> printHeads_)
    {
        printHeads = printHeads_;
    }

    /**
     * Whilst the printer does not implement Runnable, it will still run concurrently alongside the print heads, in
     * the main thread, to manage global time. As such, a start method has been implemented
     */
    public void start()
    {
        // Loop while the simulation is not complete
        while (!isFinished())
        {
            // Sleep for a whole second
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                // Shouldn't happen!
            }
            // Increment time
            currentTime++;
        }
        // Time will be one more than the actual finish time because the loop above increments before
        // checking if it is done
        currentTime--;
        // Output when complete
        System.out.println("(" + currentTime + ")"
                + " DONE");
    }

    /**
     * Checks whether the next job in the queue matches the type of jobs that are currently being run
     * @return whether the next job is able to be run according to the current jobs being run
     */
    public boolean validJobNext()
    {
        // Before checking the next job in the queue, check if all print heads are done - if so, any job is fair game
        boolean done = true;
        // Loop through all print heads
        for (PrintHead printHead : printHeads)
        {
            // If any job is not complete, the jobType stays the same
            if (printHead.printing())
            {
                done = false;
            }
        }
        // If all jobs are complete, any job is fair game
        if (done)
        {
            jobType = "none";
        }
        // If no job is currently running or if the next job matches the current job type
        return jobType.equals("none") || ((jobs.size() > 0) && (jobs.get(0).getType().equals(jobType)));
    }

    // Getters
    public int getTime()
    {
        return currentTime;
    }
    public String getJobType()
    {
        return jobType;
    }

    public void setJobType(String jobType_)
    {
        jobType = jobType_;
    }

    public int getJobsSize()
    {
        return jobs.size();
    }

    /**
     * Gets the next job in the list
     * @return the next job iin the list, or null if the list is empty
     */
    Job getJob()
    {
        if (jobs.size() > 0)
        {
            Job job = jobs.remove(0);
            if (job != null)
            {
                jobType = job.getType();
            }
            return job;
        }
        return null;
    }

    /**
     * Returns whether all print heads are finished and the jobs list is empty
     * @return true if all jobs are finished and jobs list is empty
     */
    public boolean isFinished()
    {
        // If there are jobs still in the list then there's no way that the simulation is finished
        if (jobs.size() > 0)
        {
            return false;
        }
        // Assume the jobs are done
        boolean done = true;
        // Loop through print heads and check if any are still printing
        for (PrintHead printHead : printHeads)
        {
            if (printHead.printing())
            {
                done = false;
            }
        }
        return done;
    }
}
