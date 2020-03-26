package wr1ttenyu.f1nal.study.project.archetype.util.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wr1ttenyu.f1nal.study.project.archetype.util.common.constant.enums.CommonResponseEnum;

/**
 * <p>通用返回结果</p>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
@Data
public class CommonResponse<T> extends BaseResponse {
    /**
     * 数据列表
     */
    protected T data;

    public static <T extends Object> CommonResponse<T> constructCommonResponse(T data) {
        CommonResponse response = new CommonResponse();
        response.setCode(CommonResponseEnum.SUCCESS.getCode());
        response.setMessage(CommonResponseEnum.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }
}
