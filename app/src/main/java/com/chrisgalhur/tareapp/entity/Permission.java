package com.chrisgalhur.tareapp.entity;

public class Permission {

    private String name;
    private String description;
    private String realName;
    private boolean isGranted;


    public Permission(String name, String description, String realName) {
        this.name = name;
        this.description = description;
        this.realName = realName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRealName() {
        return realName;
    }

    public boolean isGranted() {
        return isGranted;
    }

    public void setGranted(boolean isGranted) {
        this.isGranted = isGranted;
    }
}
