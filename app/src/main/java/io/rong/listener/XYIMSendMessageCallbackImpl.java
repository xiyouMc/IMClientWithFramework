package io.rong.listener;

import com.xiaoying.imapi.XYIMSendMessageCallback;
import com.xiaoying.imapi.api.BusEvent;
import com.xiaoying.imapi.message.XYMessage;
import com.xiaoying.imapi.model.ErrorCode;

import io.rong.util.IMUtil;

/**
 * Created by xiyoumc on 16/7/25.
 */
public class XYIMSendMessageCallbackImpl implements XYIMSendMessageCallback {
    private XYMessage msg;

    public XYIMSendMessageCallbackImpl(XYMessage msg) {
        this.msg = msg;
    }

    @Override
    public void onSuccess(Integer integer) {
        IMUtil.getEventBus().post(new BusEvent.MessageSent(msg, 0));
    }

    @Override
    public void onError(Integer messageId, ErrorCode e) {
        IMUtil.getEventBus().post(new BusEvent.MessageSent(msg, e.getValue()));
    }
}
