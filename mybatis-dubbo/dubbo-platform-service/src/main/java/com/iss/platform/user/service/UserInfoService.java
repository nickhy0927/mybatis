package com.iss.platform.user.service;

import com.iss.platform.user.entity.UserInfo;
import com.iss.utils.ResponesEntity;

public interface UserInfoService {

    ResponesEntity<UserInfo> findUserInfoByLoginname(String loginname);
}
