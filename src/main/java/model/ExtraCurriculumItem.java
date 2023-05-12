package model;

public class ExtraCurriculumItem extends ExtraCurriculum{
    String name;

    String content;

    public ExtraCurriculumItem(String name, String content) {
        this.name = name;
        this.content = content;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return name + " " + content;
    }
}
