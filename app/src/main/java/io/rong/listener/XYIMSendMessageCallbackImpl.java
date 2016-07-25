package io.rong.listener;

import com.xiaoying.imapi.XYIMSendMessageCallback;
import com.xiaoying.imapi.api.BusEvent;

import io.rong.util.IMUtil;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * Created by xiyoumc on 16/7/25.
 */
public class XYIMSendMessageCallbackImpl implements XYIMSendMessageCallback {
    private Message msg;

    public XYIMSendMessageCallbackImpl(Message msg) {
        this.msg = msg;
    }

    @Override
    public void onSuccess(Integer integer) {
        IMUtil.getEventBus().post(new BusEvent.MessageSent(msg, 0));
    }

    @Override
    public void onError(Integer messageId, RongIMClient.ErrorCode e) {
        IMUtil.getEventBus().post(new BusEvent.MessageSent(msg, e.getValue()));
    }
}
