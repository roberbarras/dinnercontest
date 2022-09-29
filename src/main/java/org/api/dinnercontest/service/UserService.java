package org.api.dinnercontest.service;

import org.api.dinnercontest.model.UserGroupModel;
import org.api.dinnercontest.model.UserModel;
import org.api.dinnercontest.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel getUser(Long id) {
        return userRepository.findById(id);
    }

    public void save(UserModel userModel) {
        userModel.setPassword(BCrypt.hashpw(userModel.getPassword(), BCrypt.gensalt(10)));
        userRepository.save(userModel);
    }

    public void joinGroup(UserGroupModel userGroupModel, long user) {
        userRepository.join(userGroupModel, user);
    }

    public boolean isUsed(String user) {
        return userRepository.isUsed(user) > 0;
    }

    public void disjoinGroup(UserGroupModel userGroupModel, long user) {
        userRepository.disjoin(userGroupModel, user);
    }
}