package com.xiaoying.imapi;

import android.content.Context;

import io.rong.imlib.model.Message;

/**
 * Created by Administrator on 2016/6/7.
 */
public abstract class XYIMAbstractClient {
    abstract public void init(Context context, String appKey);
    abstract public void init(Context context);
    abstract public void logout();
    abstract public void connect(String token, XYIMConnectCallback callback);
    abstract public void sendMessage(Message message, String pushContent, String pushData, XYIMSendMessageCallback callback, XYIMResultCallback resultCallback);
    abstract public void setOnReceiveMessageListener(XYIMOnReceiveMessageListener listener);
}
