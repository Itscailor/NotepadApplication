package com.example.appuntino;

public class firebasemodel {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public firebasemodel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public firebasemodel(){

    }
}


