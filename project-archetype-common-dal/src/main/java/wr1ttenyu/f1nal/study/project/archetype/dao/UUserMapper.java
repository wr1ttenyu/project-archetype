package wr1ttenyu.f1nal.study.project.archetype.dao;

import org.apache.ibatis.annotations.Param;
import wr1ttenyu.f1nal.study.project.archetype.entity.UUser;
import wr1ttenyu.f1nal.study.project.archetype.entity.UUserExample;

import java.util.List;

public interface UUserMapper {
    long countByExample(UUserExample example);

    int deleteByExample(UUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(UUser record);

    int insertSelective(UUser record);

    List<UUser> selectByExample(UUserExample example);

    UUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UUser record, @Param("example") UUserExample example);

    int updateByExample(@Param("record") UUser record, @Param("example") UUserExample example);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);
}