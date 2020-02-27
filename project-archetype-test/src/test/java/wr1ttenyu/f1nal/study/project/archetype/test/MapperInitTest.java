package wr1ttenyu.f1nal.study.project.archetype.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wr1ttenyu.f1nal.study.project.archetype.Application;
import wr1ttenyu.f1nal.study.project.archetype.dao.UUserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
public class MapperInitTest {

    @Autowired
    private UUserMapper userMapper;

    @Test
    public void testUUserMapper() {
        userMapper.
    }
}
