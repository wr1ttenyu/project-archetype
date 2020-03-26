package wr1ttenyu.f1nal.study.project.archetype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.project.archetype.service.UserService;
import wr1ttenyu.f1nal.study.project.archetype.util.common.response.CommonResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, path = "/getUser")
    public CommonResponse<UserModel> getUserInfoById(String id) {
        return CommonResponse.successResponse(userService.getUserById(id));
    }
}
