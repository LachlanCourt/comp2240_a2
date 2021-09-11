/*******************************************************************************
 ****    COMP2240 Assignment 2
 ****    c3308061
 ****    Lachlan Court
 ****    29/08/2021
 ****    This class represents a Job to be undertaken by the printer. It has
 ****    member variables to refer to the size of the job and a unique ID, and
 ****    whether it is monochrome or colour
 *******************************************************************************/

package P2;

public class Job
{
    private String id;
    private int intId;
    private int size;
    private String type;

    // Constructor
    public Job(String id_, int size_)
    {
        id = id_;
        intId = Integer.valueOf(id_.substring(1));
        size = size_;
        type = id_.substring(0, 1);
    }

    // Getters
    public String getId()
    {
        return id;
    }

    public int getIntId()
    {
        return intId;
    }

    public int getSize()
    {
        return size;
    }

    public String getType()
    {
        return type;
    }
}
