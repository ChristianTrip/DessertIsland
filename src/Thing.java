public class Thing {

    private String description;
    private int timesChosen;


    Thing(String name) {
        this.description = name;

    }

    public String toStringResults(){
        return this.description +
                "\nIt's been chosen " + this.timesChosen + " times";
    }

    public String getDescription(){
        return this.description;
    }

    public int getTimesChosen() {
        return timesChosen;
    }

    public void setTimesChosen(int timesChosen){
        this.timesChosen = timesChosen;
    }

    public void addTimesChosen() {
        this.timesChosen ++;
    }

}
