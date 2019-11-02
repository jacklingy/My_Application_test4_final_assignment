package edu.ncu.myapplication_test4_final_assignment;

public class Message {

    private String content;
    private int avatar;

    public Message(String content, int avatar) {
        this.content = content;
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
