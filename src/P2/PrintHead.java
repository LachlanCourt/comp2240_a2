package P2;

public class PrintHead implements Runnable
{
    private Printer printer;
    private boolean printing;
    private int startTime;

    public PrintHead(Printer printer_)
    {
        printer = printer_;
        printing = false;
        startTime = 0;
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
            synchronized (this) {
                if (printer.validJobNext()) {
                    job = printer.getJob();
                }

                if (job == null) {
                    break;
                }
                printing = true;
            }

            if (printing) {
                while (printer.getTime() < startTime + job.getSize()) {
                    // vibe
                }
                System.out.println(printer.getTime() + " job: " + job.getId());
                printing = false;
            }
        }
    }
}
