package com.xiaoying.imapi.api;


import com.xiaoying.imapi.XYIMUserInfo;

/**
 * Created by xiyoumc on 16/7/23.
 */
public interface UserInfoProvider {
    XYIMUserInfo getUserInfo(String userId);
}
