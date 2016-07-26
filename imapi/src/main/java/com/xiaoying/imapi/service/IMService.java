package com.xiaoying.imapi.service;

import com.xiaoying.imapi.BaseMessageTemplate;
import com.xiaoying.imapi.XYIMConnectCallback;
import com.xiaoying.imapi.XYIMOnReceiveMessageListener;
import com.xiaoying.imapi.XYIMResultCallback;
import com.xiaoying.imapi.XYIMSendMessageCallback;
import com.xiaoying.imapi.XYOperationCallback;
import com.xiaoying.imapi.api.UserInfoProvider;

import android.content.Context;

import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * Created by xiyoumc on 16/7/22.
 */
public interface IMService {
    void init(Context context);

    void init(Context context, String appKey);

    void connect(String token, final XYIMConnectCallback callback);

    void logout();

    void startConversation(Context context, Conversation.ConversationType conversationType, String targetId, String liveUrl);

    void registerMessageType(Class<? extends MessageContent> messageContentClass);

    void registerMessageTemplate(BaseMessageTemplate template);

    BaseMessageTemplate getMessageTemplate(Class<? extends MessageContent> messageContent);

    void setUserInfoProvider(UserInfoProvider provider);

    UserInfo getCurrentUserInfo();

    void sendMessage(final Message msg, XYIMSendMessageCallback callback, XYIMResultCallback<Message> result);

    void registerMessageEvent(XYIMOnReceiveMessageListener listener);

    void setCurrentUserInfo(UserInfo userInfo);

    void joinChatRoom(final String chatroomId, final int defMessageCount, final XYOperationCallback callback);

}
