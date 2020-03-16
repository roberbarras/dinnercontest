package com.api.dinnercontest.service;

import com.api.dinnercontest.model.CategoryModel;
import com.api.dinnercontest.model.GroupCategoryModel;
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

    public void saveCategory(GroupCategoryModel groupCategoryModel) {
        groupRepository.saveCategory(groupCategoryModel);
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


}
