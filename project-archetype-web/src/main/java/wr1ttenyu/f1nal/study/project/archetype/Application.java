package wr1ttenyu.f1nal.study.project.archetype;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "wr1ttenyu.f1nal.study.project.archetype")
@MapperScan("wr1ttenyu.f1nal.study.project.archetype.dao")
@EnableAspectJAutoProxy(exposeProxy = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
