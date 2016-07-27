package com.xiaoying.imapi;

import com.xiaoying.imapi.model.ErrorCode;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface XYIMConnectCallback {
    public void onTokenIncorrect();

    public abstract void onSuccess(String var1);

    public abstract void onError(ErrorCode var1);
}
