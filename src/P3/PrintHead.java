package P3;

public class PrintHead implements Runnable
{
    private Printer printer;
    private boolean printing;
    private int startTime;
    private int id;

    public PrintHead(Printer printer_, int id_)
    {
        printer = printer_;
        printing = false;
        startTime = 0;
        id = id_;
    }

    public int getId()
    {
        return id;
    }

    public boolean printing()
    {
        return printing;
    }

    @Override public void run()
    {
        Job job = null;
        while (!printer.isFinished())
        {
            try
            {
                printer.getBlock().acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                break;
            }
            if (printer.validJobNext())
            {
                job = printer.getJob();
                if (job != null)
                {
                    System.out.println("(" + printer.getTime() + ") " + job.getId() + " uses head " + id
                                       + " (time:" + job.getSize() + ")");
                    printing = true;
                    startTime = printer.getTime();
                }
            }

            if (job == null)
            {
                printer.getBlock().release();
                break;
            }
            printer.getBlock().release();

            if (printing)
            {
                while (printer.getTime() < startTime + job.getSize())
                {
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e)
                    {
                        // pass
                    }
                    // System.out.println("Head " + id + " printing job " + job.getId() + ", current time is " +
                    // printer.getTime());
                    // vibe
                }

                printing = false;
            }
        }
    }
}
