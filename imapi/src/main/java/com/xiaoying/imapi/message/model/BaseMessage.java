package com.xiaoying.imapi.message.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xiyoumc on 16/8/2.
 */
public class BaseMessage {
    public String content;

    private BaseMessage() {
    }

    public BaseMessage(BaseMessageBuilder baseMessageBuilder) {
        this.content = baseMessageBuilder.content;
    }

    public static class BaseMessageBuilder {
        private String content;

        public BaseMessageBuilder content(String content) {
            this.content = content;
            return this;
        }

        public BaseMessage build() {
            return new BaseMessage(this);
        }
    }

    public String toString() {
        JSONObject message = new JSONObject();
        try {
            message.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return message.toString();
    }

    public static BaseMessage parse(JSONObject object) {
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.content = object.optString("content");
        return baseMessage;
    }
}
