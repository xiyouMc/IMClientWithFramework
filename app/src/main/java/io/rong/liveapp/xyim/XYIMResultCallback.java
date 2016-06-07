package io.rong.liveapp.xyim;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface XYIMResultCallback<T> {
    public abstract void onSuccess(Message message);
    public abstract void onError(RongIMClient.ErrorCode var1);
}
