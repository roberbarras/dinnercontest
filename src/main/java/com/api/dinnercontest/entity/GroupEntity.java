package com.api.dinnercontest.entity;

public class GroupEntity {

    private Long groupId;

    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "GroupEntity { " +
                "groupId: " + groupId +
                ", groupName: " + groupName +
                " }";
    }
}
