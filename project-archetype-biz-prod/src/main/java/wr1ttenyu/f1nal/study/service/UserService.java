package wr1ttenyu.f1nal.study.service;

import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;

public interface UserService {

    UserModel getUserById(String id);

    UserModel insertUserRecord(UserModel model);

    UserModel getOrSaveUserByNameAndAge(String name, Integer age);
}
