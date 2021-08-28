package P2;

public class Job
{
    private String id;
    private int intId;
    private int size;

    public Job(String id_, int size_)
    {
        id = id_;
        intId = Integer.valueOf(id_.substring(1));
        size = size_;
    }

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
}
