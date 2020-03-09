package wr1ttenyu.f1nal.study.project.archetype.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import wr1ttenyu.f1nal.study.project.archetype.dao.UUserMapper;
import wr1ttenyu.f1nal.study.project.archetype.entity.UUser;
import wr1ttenyu.f1nal.study.project.archetype.entity.UUserExample;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.project.archetype.service.UserService;
import wr1ttenyu.f1nal.study.project.archetype.util.UUIDGenerator;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UUserMapper userMapper;

    @Override
    public UUser getUserById(String id) {
        if (StringUtils.isEmpty(id)) return null;
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserModel insertUserRecord(UserModel model) {
        logger.info("新增用户，model:{}", model);
        if (model == null) return null;
        if (StringUtils.isEmpty(model.getId())) {
            model.setId(UUIDGenerator.generate());
        }
        userMapper.insert(UserModel.convertModelToDo(model));
        if(Integer.valueOf(15).equals(model.getAge())) {
            throw new RuntimeException("test");
        }
        return model;
    }

    @Override
    @Transactional
    public UserModel getOrSaveUserByNameAndAge(String name, Integer age) {
        UUserExample example = new UUserExample();
        example.createCriteria().andNameEqualTo(name);
        List<UUser> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            UUser user = new UUser();
            user.setId(UUIDGenerator.generate());
            user.setName(name);
            user.setAge(age);
            userMapper.insert(user);
        }

        UserModel user = new UserModel();
        user.setId(UUIDGenerator.generate());
        user.setName("test1");
        user.setAge(15);
        insertUserRecord(user);

        users = userMapper.selectByExample(example);
        return UserModel.convertDoToModel(users.get(0));
    }
}
