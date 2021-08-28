package P1;

public class WAR implements Runnable
{
    private int track;
    private boolean status;

    public WAR() {}

    public WAR(int track_, boolean status_)
    {
        track = track_;
        status = status_;
    }

    @Override public void run()
    {
        System.out.println("track: " + track + " status: " + status);
    }
}
