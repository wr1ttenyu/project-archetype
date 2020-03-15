package wr1ttenyu.f1nal.study.project.archetype.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wr1ttenyu.f1nal.study.project.archetype.dao.UUserMapper;
import wr1ttenyu.f1nal.study.project.archetype.entity.UUser;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.project.archetype.service.UserService;

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
    public void testUserService() {
        UserModel user = userService.getOrSaveUserByNameAndAge("二憨子6", 15);
        Assert.assertNotNull(user);
    }
}
