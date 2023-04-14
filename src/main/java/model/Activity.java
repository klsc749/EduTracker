package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity {

    String id;
    String name;
    Date startDate;
    Date endDate;
    ActivityType type;
    List<String> directors = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    @Override
    public String toString(){
        return "Activity [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", type=" + type + ", directors=" + directors + "]";
    }

    public enum ActivityType{
        EXTRA, CLASS;
    }
}
