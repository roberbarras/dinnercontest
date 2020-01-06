package com.api.dinnercontest.converter;

import com.api.dinnercontest.entity.UserEntity;
import com.api.dinnercontest.model.UserModel;

public class UserConverter {

    public static UserModel toModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setUserName(userEntity.getUserName());
        userModel.setAccessName(userEntity.getAccessName());
        return userModel;
    }

    public static UserEntity toEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userModel.getUserName());
        userEntity.setAccessName(userModel.getAccessName());
        return userEntity;
    }
}
