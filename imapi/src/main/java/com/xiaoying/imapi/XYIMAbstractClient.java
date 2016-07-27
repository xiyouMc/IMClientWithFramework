package com.xiaoying.imapi;

import com.xiaoying.imapi.message.XYMessage;

import android.content.Context;

/**
 * Created by Administrator on 2016/6/7.
 */
public abstract class XYIMAbstractClient {
    abstract public void init(Context context, String appKey);

    abstract public void init(Context context);

    abstract public void logout();

    abstract public void connect(String token, XYIMConnectCallback callback);

    abstract public void sendMessage(XYMessage message, String pushContent, String pushData, XYIMSendMessageCallback callback, XYIMResultCallback resultCallback);

    abstract public void setOnReceiveMessageListener(XYIMOnReceiveMessageListener listener);
}
