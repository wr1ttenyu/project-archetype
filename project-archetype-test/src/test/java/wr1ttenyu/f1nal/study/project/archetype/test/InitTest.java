package wr1ttenyu.f1nal.study.project.archetype.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wr1ttenyu.f1nal.study.project.archetype.Application;
import wr1ttenyu.f1nal.study.project.archetype.dao.UUserMapper;
import wr1ttenyu.f1nal.study.project.archetype.entity.UUser;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.project.archetype.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
public class InitTest {

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
        UserModel user = userService.getOrSaveUserByNameAndAge("二憨子", 28);
        Assert.assertNotNull(user);
    }
}
