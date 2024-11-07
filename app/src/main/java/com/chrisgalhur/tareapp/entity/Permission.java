package com.chrisgalhur.tareapp.entity;

public class Permission {

    String name;
    String description;
    String realName;

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
}
