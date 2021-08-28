package P1;

import java.util.concurrent.*;

public class Intersection
{
    private Semaphore block;

    public Intersection()
    {
        block  = new Semaphore(1, true);
    }

    public Semaphore getBlock()
    {
        return block;
    }
}
