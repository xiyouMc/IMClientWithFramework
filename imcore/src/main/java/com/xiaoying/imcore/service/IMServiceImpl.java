package com.xiaoying.imcore.service;

import com.xiaoying.imapi.BaseMessageTemplate;
import com.xiaoying.imapi.XYIMConnectCallback;
import com.xiaoying.imapi.XYIMOnReceiveMessageListener;
import com.xiaoying.imapi.XYIMResultCallback;
import com.xiaoying.imapi.XYIMSendMessageCallback;
import com.xiaoying.imapi.XYOperationCallback;
import com.xiaoying.imapi.api.UserInfoProvider;
import com.xiaoying.imapi.service.IMService;
import com.xiaoying.imcore.livekit.RongIM;

import android.content.Context;
import android.util.Log;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * Created by xiyoumc on 16/7/23.
 */
public class IMServiceImpl implements IMService {
    @Override
    public void init(Context context) {
        RongIM.init(context);
    }

    @Override
    public void init(Context context, String appKey) {
        RongIM.init(context, appKey);
    }

    @Override
    public void connect(String token, XYIMConnectCallback callback) {
        RongIM.getInstance().connect(token, callback);
    }

    @Override
    public void logout() {
        RongIM.getInstance().logout();
    }

    @Override
    public void startConversation(Context context, Conversation.ConversationType conversationType, String targetId, String liveUrl) {
        RongIM.getInstance().startConversation(context, conversationType, targetId, liveUrl);
    }

    @Override
    public void registerMessageType(Class<? extends MessageContent> messageContentClass) {
        RongIM.getInstance().registerMessageType(messageContentClass);
    }

    @Override
    public void registerMessageTemplate(BaseMessageTemplate template) {
        RongIM.getInstance().registerMessageTemplate(template);
    }

    @Override
    public BaseMessageTemplate getMessageTemplate(Class<? extends MessageContent> messageContent) {
        return RongIM.getInstance().getMessageTemplate(messageContent);
    }

    @Override
    public void setUserInfoProvider(UserInfoProvider provider) {
        RongIM.getInstance().setUserInfoProvider(provider);
    }

    @Override
    public UserInfo getCurrentUserInfo() {
        return RongIM.getInstance().getCurrentUserInfo();
    }

    @Override
    public void sendMessage(Message msg, XYIMSendMessageCallback callback, XYIMResultCallback<Message> result) {
        RongIM.getInstance().sendMessage(msg, callback, result);
    }

    @Override
    public void registerMessageEvent(XYIMOnReceiveMessageListener listener) {
        RongIM.getInstance().registerMessageEvent(listener);
    }

    @Override
    public void setCurrentUserInfo(UserInfo userInfo) {
        RongIM.getInstance().setCurrentUserInfo(userInfo);
    }

    @Override
    public void joinChatRoom(String chatroomId, int defMessageCount, final XYOperationCallback callback) {
        RongIMClient.getInstance().joinChatRoom(chatroomId, defMessageCount, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d("IMService", errorCode.getMessage());
//                callback.onError(errorCode);
            }
        });
    }
}
