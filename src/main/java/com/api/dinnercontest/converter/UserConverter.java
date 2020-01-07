package com.api.dinnercontest.converter;

import com.api.dinnercontest.entity.UserEntity;
import com.api.dinnercontest.model.UserModel;

public class UserConverter {

    public static UserModel toModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setUserName(userEntity.getUserName());
        userModel.setAccessName(userEntity.getAccessName());
        userModel.setCreationDate(userEntity.getCreationDate());
        userModel.setEmail(userEntity.getEmail());
        userModel.setLastLogin(userEntity.getLastLogin());
        return userModel;
    }

    public static UserEntity toEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userModel.getUserName());
        userEntity.setAccessName(userModel.getAccessName());
        userEntity.setCreationDate(userModel.getCreationDate());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setLastLogin(userModel.getLastLogin());
        return userEntity;
    }
}
