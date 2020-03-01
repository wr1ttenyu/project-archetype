package wr1ttenyu.f1nal.study.project.archetype.service;

import wr1ttenyu.f1nal.study.project.archetype.entity.UUser;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;

public interface UserService {

    UUser getUserById(String id);

    UserModel insertUserRecord(UserModel model);

    UserModel getOrSaveUserByNameAndAge(String name, Integer age);
}
