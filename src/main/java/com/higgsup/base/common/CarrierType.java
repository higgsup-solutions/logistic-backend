package com.higgsup.base.common;

public enum CarrierType {

    DOMESTIC( "domestic"),
    INTERNATIONAL( "international"),
    TNT( "TNT"),
    DHL( "DHL");


    private String content;

    CarrierType( String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
