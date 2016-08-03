package com.xiaoying.imapi.message.model;

/**
 * 用户模型类
 * Created by xiyoumc on 16/7/28.
 */
public class MessageUser {
    public String userID;
    public String userName;
    public String userIcon;
    public String level;

    public MessageUser(Builder builder) {
        this.userID = builder.userID;
        this.userName = builder.userName;
        this.userIcon = builder.userIcon;
        this.level = builder.level;
    }

    public static class Builder {
        private String userID;
        private String userName;
        private String userIcon;
        private String level;

        public Builder userID(String userID) {
            this.userID = userID;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userIcon(String userIcon) {
            this.userIcon = userIcon;
            return this;
        }

        public Builder level(String level) {
            this.level = level;
            return this;
        }

        public MessageUser build() {
            return new MessageUser(this);
        }
    }
}
