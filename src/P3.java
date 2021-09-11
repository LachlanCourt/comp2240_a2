/*******************************************************************************
 ****    COMP2240 Assignment 2
 ****    c3308061
 ****    Lachlan Court
 ****    29/08/2021
 ****    This class houses the main run of the program. It creates 3 threads to
 ****    simulate three concurrently running print heads, and starts them all
 *******************************************************************************/

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class P3
{
    // Create instance of P3 and start
    public static void main(String[] args)
    {
        P3 p = new P3();
        p.run(args);
    }

    /**
     * Main run of the program. Manages reading data from file, then creates three print heads and starts them all
     * @param args the command line argument, a required filename
     */
    public void run(String[] args)
    {

        ArrayList<P2_P3_Job> jobs = new ArrayList<P2_P3_Job>();
        ArrayList<Thread> threads = new ArrayList<Thread>();

        // Check that the file is valid, if the read throws an exception, terminate the simulation
        try
        {
            jobs = generateFromFile(args[0]);
        }
        catch (Exception e)
        {
            System.err.println("Error reading file");
            System.exit(1);
        }

        // Create a printer to manage the simulation
        P3_Printer printer = new P3_Printer(jobs);
        // Create a list of three printheads
        ArrayList<P3_PrintHead> printHeads = new ArrayList<P3_PrintHead>();
        for (int i = 0; i < 3; i++)
        {
            // Create each print head with a unique id and add it to both the printHeads list and the list of threads
            P3_PrintHead temp = new P3_PrintHead(printer, i + 1);
            threads.add(new Thread(temp));
            printHeads.add(temp);
        }
        // Make the printer aware of its printheads
        printer.setPrintHeads(printHeads);
        // Start threads
        for (Thread thread : threads)
        {
            thread.start();
        }
        // Start the printer to track global time in the main thread
        printer.start();
    }

    /**
     * Generates Jobs from a file
     * @param filename name of file to be read
     * @return an array of Jobs
     * @throws Exception a file read error either an invalid filename or an empty file
     */
    public ArrayList<P2_P3_Job> generateFromFile(String filename) throws Exception
    {
        // Declare Scanner to read from the file
        Scanner input;
        input = new Scanner(new File(filename));

        ArrayList<P2_P3_Job> jobs = new ArrayList<P2_P3_Job>();

        // First line indicates the number of jobs which I then proceed to blatantly ignore
        String line = input.nextLine();
        // Loop while there are still lines in the file
        while (input.hasNext())
        {
            // Create a new Job and add it to the list
            String id = input.next();
            int size = Integer.valueOf(input.nextLine().substring(1));
            jobs.add(new P2_P3_Job(id, size));
        }
        return jobs;
    }
}
