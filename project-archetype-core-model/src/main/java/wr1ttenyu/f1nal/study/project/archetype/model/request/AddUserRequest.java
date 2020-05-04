package wr1ttenyu.f1nal.study.project.archetype.model.request;


import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AddUserRequest {

    @NotNull
    private String name;

    @NotNull
    @DecimalMin(value = "0")
    private Integer age;

    @JSONField(format = "yyyy-MM-dd")
    private LocalDate birthday;

    private Boolean goodMan;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGoodMan() {
        return goodMan;
    }

    public void setGoodMan(Boolean goodMan) {
        this.goodMan = goodMan;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
