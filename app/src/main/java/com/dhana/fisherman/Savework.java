package com.dhana.fisherman;

import com.google.firebase.Timestamp;

public class Savework {
    String title;
    String content;
    Timestamp timestamp;

    public Savework() {
    }

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

    public com.google.firebase.Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(com.google.firebase.Timestamp timestamp) {
        this.timestamp = timestamp;
    }

//    public void setTimestamp(com.google.firebase.Timestamp now) {
//    }
}
