package com.xiaoying.imapi;

import com.xiaoying.imapi.message.XYMessage;
import com.xiaoying.imapi.model.ErrorCode;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface XYIMResultCallback<T> {
    public abstract void onSuccess(XYMessage message);

    public abstract void onError(ErrorCode var1);
}
