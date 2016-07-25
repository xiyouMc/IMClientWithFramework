package com.xiaoying.imapi;

import io.rong.imlib.model.Message;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface XYIMOnReceiveMessageListener {
    boolean onReceived(Message var1, int var2);
}
