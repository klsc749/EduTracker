package model;

import java.util.ArrayList;
import java.util.List;

public class ExtraCurriculum extends Activity{
    List<ExtraCurriculumItem> extraCurriculumItems = new ArrayList<ExtraCurriculumItem>();
    public ExtraCurriculum(){

    }
    public ExtraCurriculum(List<ExtraCurriculumItem> extraCurriculumItems) {
        this.extraCurriculumItems = extraCurriculumItems;
    }
    public List<ExtraCurriculumItem> getExtraCurriculumItems() {
        return extraCurriculumItems;
    }

    public void setExtraCurriculumItems(List<ExtraCurriculumItem> extraCurriculumItems) {
        this.extraCurriculumItems = extraCurriculumItems;
    }

    String content;
    List<String> teammates = new ArrayList<String>();

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
}
