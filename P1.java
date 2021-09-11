/*******************************************************************************
 ****    COMP2240 Assignment 2
 ****    c3308061
 ****    Lachlan Court
 ****    29/08/2021
 ****    This class houses the main run of the program. It creates a number of
 ****    threads equal to the number of WAR's specified in the file, and then
 ****    starts them to run concurrently
 *******************************************************************************/

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class P1
{
    // Create instance of P1 and start
    public static void main(String[] args)
    {
        P1 p = new P1();
        p.run(args);
    }

    /**
     * Main run of the program. Reads data from file, then creates a thread for each WAR and starts them all
     * @param args the command line arguments, a required filename and then an optional max run time (150 by default)
     */
    public void run(String[] args)
    {
        // Initialise max run to 150 unless an additional argument is provided
        int maxRun = 150;
        try
        {
            if (args.length > 1)
            {
                // If another argument is given, assume it is an integer and convert it to a maxRun value
                maxRun = Integer.valueOf(args[1]);
            }
        }
        catch (Exception e)
        {}  // Ignore exception - just run with 150 if the argument is invalid

        P1_Intersection intersection = new P1_Intersection(maxRun);
        ArrayList<P1_WAR> wars = new ArrayList<P1_WAR>();
        ArrayList<Thread> threads = new ArrayList<Thread>();
        // Check that the file is valid, if the read throws an exception, terminate the simulation
        try
        {
            wars = generateFromFile(args[0], intersection);
        }
        catch (Exception e)
        {
            System.err.println("Error reading file");
            System.exit(1);
        }

        // Start a thread for each WAR and pass each thread one of the WARs from the ArrayList
        for (P1_WAR war : wars)
        {
            threads.add(new Thread(war));
        }
        // Start each thread
        for (Thread thread : threads)
        {
            thread.start();
        }
    }

    /**
     * Generates an array of WARs from a file
     * @param filename name of file to be read
     * @param intersection the track Intersection of the simulation
     * @return an array of WARS
     * @throws Exception a file read error either an invalid filename or an empty file
     */
    public ArrayList<P1_WAR> generateFromFile(String filename, P1_Intersection intersection) throws Exception
    {
        // Declare Scanner to read from the file
        Scanner input = new Scanner(new File(filename));

        // Data file contains a single line so read it
        String data;
        if (input.hasNext())
        {
            data = input.nextLine() + " ";  // Loops below use space delimiter
        }
        else
        {
            throw new Exception("Invalid file");
        }

        // Initialise unique ID and new list of WARs
        int warCount = 1;
        ArrayList<P1_WAR> wars = new ArrayList<P1_WAR>();
        // Loop through the line of text read from file
        for (int i = 0; i < data.length() - 2; i++)
        {
            // If the current section of the line refers to North track
            if (data.substring(i, i + 2).equals("N="))
            {
                // Skip the letter and = sign
                i += 2;
                int warNums = 0;
                // In case of a multi-digit number of WARs, read until the next space
                for (int j = i; j < data.length(); j++)
                {
                    if (data.substring(j, j + 1).equals(" "))
                    {
                        warNums = Integer.valueOf(data.substring(i, j));
                        break;
                    }
                }
                // Create the specified number of WARs on that track
                for (int k = 0; k < warNums; k++)
                {
                    wars.add(new P1_WAR(2, true, warCount, intersection));
                    warCount++;
                }
            }
            // If the current section of the line refers to South track
            if (data.substring(i, i + 2).equals("S="))
            {
                // Skip the letter and = sign
                i += 2;
                int warNums = 0;
                // In case of a multi-digit number of WARs, read until the next space
                for (int j = i; j < data.length(); j++)
                {
                    if (data.substring(j, j + 1).equals(" "))
                    {
                        warNums = Integer.valueOf(data.substring(i, j));
                        break;
                    }
                }
                // Create the specified number of WARs on that track
                for (int k = 0; k < warNums; k++)
                {
                    wars.add(new P1_WAR(2, false, warCount, intersection));
                    warCount++;
                }
            }
            // If the current section of the line refers to East track
            if (data.substring(i, i + 2).equals("E="))
            {
                // Skip the letter and = sign
                i += 2;
                int warNums = 0;
                // In case of a multi-digit number of WARs, read until the next space
                for (int j = i; j < data.length(); j++)
                {
                    if (data.substring(j, j + 1).equals(" "))
                    {
                        warNums = Integer.valueOf(data.substring(i, j));
                        break;
                    }
                }
                // Create the specified number of WARs on that track
                for (int k = 0; k < warNums; k++)
                {
                    wars.add(new P1_WAR(1, false, warCount, intersection));
                    warCount++;
                }
            }
            // If the current section of the line refers to West track
            if (data.substring(i, i + 2).equals("W="))
            {
                // Skip the letter and = sign
                i += 2;
                int warNums = 0;
                // In case of a multi-digit number of WARs, read until the next space
                for (int j = i; j < data.length(); j++)
                {
                    if (data.substring(j, j + 1).equals(" "))
                    {
                        warNums = Integer.valueOf(data.substring(i, j));
                        break;
                    }
                }
                // Create the specified number of WARs on that track
                for (int k = 0; k < warNums; k++)
                {
                    wars.add(new P1_WAR(1, true, warCount, intersection));
                    warCount++;
                }
            }
        }
        return wars;
    }
}
