package com.iss.platform.user.impl;

import com.iss.platform.user.entity.UserInfo;
import com.iss.platform.user.service.UserInfoService;
import com.iss.utils.ResponesEntity;
import com.mybatis.common.utils.BeanCopyUtils;
import com.mybatis.common.utils.MD5;
import com.mybatis.core.orm.core.exception.ServiceException;
import com.mybatis.platform.user.entity.User;
import com.mybatis.platform.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserService userService;

    @Override
    public ResponesEntity<UserInfo> findUserInfoByLoginname(String loginname, String password) {
        try {
            User user = userService.login(loginname);
            ResponesEntity<UserInfo> responesEntity = new ResponesEntity<>();
            String encode = MD5.MD5Encode(password.toUpperCase());
            if (user != null && StringUtils.equals(user.getPassword(), encode)) {
                UserInfo userInfo = BeanCopyUtils.copyObject(UserInfo.class, user);
                responesEntity.setData(userInfo);
                responesEntity.setCode(ResponesEntity.ResultCode.SUCCESS);
                responesEntity.setMsg("登录成功");
            } else {
                responesEntity.setCode(ResponesEntity.ResultCode.FAIL);
                responesEntity.setMsg("账户名称或密码不正确");
            }
            return responesEntity;
        } catch (ServiceException e) {
            throw new ServiceException("账户名称或密码不正确", new Throwable());
        }
    }
}
