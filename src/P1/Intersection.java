package P1;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Intersection
{
    private Semaphore block;
    private boolean running;
    private static ArrayList<Integer> trackCount;

    public static final int MAX_CROSSING = 150;

    public Intersection()
    {
        block = new Semaphore(1, true);
        running = true;

        trackCount = new ArrayList<>();
        trackCount.add(0);
        trackCount.add(0);
    }

    public Semaphore getBlock()
    {
        return block;
    }

    public boolean getRunning()
    {
        return running;
    }

    public void reportPass(int track)
    {
        track -= 1;  // Arrays index from 0 but tracks are 1 and 2
        trackCount.set(track, trackCount.get(track) + 1);
        System.out.println("Total crossed in Track1: " + trackCount.get(0) + " Track 2: " + trackCount.get(1));
        if ((trackCount.get(0) > MAX_CROSSING) && (trackCount.get(1) > MAX_CROSSING))
        {
            running = false;
        }
    }
}
