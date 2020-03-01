package wr1ttenyu.f1nal.study.project.archetype.model;

import wr1ttenyu.f1nal.study.project.archetype.entity.UUser;

public class UserModel {

    private String id;

    private String name;

    private Integer age;

    public static UUser convertModelToDo(UserModel model) {
        if (model == null) return null;
        UUser user = new UUser();
        user.setId(model.getId());
        user.setAge(model.getAge());
        user.setName(model.getName());
        return user;
    }

    public static UserModel convertDoToModel(UUser entity) {
        if (entity == null) return null;
        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setAge(entity.getAge());
        user.setName(entity.getName());
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
