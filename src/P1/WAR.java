/*******************************************************************************
 ****    COMP2240 Assignment 2
 ****    c3308061
 ****    Lachlan Court
 ****    29/08/2021
 ****    This class represents a Warehouse Assistance Robot (WAR) and has the
 ****    capacity to run concurrently with other instances. It reports to the
 ****    Intersection class each time it passes the intersection
 *******************************************************************************/

package P1;

public class WAR implements Runnable
{
    private int track;
    private boolean status;
    String name;
    Intersection intersection;

    // Constructor
    public WAR(int track_, boolean status_, int identifier, Intersection intersection_)
    {
        track = track_;
        status = status_;
        name = "WAR-" + identifier;
        intersection = intersection_;
    }

    /**
     * Main run method for concurrent threads. Simulates a WAR waiting at the intersection, crossing, then  either
     * loading or unloading and returning to the intersection
     */
    @Override public void run()
    {
        // Loop while the simulation is running
        while (intersection.getRunning())
        {
            // Output that the WAR is waiting at the intersection
            System.out.println(name + " (" + (status ? "Loaded" : "Unloaded")
                               + ") Waiting at the Intersection. Going towards " + (status ? "Dock" : "Storage")
                               + track);
            // Critical section, acquire semaphore block
            try
            {
                intersection.getBlock().acquire();
            }
            catch (InterruptedException e)
            {
                // Shouldn't happen!
                break;
            }
            // As the thread performs actions between checking while loop and acquiring the block, sim may have finished
            // in the meantime so check one more time
            if (!intersection.getRunning())
            {
                intersection.getBlock().release();
                break;
            }
            // Spec requires that the WAR passes three checkpoints while crossing the intersection
            for (int i = 1; i <= 3; i++)
            {
                System.out.println(name + " (" + (status ? "Loaded" : "Unloaded")
                                   + ") Crossing intersection Checkpoint " + i + ".");
                // Spec requires that each checkpoint takes 200ms to pass
                try
                {
                    Thread.sleep(200);
                }
                catch (InterruptedException e)
                {
                    // Shouldn't happen!
                }
            }
            // Output successful crossing and report to the intersection to increment the track count
            System.out.println(name + " (" + (status ? "Loaded" : "Unloaded") + ") Crossed the intersection.");
            intersection.reportPass(track);
            intersection.getBlock().release();
            // End Critical section, release semaphore block

            // Swap the loaded/unloaded status before returning to the intersection
            status = !status;
        }
    }
}
