package P1;

import java.util.ArrayList;

public class WAR implements Runnable
{
    private int track;
    private boolean status;
    Intersection intersection;

    private static ArrayList<Integer> trackCount;

    public WAR() {}

    public WAR(int track_, boolean status_, Intersection intersection_)
    {
        track = track_;
        status = status_;
        intersection = intersection_;

        trackCount = new ArrayList<>();
        // Janky fix - add dummy value in index 0 so that track 1 and 2 match up to the index
        trackCount.add(0);
        trackCount.add(0);
        trackCount.add(0);
    }

    @Override public void run()
    {
        while ((trackCount.get(1) < 150) || (trackCount.get(2) < 150))
        {
            
        }
        System.out.println("track: " + track + " status: " + status);
    }
}
