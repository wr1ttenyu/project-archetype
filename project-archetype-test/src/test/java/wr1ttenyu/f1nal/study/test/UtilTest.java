package wr1ttenyu.f1nal.study.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import wr1ttenyu.f1nal.study.util.UUIDGenerator;


public class UtilTest extends BaseTest {

    @Test
    public void testUUIDGenerator() {
        String generate = UUIDGenerator.generate();
        System.out.println("-----------------------------------------------------------");
        System.out.println(generate);
    }

}
