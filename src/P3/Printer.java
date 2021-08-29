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

    public void setPrintHeads(ArrayList<PrintHead> printHeads_)
    {
        printHeads = printHeads_;
    }

    public void start()
    {
        while (!isFinished())
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            currentTime++;
        }
        currentTime--;  // Time will be one more than the actual finish time because the loop above increments before
                        // checking if it is done
        System.out.println("(" + currentTime + ")"
                           + " DONE");
    }


    public Semaphore getBlock()
    {
        return block;

    public boolean validJobNext()
    {
        boolean done = true;
        for (PrintHead printHead : printHeads)
        {
            if (printHead.printing())
            {
                done = false;
            }
        }
        if (done)
        {
            jobType = "none";
        }
        // System.out.println(jobType);
        return jobType.equals("none") || ((jobs.size() > 0) && (jobs.get(0).getType().equals(jobType)));
    }

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

    public int getJobSize()
    {
        return jobs.size();
    }

    public boolean isFinished()
    {
        if (jobs.size() > 0)
        {
            return false;
        }
        boolean done = true;
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
