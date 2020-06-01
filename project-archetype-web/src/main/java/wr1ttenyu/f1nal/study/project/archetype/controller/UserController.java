package wr1ttenyu.f1nal.study.project.archetype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wr1ttenyu.f1nal.study.project.archetype.model.TokenModel;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.project.archetype.model.request.AddUserRequest;
import wr1ttenyu.f1nal.study.project.archetype.service.UserService;
import wr1ttenyu.f1nal.study.project.archetype.service.mq.UserInfoToMqService;
import wr1ttenyu.f1nal.study.project.archetype.util.common.response.CommonResponse;
import wr1ttenyu.f1nal.study.project.archetype.web.utils.token.TokenManager;
import wr1ttenyu.f1nal.study.project.archetype.web.utils.token.TokenValid;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoToMqService userInfoToMqService;

    @RequestMapping(method = RequestMethod.GET, path = "/getUser")
    public CommonResponse<UserModel> getUserInfoById(@NotNull(message="用户id不能为空") String id) {
        return CommonResponse.successResponse(userService.getUserById(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getToken")
    public CommonResponse<String> getToken(@NotNull(message="用户id不能为空") String id) {
        UserModel user = userService.getUserById(id);
        TokenModel tokenModel = TokenManager.makeToken(user);
        return CommonResponse.successResponse(tokenModel.getToken());
    }


    @RequestMapping(method = RequestMethod.POST, path = "/addUser")
    public CommonResponse<String> addUser(@Valid AddUserRequest request) {
        UserModel userModel = UserModel.convertReqToModel(request);
        userInfoToMqService.sendUserInfoMsg(userModel);
        return CommonResponse.successResponse(userModel.getId());
    }

    @TokenValid
    @RequestMapping(method = RequestMethod.POST, path = "/addUserJson")
    public CommonResponse<UserModel> addUserJson(@Valid @RequestBody AddUserRequest request) {
        UserModel userModel = UserModel.convertReqToModel(request);
        userService.insertUserRecord(userModel);
        return CommonResponse.successResponse(userModel);
    }
}
