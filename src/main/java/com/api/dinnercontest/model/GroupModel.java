package com.api.dinnercontest.model;

import java.time.LocalDateTime;

public class GroupModel {

    private Long groupId;

    private String groupName;

    private LocalDateTime creationDate;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

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
        return "GroupModel{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
