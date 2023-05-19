package model;

public class Awards {
    private String time;
    private String content;

    public Awards(String time, String content) {
        this.time = time;
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }
}
