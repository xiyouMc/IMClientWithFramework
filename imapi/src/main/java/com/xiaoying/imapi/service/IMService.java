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

    /**
     * 连接聊天服务
     * @param token 用户的Token
     * @param callback 连接状态回调
     */
    void connect(String token, final XYIMConnectCallback callback);

    /**
     * 退出登陆
     */
    void logout();

    /**
     * 开启聊天会话
     * @param context 上下文
     * @param conversationType 聊天类型
     * @param targetId 聊天室ID
     * @param liveUrl 聊天链接
     */
    void startConversation(Context context, XYConversationType conversationType, String targetId, String liveUrl);

    /**
     * 注册消息类型
     * @param messageContentClass 消息类型类
     */
    void registerMessageType(Class<? extends XYMessageContent> messageContentClass);

    /**
     * 注册消息模版
     * 用于处理消息内容
     * @param template 消息模版类
     */
    void registerMessageTemplate(BaseMessageTemplate template);

    /**
     * 获取消息类型
     * 将当前消息处理并展示
     * @param messageContent 消息内容类
     * @return
     */
    BaseMessageTemplate getMessageTemplate(Class<? extends XYMessageContent> messageContent);

    /**
     * 设置当前用户
     * @param provider
     */
    void setUserInfoProvider(UserInfoProvider provider);

    /**
     * 获取当前用户
     * @return 当前用户对象
     */
    XYIMUserInfo getCurrentUserInfo();

    /**
     * 发送消息
     * @param msg 消息对象
     * @param callback 发送状态回调
     * @param result 消息回调
     */
    void sendMessage(final XYMessage msg, XYIMSendMessageCallback callback, XYIMResultCallback<XYMessage> result);

    void registerMessageEvent(XYIMOnReceiveMessageListener listener);

    void setCurrentUserInfo(XYIMUserInfo userInfo);

    /**
     * 加入当前聊天室
     * @param chatroomId 聊天室ID
     * @param defMessageCount 可发送消息个数
     * @param callback 操作回调
     */
    void joinChatRoom(final String chatroomId, final int defMessageCount, final XYOperationCallback callback);

}
