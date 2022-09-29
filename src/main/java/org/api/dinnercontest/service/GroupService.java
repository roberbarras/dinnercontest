package org.api.dinnercontest.service;

import org.api.dinnercontest.model.CategoryModel;
import org.api.dinnercontest.model.GroupModel;
import org.api.dinnercontest.model.UserModel;
import org.api.dinnercontest.repository.GroupRepository;
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

    public List<Long> getIdCategories(Long group) {
        return groupRepository.getIdCategories(group);
    }

    public List<CategoryModel> getCategories(Long group) {
        return groupRepository.getCategories(group);
    }

    public boolean checkUserGroup(Long group, Long user) {
        return groupRepository.checkUser(group, user);
    }


    public List<GroupModel> getGroupUser(Long user) {
        return groupRepository.getGroupUser(user);
    }
}