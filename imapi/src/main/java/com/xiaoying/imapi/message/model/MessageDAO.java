package com.xiaoying.imapi.message.model;

import org.json.JSONObject;

/**
 * Created by xiyoumc on 16/7/28.
 */
public class MessageDAO {
    public MessageUser mMessageUser;
    public String messageType;
    public BaseMessage msgContent;

    public MessageDAO(Builder builder) {
        this.mMessageUser = builder.mMessageUser;
        this.messageType = builder.messageType;
        this.msgContent = builder.msgContent;
    }

    public static class Builder {
        private MessageUser mMessageUser;
        private String messageType;
        private BaseMessage msgContent;

        public Builder user(MessageUser messageUser) {
            this.mMessageUser = messageUser;
            return this;
        }

        public Builder messageType(String messageType) {
            this.messageType = messageType;
            return this;
        }

        public Builder msgContent(BaseMessage msgContent) {
            this.msgContent = msgContent;
            return this;
        }

        public MessageDAO build() {
            return new MessageDAO(this);
        }
    }

    public String toString() {
        JSONObject messageDao = MessageRoute.messageMerge(this);
        return messageDao.toString();
    }
}
