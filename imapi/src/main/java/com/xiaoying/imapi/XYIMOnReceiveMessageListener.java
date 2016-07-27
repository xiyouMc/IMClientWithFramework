package com.xiaoying.imapi;

import com.xiaoying.imapi.message.XYMessage;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface XYIMOnReceiveMessageListener {
    boolean onReceived(XYMessage var1, int var2);
}
