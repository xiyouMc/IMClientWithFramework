package com.xiaoying.imcore.liveapp.xyim.rongyun;

import android.content.Context;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import com.xiaoying.imapi.XYIMAbstractClient;
import com.xiaoying.imapi.XYIMConnectCallback;
import com.xiaoying.imapi.XYIMOnReceiveMessageListener;
import com.xiaoying.imapi.XYIMResultCallback;
import com.xiaoying.imapi.XYIMSendMessageCallback;

/**
 * Created by Administrator on 2016/6/7.
 */
public class XYIMRongyunClient extends XYIMAbstractClient {

    static XYIMRongyunClient mClient;
    static RongIMClient RongIMClientInstance;

    public static XYIMRongyunClient getInstance() {
        if(null == mClient){
            mClient = new XYIMRongyunClient();
        }
        return mClient;
    }

    public XYIMRongyunClient(){
        RongIMClientInstance = RongIMClient.getInstance();
    }

    @Override
    public void init(Context context, String appKey) {
        RongIMClient.init(context, appKey);
    }

    @Override
    public void init(Context context) {
        RongIMClient.init(context);
    }

    public void connect(String token, final XYIMConnectCallback callback) {
        RongIMClient.connect(token, new RongIMClient.ConnectCallback(){

            @Override
            public void onSuccess(String s) {
                callback.onSuccess(s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                callback.onError(errorCode);
            }

            @Override
            public void onTokenIncorrect() {

            }
        });
    }

    @Override
    public void sendMessage(Message message, String pushContent, String pushData, final XYIMSendMessageCallback sendMessageCallback, final XYIMResultCallback resultCallback) {
        RongIMClient.SendMessageCallback callback = new RongIMClient.SendMessageCallback() {
            @Override
            public void onSuccess(Integer integer) {
                sendMessageCallback.onSuccess(integer);
            }

            @Override
            public void onError(Integer messageId, RongIMClient.ErrorCode e) {
                sendMessageCallback.onError(messageId, e);
            }
        };
        RongIMClient.ResultCallback<Message> result = new RongIMClient.ResultCallback<Message>() {
            @Override
            public void onSuccess(Message message) {
                resultCallback.onSuccess(message);
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                resultCallback.onError(e);
            }
        };
        RongIMClient.getInstance().sendMessage(message, null, null, callback, result);
    }

    @Override
    public void logout() {
        RongIMClient.getInstance().logout();
    }

    @Override
    public void setOnReceiveMessageListener(final XYIMOnReceiveMessageListener listener) {
        RongIMClient.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int left) {
                listener.onReceived(message, left);
                return false;
            }
        });
    }
}
