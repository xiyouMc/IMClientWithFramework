package io.rong.liveapp.xyim;

import io.rong.imlib.RongIMClient;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface XYIMConnectCallback {
    public void onTokenIncorrect();
    public abstract void onSuccess(String var1);
    public abstract void onError(RongIMClient.ErrorCode var1);
}
