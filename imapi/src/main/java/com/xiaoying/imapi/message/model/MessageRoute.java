package com.xiaoying.imapi.message.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 消息路由
 * Created by xiyoumc on 16/7/29.
 */
public class MessageRoute {
    public static JSONObject messageMerge(MessageDAO messageDAO) {
        JSONObject message = new JSONObject();
        try {
            //msgType
            message.put("msgType", messageDAO.messageType);
            //user
            userMerge(messageDAO.mMessageUser, message);
            //msgContent
            BaseMessage msgContent = messageDAO.msgContent;
            message.put("msgContent", new JSONObject(msgContent.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }

    private static void userMerge(MessageUser messageUser, JSONObject message) {
        try {
            if (messageUser.userID != null) {
                message.put("userID", messageUser.userID);
            }
            if (messageUser.userName != null) {
                message.put("userName", messageUser.userName);
            }
            if (messageUser.userIcon != null) {
                message.put("userIcon", messageUser.userIcon);
            }
            if (messageUser.level != null) {
                message.put("level", messageUser.level);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static MessageUser messageToUser(JSONObject messge) {
        MessageUser messageUser = new MessageUser.Builder().userID(messge.optString("userID")).userName(messge.optString("userName")).userIcon(messge.optString("userIcon"))
                .level(messge.optString("level")).build();
        return messageUser;
    }

    public static String messageToMsgType(JSONObject message) {
        return message.optString("msgType");
    }

    public static JSONObject messageToMsgContent(JSONObject message) {
        return message.optJSONObject("msgContent");
    }
}
