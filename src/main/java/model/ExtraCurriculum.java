package model;

import java.util.ArrayList;
import java.util.List;

public class ExtraCurriculum extends Activity{
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
