package com.api.dinnercontest.model;

public class GroupModel {

    private String groupName;

    public String getName() {
        return groupName;
    }

    public void setName(String name) {
        this.groupName = name;
    }

    @Override
    public String toString() {
        return "GroupModel { " +
                "groupName: " + groupName +
                " }";
    }
}
