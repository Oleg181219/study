package Metro;

public class Station implements Comparable<Station>
{
    private String line;
    private String name;




    public Station(String line, String name)
    {
        this.name = name;
        this.line = line;
    }

    public String getLine()
    {
        return line;
    }

    public String getName()
    {
        return name;
    }


    @Override
    public int compareTo(Station station) {
        if (name.compareTo(station.getName()) == 0 && line.compareTo(station.getLine()) == 0) return 0;
        else return -1;
    }

}