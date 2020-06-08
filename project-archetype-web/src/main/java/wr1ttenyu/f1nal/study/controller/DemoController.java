package wr1ttenyu.f1nal.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wr1ttenyu.f1nal.study.project.archetype.model.SayHiModel;
import wr1ttenyu.f1nal.study.project.archetype.model.TokenModel;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.project.archetype.model.request.AddUserRequest;
import wr1ttenyu.f1nal.study.project.archetype.model.request.SayHiRequest;
import wr1ttenyu.f1nal.study.project.archetype.model.request.SayHiResponse;
import wr1ttenyu.f1nal.study.service.DemoService;
import wr1ttenyu.f1nal.study.util.common.response.CommonResponse;
import wr1ttenyu.f1nal.study.web.utils.token.TokenManager;
import wr1ttenyu.f1nal.study.web.utils.token.TokenValid;

import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/hello")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(method = RequestMethod.POST, path = "/getToken")
    public CommonResponse<String> getToken(@RequestBody AddUserRequest user) {
        TokenModel tokenModel = TokenManager.makeToken(UserModel.convertReqToModel(user));
        return CommonResponse.successResponse(tokenModel.getToken());
    }

    @TokenValid
    @RequestMapping(method = RequestMethod.POST, path = "/sayHi")
    public CommonResponse<SayHiResponse> getUserInfoById(@RequestBody SayHiRequest sayHiRequest, UserModel userModel) {
        SayHiModel sayHiModel = demoService.sayHi(SayHiModel.convertReqToModel(sayHiRequest), userModel);
        return CommonResponse.successResponse(SayHiModel.convertModelToRes(sayHiModel, userModel));
    }
}
