package com.xiaoying.imapi.api;

import io.rong.imlib.model.UserInfo;

/**
 * Created by xiyoumc on 16/7/23.
 */
public interface UserInfoProvider {
    UserInfo getUserInfo(String userId);
}
