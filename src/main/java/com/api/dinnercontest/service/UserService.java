package com.api.dinnercontest.service;

import com.api.dinnercontest.converter.UserConverter;
import com.api.dinnercontest.model.UserGroupModel;
import com.api.dinnercontest.model.UserModel;
import com.api.dinnercontest.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel getUser(Long id) {
        return UserConverter.toModel(userRepository.findById(id));
    }

    public void save(UserModel userModel) {
        userModel.setPassword(BCrypt.hashpw(userModel.getPassword(), BCrypt.gensalt(10)));
        userRepository.save(UserConverter.toEntity(userModel));
    }

    public void joinGroup(UserGroupModel userGroupModel) {
        userRepository.join(userGroupModel);
    }

    public boolean isUsed(String user) {
        return userRepository.isUsed(user) > 0;
    }
}
