package model;

public class MarkItem implements CalculateMark {
    String name;
    double mark;
    double proportion;

    public MarkItem(String name, double mark, double proportion) {
        this.name = name;
        this.mark = mark;
        this.proportion = proportion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    public double calculateMark() {
        return mark * proportion;
    }

    public String toString() {
        return name + " " + mark + " " + proportion;
    }
}
