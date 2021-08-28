package P1;

public class WAR implements Runnable
{
    private int track = 0;
    private boolean status;
    String name;
    Intersection intersection;

    public WAR() {}

    public WAR(int track_, boolean status_, int identifier, Intersection intersection_)
    {
        track = track_;
        status = status_;
        name = "WAR-" + String.valueOf(identifier);
        intersection = intersection_;
    }

    @Override public void run()
    {
        System.out.println(name + " Coming online:\nTrack: " + track + " status: " + status);
        while (intersection.getRunning())
        {
            intersection.reportPass(track);
        }
        // System.out.println("track: " + track + " status: " + status);
    }
}
