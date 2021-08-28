package P1;

public class WAR implements Runnable
{
    private int track = 0;
    private boolean status;
    Intersection intersection;

    public WAR() {}

    public WAR(int track_, boolean status_, Intersection intersection_)
    {
        track = track_;
        status = status_;
        intersection = intersection_;


    }

    @Override public void run()
    {
        //System.out.println("track: " + track + " status: " + status);
        while (intersection.getRunning())
        {
            intersection.reportPass(track);
        }
        //System.out.println("track: " + track + " status: " + status);
    }
}
