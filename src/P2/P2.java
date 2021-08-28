package P2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class P2
{
    public static void main(String[] args)
    {
        P2 p = new P2();
        p.run(args);
    }

    public void run(String[] args)
    {
        // Check that the file is valid, if the read throws an exception, terminate the simulation
        ArrayList<Job> jobs = new ArrayList();
        ArrayList<Thread> threads = new ArrayList();

        try
        {
            jobs = generateFromFile(args[0]);
        }
        catch (Exception e)
        {
            System.err.println("Error reading file");
            System.exit(1);
        }

        Printer printer = new Printer(jobs);
        ArrayList<PrintHead> printHeads = new ArrayList();
        for (int i = 0; i < 3; i++)
        {
            PrintHead temp = new PrintHead(printer);
            threads.add(new Thread(temp));
            printHeads.add(temp);
        }
        for (Thread thread : threads)
        {
            thread.start();
        }
        // Start the printer to track global time in the main thread
        printer.setPrintHeads(printHeads);
        printer.start();
    }

    public ArrayList<Job> generateFromFile(String filename) throws Exception
    {
        // Declare Scanner to read from the file
        Scanner input;
        input = new Scanner(new File(filename));

        ArrayList<Job> jobs = new ArrayList();

        // Line that has been read
        String line = input.nextLine();
        int noJobs = Integer.valueOf(line);
        while (input.hasNext())
        {
            String id = input.next();
            int size = Integer.valueOf(input.nextLine().substring(1));
            jobs.add(new Job(id, size));
        }
        return jobs;
    }
}
