package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Comment {
    private String userName;
    private String content;

    public Comment(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
