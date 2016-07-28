package com.xiaoying.imcore.liveapp.xyim.rongyun;

import com.xiaoying.imapi.XYConversationType;
import com.xiaoying.imapi.XYIMAbstractClient;
import com.xiaoying.imapi.XYIMConnectCallback;
import com.xiaoying.imapi.XYIMOnReceiveMessageListener;
import com.xiaoying.imapi.XYIMResultCallback;
import com.xiaoying.imapi.XYIMSendMessageCallback;
import com.xiaoying.imapi.message.XYEmoji;
import com.xiaoying.imapi.message.XYMessage;
import com.xiaoying.imapi.message.XYMessageContent;
import com.xiaoying.imapi.message.XYTextMessage;
import com.xiaoying.imapi.model.ErrorCode;

import android.content.Context;
import android.os.Parcel;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

/**
 * Created by Administrator on 2016/6/7.
 */
public class XYIMRongyunClient extends XYIMAbstractClient {

    static XYIMRongyunClient mClient;
    static RongIMClient RongIMClientInstance;
    private Context context;

    public static XYIMRongyunClient getInstance() {
        if (null == mClient) {
            mClient = new XYIMRongyunClient();
        }
        return mClient;
    }

    public XYIMRongyunClient() {
        RongIMClientInstance = RongIMClient.getInstance();
    }

    @Override
    public void init(Context context, String appKey) {
        RongIMClient.init(context, appKey);
        this.context = context;
    }

    @Override
    public void init(Context context) {
        RongIMClient.init(context);
        this.context = context;
    }

    public void connect(String token, final XYIMConnectCallback callback) {
        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {

            @Override
            public void onSuccess(String s) {
                callback.onSuccess(s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                callback.onError(ErrorCode.valueOf(errorCode.getValue()));
            }

            @Override
            public void onTokenIncorrect() {

            }
        });
    }

    @Override
    public void sendMessage(final XYMessage message, String pushContent, String pushData, final XYIMSendMessageCallback sendMessageCallback, final XYIMResultCallback resultCallback) {
        RongIMClient.SendMessageCallback callback = new RongIMClient.SendMessageCallback() {
            @Override
            public void onSuccess(Integer integer) {
                sendMessageCallback.onSuccess(integer);
            }

            @Override
            public void onError(Integer messageId, RongIMClient.ErrorCode e) {
                sendMessageCallback.onError(messageId, ErrorCode.valueOf(e.getValue()));
            }
        };
        RongIMClient.ResultCallback<Message> result = new RongIMClient.ResultCallback<Message>() {
            @Override
            public void onSuccess(final Message message) {
                resultCallback.onSuccess(XYMessage.obtain(message.getTargetId(), XYConversationType.setValue(message.getConversationType().getValue()), new XYMessageContent() {
                    MessageContent mContent = message.getContent();

                    @Override
                    public byte[] encode() {
                        return mContent.encode();
                    }

                    @Override
                    public int describeContents() {
                        return mContent.describeContents();
                    }

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {
                        mContent.writeToParcel(parcel, i);
                    }

                    @Override
                    public String getMessage() {
                        return null;
                    }
                }));
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                resultCallback.onError(ErrorCode.valueOf(e.getValue()));
            }
        };
        RongMessage rongMessage = RongMessage.obtain(message.getContent().getMessage());
        Message msg = Message.obtain(message.getTargetId(), Conversation.ConversationType.setValue(message.getConversationType().getValue()),
                rongMessage);
        RongIMClient.getInstance().sendMessage(msg, null, null, callback, result);
    }

    @Override
    public void logout() {
        RongIMClient.getInstance().logout();
    }

    @Override
    public void setOnReceiveMessageListener(final XYIMOnReceiveMessageListener listener) {
        RongIMClient.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(final Message message, int left) {

                RongMessage textMsg = (RongMessage) message.getContent();
                CharSequence text = XYEmoji.ensure(context, textMsg.getMessage());
                XYTextMessage xyTextMessage = XYTextMessage.obtain(text.toString());
                final XYMessage msg = XYMessage.obtain(message.getTargetId(), XYConversationType.setValue(message.getConversationType().getValue()), xyTextMessage);
                msg.setSenderUserId(message.getSenderUserId());
                msg.setExtra(message.getExtra());
                msg.setMessageDirection(XYMessage.MessageDirection.setValue(message.getMessageDirection().getValue()));
                msg.setMessageId(message.getMessageId());
                msg.setObjectName(message.getObjectName());
                msg.setReceivedTime(message.getReceivedTime());
                msg.setReceivedStatus(new XYMessage.ReceivedStatus(message.getReceivedStatus().getFlag()));
                msg.setSentStatus(XYMessage.SentStatus.setValue(message.getSentStatus().getValue()));
                msg.setSentTime(message.getSentTime());
                msg.setUId(message.getUId());
                msg.setTargetId(message.getTargetId());
                listener.onReceived(msg, left);
                return false;
            }
        });
    }
}
