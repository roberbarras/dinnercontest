package com.api.dinnercontest.converter;

import com.api.dinnercontest.entity.GroupEntity;
import com.api.dinnercontest.model.GroupModel;

public class GroupConverter {

    public static GroupModel toModel(GroupEntity groupEntity) {
        GroupModel groupModel = new GroupModel();
        groupModel.setName(groupEntity.getGroupName());
        return groupModel;
    }

    public static GroupEntity toEntity(GroupModel groupModel) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(groupModel.getName());
        return groupEntity;
    }
}
