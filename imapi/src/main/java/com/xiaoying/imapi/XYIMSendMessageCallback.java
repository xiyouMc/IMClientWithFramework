package com.xiaoying.imapi;

import com.xiaoying.imapi.model.ErrorCode;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface XYIMSendMessageCallback {
    public void onSuccess(Integer integer);

    public void onError(Integer messageId, ErrorCode e);
}
