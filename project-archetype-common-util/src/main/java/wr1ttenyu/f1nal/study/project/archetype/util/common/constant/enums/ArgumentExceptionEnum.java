package wr1ttenyu.f1nal.study.project.archetype.util.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wr1ttenyu.f1nal.study.project.archetype.util.common.exception.assertion.CommonExceptionAssert;

/**
 * <p>参数校验异常返回结果</p>
 */
@Getter
@AllArgsConstructor
public enum ArgumentExceptionEnum implements CommonExceptionAssert {

    /**
     * 绑定参数校验异常
     */
    VALID_ERROR(6000, "参数校验异常");

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

}
