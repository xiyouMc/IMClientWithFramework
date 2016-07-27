package com.xiaoying.imapi.service;

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

import android.content.Context;

/**
 * Created by xiyoumc on 16/7/22.
 */
public interface IMService {
    void init(Context context);

    void init(Context context, String appKey);

    void connect(String token, final XYIMConnectCallback callback);

    void logout();

    void startConversation(Context context, XYConversationType conversationType, String targetId, String liveUrl);

    void registerMessageType(Class<? extends XYMessageContent> messageContentClass);

    void registerMessageTemplate(BaseMessageTemplate template);

    BaseMessageTemplate getMessageTemplate(Class<? extends XYMessageContent> messageContent);

    void setUserInfoProvider(UserInfoProvider provider);

    XYIMUserInfo getCurrentUserInfo();

    void sendMessage(final XYMessage msg, XYIMSendMessageCallback callback, XYIMResultCallback<XYMessage> result);

    void registerMessageEvent(XYIMOnReceiveMessageListener listener);

    void setCurrentUserInfo(XYIMUserInfo userInfo);

    void joinChatRoom(final String chatroomId, final int defMessageCount, final XYOperationCallback callback);

}
