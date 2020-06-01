package wr1ttenyu.f1nal.study.project.archetype.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wr1ttenyu.f1nal.study.project.archetype.dao.UUserMapper;
import wr1ttenyu.f1nal.study.project.archetype.entity.UUser;
import wr1ttenyu.f1nal.study.project.archetype.service.UserService;
import wr1ttenyu.f1nal.study.project.archetype.util.UUIDGenerator;

public class InitTest extends BaseTest {

    @Autowired
    private UUserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testUUserMapper() {
        UUser user = userMapper.selectByPrimaryKey("1");
        System.out.println(user);
    }

    @Test
    public void testUUserMapperAdd() {
        UUser user = userMapper.selectByPrimaryKey("1");
        user.setId(UUIDGenerator.generate());
        userMapper.insert(user);
    }

    @Test
    public void testUserService() {
        userService.getUserById("100");
    }
}
