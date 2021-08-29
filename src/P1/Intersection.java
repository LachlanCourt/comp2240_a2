/*******************************************************************************
 ****    COMP2240 Assignment 2
 ****    c3308061
 ****    Lachlan Court
 ****    29/08/2021
 ****    This class is responsible for managing mutual exclusion through the
 ****    provision of a Semaphore. It also keeps track of how many WARs have
 ****    crossed each track
 *******************************************************************************/

package P1;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Intersection
{
    // Semaphore to manage mutual exclusion
    private Semaphore block;
    // Record if the simulation is currently running
    private boolean running;
    // Record number of WARs crossed from each track
    private static ArrayList<Integer> trackCount;

    private final int MAX_CROSSING;

    // Constructor
    public Intersection(int maxRun)
    {
        block = new Semaphore(1, true);
        running = true;
        MAX_CROSSING = maxRun;

        // Initialise the track count and add two counts starting at 0
        trackCount = new ArrayList<>();
        trackCount.add(0);
        trackCount.add(0);
    }

    // Getters
    public Semaphore getBlock()
    {
        return block;
    }

    public boolean getRunning()
    {
        return running;
    }

    /**
     * Increments the track counter and determines if the simulation should terminate
     * @param track the id of the track that a WAR just crossed the intersection on
     */
    public void reportPass(int track)
    {
        // Arrays index from 0 but tracks are 1 and 2
        track -= 1;
        // Increment the counter for that track and output to console
        trackCount.set(track, trackCount.get(track) + 1);
        System.out.println("Total crossed in Track1: " + trackCount.get(0) + " Track 2: " + trackCount.get(1));
        // Terminate the simulation if both tracks have reached the max crossing counter
        if ((trackCount.get(0) >= MAX_CROSSING) && (trackCount.get(1) >= MAX_CROSSING))
        {
            running = false;
        }
    }
}
