package P1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class P1
{
    public static void main(String[] args)
    {
        P1 p = new P1();
        p.run(args);
    }

    public void run(String[] args)
    {
        // Check that the file is valid, if the read throws an exception, terminate the simulation
        ArrayList<WAR> wars;
        try
        {
            wars = readData(args[0]);
        }
        catch (Exception e)
        {
            System.err.println("Error reading file");
            System.exit(1);
        }
    }

    public ArrayList<WAR> readData(String filename) throws Exception
    {
        // Declare Scanner to read from the file
        Scanner input;
        input = new Scanner(new File(filename));

        // Line that has been read
        String data;
        if (input.hasNext())
        {
            data = input.nextLine() + " ";
        }
        else
        {
            throw new Exception("Invalid file");
        }

        ArrayList<WAR> wars = new ArrayList<WAR>();
        for (int i = 0; i < data.length() - 2; i++)
        {
            if (data.substring(i, i + 2).equals("N="))
            {
                i += 2;
                int warNums = 0;
                for (int j = i; j < data.length(); j++)
                {
                    if (data.substring(j, j + 1).equals(" "))
                    {
                        warNums = Integer.valueOf(data.substring(i, j));
                        break;
                    }
                }
                for (int k = 0; k < warNums; k++)
                {
                    wars.add(new WAR(2, true));
                }
            }
            if (data.substring(i, i + 2).equals("S="))
            {
                i += 2;
                int warNums = 0;
                for (int j = i; j < data.length(); j++)
                {
                    if (data.substring(j, j + 1).equals(" "))
                    {
                        warNums = Integer.valueOf(data.substring(i, j));
                        break;
                    }
                }
                for (int k = 0; k < warNums; k++)
                {
                    wars.add(new WAR(2, false));
                }
            }
            if (data.substring(i, i + 2).equals("E="))
            {
                i += 2;
                int warNums = 0;
                for (int j = i; j < data.length(); j++)
                {
                    if (data.substring(j, j + 1).equals(" "))
                    {
                        warNums = Integer.valueOf(data.substring(i, j));
                        break;
                    }
                }
                for (int k = 0; k < warNums; k++)
                {
                    wars.add(new WAR(1, false));
                }
            }
            if (data.substring(i, i + 2).equals("W="))
            {
                i += 2;
                int warNums = 0;
                for (int j = i; j < data.length(); j++)
                {
                    if (data.substring(j, j + 1).equals(" "))
                    {
                        warNums = Integer.valueOf(data.substring(i, j));
                        break;
                    }
                }
                for (int k = 0; k < warNums; k++)
                {
                    wars.add(new WAR(1, true));
                }
            }
        }
        return wars;
    }
}
