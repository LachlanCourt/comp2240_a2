/*******************************************************************************
 ****    COMP2240 Assignment 2
 ****    c3308061
 ****    Lachlan Court
 ****    29/08/2021
 ****    This class represents a Print Head that runs print jobs concurrently
 ****    with other Print Heads. Class interfaces with a printer to run in real
 ****    time
 *******************************************************************************/

package P2;

public class PrintHead implements Runnable
{
    private Printer printer;
    private boolean printing;
    private int startTime;
    private int id;

    // Constructor
    public PrintHead(Printer printer_, int id_)
    {
        printer = printer_;
        printing = false;
        startTime = 0;
        id = id_;
    }

    // Getters
    public int getId()
    {
        return id;
    }

    public boolean printing()
    {
        return printing;
    }

    /**
     * Main run method for concurrent threads. Simulates a Print Head working on a job
     */
    @Override public void run()
    {
        // Initialise job
        Job job = null;
        // Loop while the simulation is not complete
        while (!printer.isFinished())
        {
            // Sync block with the printer as unique identifier
            synchronized (printer)
            {
                // Check if this print head would be able to run the next job in the list
                if (printer.validJobNext())
                {
                    // Get a job
                    job = printer.getJob();
                    if (job != null)
                    {
                        // If the job is not null, start it running
                        System.out.println("(" + printer.getTime() + ") " + job.getId() + " uses head " + id
                                           + " (time:" + job.getSize() + ")");
                        printing = true;
                        startTime = printer.getTime();
                    }
                }
                // The Printer's getJob method will return null if there are no jobs left in the list. In that case,
                // the jobs must be done so the loop can end
                if (job == null)
                {
                    break;
                }
            }

            // If a job is ready to print, let's get it going
            if (printing)
            {
                // Query the global time from the printer and loop until the job is complete
                while (printer.getTime() < startTime + job.getSize())
                {
                    // Busy waiting without a delay means that global time never increments. Big bad :(
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e)
                    {
                        // Shouldn't happen!
                    }
                }
                // When the loop has ended, the print job has finished
                printing = false;
            }
        }
    }
}
