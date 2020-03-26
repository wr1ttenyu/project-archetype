package wr1ttenyu.f1nal.study.project.archetype.util.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wr1ttenyu.f1nal.study.project.archetype.util.common.exception.BusinessException;
import wr1ttenyu.f1nal.study.project.archetype.util.common.exception.assertion.BusinessExceptionAssert;

/**
 * <p>业务异常</p>
 *
 */
@Getter
@AllArgsConstructor
public enum BusinessResponseEnum implements BusinessExceptionAssert {

    /**
     * 4***，对应{@link BusinessException}，系统业务处理异常
     */
    USER_NOT_FOUND(4000, "用户不存在");

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

}
