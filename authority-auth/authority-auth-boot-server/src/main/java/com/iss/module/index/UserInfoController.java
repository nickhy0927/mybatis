package com.iss.module.index;

import com.core.utils.JSONResult;
import com.iss.module.platform.user.pojo.User;
import com.iss.module.platform.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "用户管理接口")
@RestController
public class UserInfoController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询用户列表", notes = "查询所有的用户信息")
    @RequestMapping(value = "/user/query-user-list.json", method = RequestMethod.GET)
    public JSONResult queryUserList(@ApiParam(value = "用户主键ID") String id) {
        List<User> users = userService.queryPageByMap();
        return JSONResult.success(users);
    }

    @ApiOperation(value = "根据用户ID查询用户列表", notes = "根据用户ID查询用户列表")
    @RequestMapping(value = "/user/get-user-list.json", method = RequestMethod.GET)
    public List<User> getUserList(@ApiParam(value = "用户主键ID") String id) {
        List<User> users = userService.queryPageByMap();
        return users;
    }
}
