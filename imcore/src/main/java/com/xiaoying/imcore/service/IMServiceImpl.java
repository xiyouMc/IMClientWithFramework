package com.xiaoying.imcore.service;

import com.xiaoying.imapi.BaseMessageTemplate;
import com.xiaoying.imapi.XYConversationType;
import com.xiaoying.imapi.XYIMConnectCallback;
import com.xiaoying.imapi.XYIMOnReceiveMessageListener;
import com.xiaoying.imapi.XYIMResultCallback;
import com.xiaoying.imapi.XYIMSendMessageCallback;
import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.XYOperationCallback;
import com.xiaoying.imapi.api.UserInfoProvider;
import com.xiaoying.imapi.message.XYMessage;
import com.xiaoying.imapi.message.XYMessageContent;
import com.xiaoying.imapi.model.ErrorCode;
import com.xiaoying.imapi.service.IMService;
import com.xiaoying.imcore.liveapp.xyim.rongyun.RongMessage;
import com.xiaoying.imcore.liveapp.xyim.rongyun.RongMessageContent;
import com.xiaoying.imcore.livekit.RongIM;

import android.content.Context;
import android.util.Log;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
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
    public void startConversation(Context context, XYConversationType conversationType, String targetId, String liveUrl) {
        RongIM.getInstance().startConversation(context, Conversation.ConversationType.setValue(conversationType.getValue()), targetId, liveUrl);
    }

    @Override
    public void registerMessageType(Class<? extends XYMessageContent> messageContentClass) {
        RongIM.getInstance().registerMessageType(RongMessageContent.class);
        RongIM.getInstance().registerMessageType(RongMessage.class);
    }

    @Override
    public void registerMessageTemplate(BaseMessageTemplate template) {
        RongIM.getInstance().registerMessageTemplate(template);
    }

    @Override
    public BaseMessageTemplate getMessageTemplate(Class messageContent) {
        return RongIM.getInstance().getMessageTemplate(messageContent);
    }

    @Override
    public void setUserInfoProvider(UserInfoProvider provider) {
        RongIM.getInstance().setUserInfoProvider(provider);
    }

    @Override
    public XYIMUserInfo getCurrentUserInfo() {
        UserInfo userInfo = RongIM.getInstance().getCurrentUserInfo();
        return new XYIMUserInfo(userInfo.getUserId(), userInfo.getName(), userInfo.getPortraitUri());
    }

    @Override
    public void sendMessage(final XYMessage msg, XYIMSendMessageCallback callback, final XYIMResultCallback<XYMessage> result) {
        RongIM.getInstance().sendMessage(msg, callback, new XYIMResultCallback<Message>() {
            @Override
            public void onSuccess(XYMessage message) {
                result.onSuccess(message);
            }

            @Override
            public void onError(ErrorCode var1) {
                result.onError(var1);
            }
        });
    }

    @Override
    public void registerMessageEvent(XYIMOnReceiveMessageListener listener) {
        RongIM.getInstance().registerMessageEvent(listener);
    }

    @Override
    public void setCurrentUserInfo(XYIMUserInfo userInfo) {
        RongIM.getInstance().setCurrentUserInfo(new UserInfo(userInfo.getUserId(), userInfo.getName(), userInfo.getPortraitUri()));
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
