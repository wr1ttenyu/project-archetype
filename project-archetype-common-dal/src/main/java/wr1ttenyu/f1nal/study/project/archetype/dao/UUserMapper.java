package wr1ttenyu.f1nal.study.project.archetype.dao;

import wr1ttenyu.f1nal.study.project.archetype.entity.UUser;

import java.util.List;

public interface UUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

    List<UUser> selectByName(String name);
}