package com.iss.platform.user.impl;

import com.iss.platform.user.entity.UserInfo;
import com.iss.platform.user.service.UserInfoService;
import com.iss.utils.ResponesEntity;
import com.mybatis.common.utils.BeanCopyUtils;
import com.mybatis.core.orm.core.exception.ServiceException;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserService userService;

    @Override
    public ResponesEntity<UserInfo> findUserInfoByLoginname(String loginname) {
        ResponesEntity<UserInfo> responesEntity = new ResponesEntity<>();
        try {
            User user = userService.login(loginname);
            if (user != null) {
                UserInfo userInfo = BeanCopyUtils.copyObject(UserInfo.class, user);
                responesEntity.setData(userInfo);
                responesEntity.setCode(ResponesEntity.ResultCode.SUCCESS);
                responesEntity.setMsg("登录成功");
            } else {
                responesEntity.setCode(ResponesEntity.ResultCode.FAIL);
                responesEntity.setMsg(loginname + "账户不存在");
            }
        } catch (ServiceException e) {
            responesEntity.setCode(ResponesEntity.ResultCode.FAIL);
            responesEntity.setMsg(loginname + "账户不存在");
            throw new ServiceException(loginname + "账户登录失败", new Throwable());
        }
        return responesEntity;

    }
}
