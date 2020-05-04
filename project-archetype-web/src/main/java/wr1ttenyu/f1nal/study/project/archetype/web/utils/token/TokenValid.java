package wr1ttenyu.f1nal.study.project.archetype.web.utils.token;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenValid {
}