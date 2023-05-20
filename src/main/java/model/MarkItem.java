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

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MarkItem))
            return false;
        MarkItem markItem = (MarkItem) o;
        return markItem.name.equals(name) && markItem.mark == mark && markItem.proportion == proportion;
    }
}
