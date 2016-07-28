package com.xiaoying.imcore.livekit;

import com.xiaoying.imapi.BaseMessageTemplate;
import com.xiaoying.imapi.XYIMAbstractClient;
import com.xiaoying.imapi.XYIMConnectCallback;
import com.xiaoying.imapi.XYIMOnReceiveMessageListener;
import com.xiaoying.imapi.XYIMResultCallback;
import com.xiaoying.imapi.XYIMSendMessageCallback;
import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.api.TemplateTag;
import com.xiaoying.imapi.api.UserInfoProvider;
import com.xiaoying.imapi.message.XYMessage;
import com.xiaoying.imcore.liveapp.xyim.rongyun.XYIMRongyunClient;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

import io.rong.imlib.AnnotationNotFoundException;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

public class RongIM {

    private static final String TAG = "RongIM";

    private static RongIM instance;
    private static Context gContext;

    private Map<Class<? extends MessageContent>, BaseMessageTemplate> msgTmpMap = new HashMap<>();
    private UserInfoProvider userInfoProvider;
    private UserInfo currentUserInfo;

    static XYIMAbstractClient mXYIMAbstractClient = new XYIMRongyunClient();

    public static void init(Context context, String appKey) {
        gContext = context.getApplicationContext();
        mXYIMAbstractClient.init(context, appKey);
    }

    public static void init(Context context) {
        gContext = context.getApplicationContext();
        mXYIMAbstractClient.init(context);
    }

    public static RongIM getInstance() {
        if (instance == null && gContext != null) {
            instance = new RongIM();
        }
        return instance;
    }

    private RongIM() {
    }

    public void connect(String token, final XYIMConnectCallback callback) {
        mXYIMAbstractClient.connect(token, callback);
    }

    public void logout() {
        currentUserInfo = null;
        mXYIMAbstractClient.logout();
    }

    public void startConversation(Context context, Conversation.ConversationType conversationType, String targetId, String liveUrl) {
        Uri uri = Uri.parse("rong://" + context.getApplicationInfo().processName).buildUpon()
                .appendPath("conversation").appendPath(conversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", targetId).appendQueryParameter("liveUrl", liveUrl).build();
        context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public void registerMessageType(Class<? extends MessageContent> messageContentClass) {
        try {
            RongIMClient.registerMessageType(messageContentClass);
        } catch (AnnotationNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void registerMessageTemplate(BaseMessageTemplate template) {
        TemplateTag tag = template.getClass().getAnnotation(TemplateTag.class);
        msgTmpMap.put(tag.messageContent(), template);
    }

    public BaseMessageTemplate getMessageTemplate(Class<? extends MessageContent> messageContent) {
        return msgTmpMap.get(messageContent);
    }

    public void setUserInfoProvider(UserInfoProvider provider) {
        userInfoProvider = provider;
    }

    public UserInfo getCurrentUserInfo() {
        if (currentUserInfo == null) {
            if (userInfoProvider != null) {
                XYIMUserInfo xyimUserInfo = userInfoProvider.getUserInfo(RongIMClient.getInstance().getCurrentUserId());
                currentUserInfo = new UserInfo(xyimUserInfo.getUserId(), xyimUserInfo.getName(), xyimUserInfo.getPortraitUri());
            } else {
                currentUserInfo = new UserInfo(RongIMClient.getInstance().getCurrentUserId(), RongIMClient.getInstance().getCurrentUserId(), Uri.parse(""));
            }
        }
        return currentUserInfo;
    }

    public void setCurrentUserInfo(UserInfo info) {
        currentUserInfo = info;
    }

    public void sendMessage(final XYMessage msg, XYIMSendMessageCallback callback, XYIMResultCallback<Message> result) {
//        XYIMSendMessageCallback callback = new XYIMSendMessageCallback() {
//            @Override
//            public void onSuccess(Integer integer) {
//                eventBus.post(new BusEvent.MessageSent(msg, 0));
//            }
//
//            @Override
//            public void onError(Integer messageId, RongIMClient.ErrorCode e) {
//                eventBus.post(new BusEvent.MessageSent(msg, e.getValue()));
//            }
//        };
//        XYIMResultCallback<Message> result = new XYIMResultCallback<Message>() {
//            @Override
//            public void onSuccess(Message message) {
//            }
//
//            @Override
//            public void onError(RongIMClient.ErrorCode e) {
//            }
//        };
        XYIMRongyunClient.getInstance().sendMessage(msg, null, null, callback, result);
    }

    public void registerMessageEvent(XYIMOnReceiveMessageListener listener) {
        XYIMRongyunClient.getInstance().setOnReceiveMessageListener(
//                new XYIMOnReceiveMessageListener() {
//            @Override
//            public boolean onReceived(Message message, int left) {
//                Log.d(TAG, "onReceived left = " + left);
//                eventBus.post(new BusEvent.MessageReceived(message, left));
//                return false;
//            }
//        }
                listener
        );
    }
}
