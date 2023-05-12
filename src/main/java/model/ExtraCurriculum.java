package model;

import java.util.ArrayList;
import java.util.List;

public class ExtraCurriculum extends Activity{
    List<ExtraCurriculum> extraCurriculumitems = new ArrayList<>();
    public ExtraCurriculum(String name, String content){
        this.name = name;
        this.content = content;
    }
    public ExtraCurriculum(){

    }
    /*
    public ExtraCurriculum(List<ExtraCurriculum> extraCurriculumItems) {
        this.extraCurriculumitems = extraCurriculumitems;
    }*/
    public List<ExtraCurriculum> getExtraCurriculumItems() {
        return extraCurriculumitems;
    }

    public void setExtraCurriculumItems(List<ExtraCurriculum> extraCurriculumitems) {
        this.extraCurriculumitems = extraCurriculumitems;
    }

    String content;
    List<String> teammates = new ArrayList<>();
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTeammates() {
        return teammates;
    }

    public void setTeammates(List<String> teammates) {
        this.teammates = teammates;
    }

    public String toString() {
        return name + " " + content;
    }
}
