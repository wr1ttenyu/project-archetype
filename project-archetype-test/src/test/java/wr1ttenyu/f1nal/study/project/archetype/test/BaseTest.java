package wr1ttenyu.f1nal.study.project.archetype.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wr1ttenyu.f1nal.study.project.archetype.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
public abstract class BaseTest {
}
