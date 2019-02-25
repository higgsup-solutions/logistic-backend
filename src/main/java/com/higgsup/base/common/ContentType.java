package com.higgsup.base.common;

public enum ContentType {

    Documents("Documents"),

    Parcel("Parcel");

    private String content;

    ContentType( String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
