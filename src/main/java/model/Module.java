package model;

import java.util.ArrayList;
import java.util.List;

public class Module extends Activity{
    Mark mark;
    String degree;
    List<String> skillTags = new ArrayList<String>();

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<String> getSkillTags() {
        return skillTags;
    }

    public void setSkillTags(List<String> skillTags) {
        this.skillTags = skillTags;
    }
}