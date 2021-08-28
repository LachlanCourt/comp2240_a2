package P1;

public class WAR implements Runnable
{
    private int track;
    private boolean status;
    String name;
    Intersection intersection;

    public WAR(int track_, boolean status_, int identifier, Intersection intersection_)
    {
        track = track_;
        status = status_;
        name = "WAR-" + identifier;
        intersection = intersection_;
    }

    @Override public void run()
    {
        System.out.println(name + " Coming online:\nTrack: " + track + " status: " + (status ? "Loaded" : "Unloaded"));
        while (intersection.getRunning())
        {
            System.out.println(name + " (" + (status ? "Loaded" : "Unloaded")
                               + "): Waiting at the Intersection. Going towards " + (status ? "Dock" : "Storage")
                               + track);
            // Critical section
            try
            {
                intersection.getBlock().acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            for (int i = 1; i <= 3; i++)
            {
                System.out.println(name + " (" + (status ? "Loaded" : "Unloaded")
                                   + "): Crossing intersection Checkpoint " + i + ".");
                try
                {
                    Thread.sleep(200);
                }
                catch (InterruptedException e)
                {
                    // Will never be interrupted
                }
            }
            intersection.reportPass(track);
            intersection.getBlock().release();
            // End Critical section
            status = !status;
        }
        // System.out.println("track: " + track + " status: " + status);
    }
}
