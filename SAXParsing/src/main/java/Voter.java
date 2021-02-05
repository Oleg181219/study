public class Voter {
    String name;
    String birthDay;
    String station;
    String time;

    public Voter(String name, String birthDay, String station, String time) {
        this.name = name;
        this.birthDay = birthDay;
        this.station = station;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
