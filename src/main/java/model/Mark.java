package model;

import java.util.ArrayList;
import java.util.List;

public class Mark implements CalculateMark {
    List<MarkItem> markItems = new ArrayList<MarkItem>();

    public Mark(List<MarkItem> markItems) {
        this.markItems = markItems;
    }

    public List<MarkItem> getMarkItems() {
        return markItems;
    }

    double totalMark;

    public void setMarkItems(List<MarkItem> markItems) {
        this.markItems = markItems;
    }

    public double getTotalMark() {
        totalMark = calculateMark();
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    public double calculateMark() {
        double total = 0;
        for (MarkItem markItem : markItems) {
            total += markItem.calculateMark();
        }
        return total;
    }
}
