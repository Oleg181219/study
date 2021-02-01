package Metro;

public class Line implements Comparable<Line>{
    private String number;
    private String name;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Line(String number, String name){
        this.number = number;
        this.name = name;

    }

    @Override
    public int compareTo(Line line) {
        return number.compareTo(line.getNumber());
    }

}
