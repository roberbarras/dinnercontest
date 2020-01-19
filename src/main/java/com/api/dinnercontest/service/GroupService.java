package com.api.dinnercontest.service;

import com.api.dinnercontest.model.GroupModel;
import com.api.dinnercontest.model.UserModel;
import com.api.dinnercontest.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupModel getGroup(Long id) {
        return groupRepository.findById(id);
    }

    public List<UserModel> getGroupUsers(Long id) {
        return groupRepository.findByIdWithUsers(id);
    }

    public void save(GroupModel groupModel) {
        groupRepository.save(groupModel.getGroupName());
    }

}
