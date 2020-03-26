package wr1ttenyu.f1nal.study.project.archetype.util.common.response;

/**
 * <p>错误返回结果</p>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
public class ErrorResponse extends BaseResponse {

    public ErrorResponse(int code, String message) {
        super(code, message);
    }
}
