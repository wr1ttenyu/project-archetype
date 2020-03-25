package wr1ttenyu.f1nal.study.project.archetype.util.common.exception.response;

import lombok.Data;
import wr1ttenyu.f1nal.study.project.archetype.util.common.constant.enums.CommonResponseEnum;
import wr1ttenyu.f1nal.study.project.archetype.util.common.exception.IResponseEnum;

/**
 * <p>基础返回结果</p>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
@Data
public class BaseResponse {
    /**
     * 返回码
     */
    protected int code;
    /**
     * 返回消息
     */
    protected String message;

    public BaseResponse() {
        // 默认创建成功的回应
        this(CommonResponseEnum.SUCCESS);
    }

    public BaseResponse(IResponseEnum responseEnum) {
        this(responseEnum.getCode(), responseEnum.getMessage());
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
