package scm.SmartContactManager.Entities;

import lombok.Builder;


public class message {

    private String content;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public messagetype getType() {
        return type;
    }

    public void setType(messagetype type) {
        this.type = type;
    }

    @Builder.Default
    private messagetype type=messagetype.blue;

}
