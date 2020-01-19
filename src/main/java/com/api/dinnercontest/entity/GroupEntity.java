package com.api.dinnercontest.entity;

import java.time.LocalDateTime;

public class GroupEntity {

    private Long groupId;

    private String groupName;

    private LocalDateTime creationDate;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "GroupEntity { " +
                "groupId: " + groupId +
                ", groupName: " + groupName +
                " }";
    }
}
