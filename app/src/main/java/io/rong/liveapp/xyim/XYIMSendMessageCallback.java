package io.rong.liveapp.xyim;

import io.rong.imlib.RongIMClient;
import io.rong.livekit.model.BusEvent;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface XYIMSendMessageCallback {
    public void onSuccess(Integer integer);
    public void onError(Integer messageId, RongIMClient.ErrorCode e);
}
