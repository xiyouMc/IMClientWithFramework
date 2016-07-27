package com.xiaoying.imapi.api;

import com.xiaoying.imapi.message.XYMessage;

public class BusEvent {

    public static class MessageReceived {
        public XYMessage message;
        public int left;

        public MessageReceived(XYMessage msg, int left) {
            message = msg;
            this.left = left;
        }
    }

    public static class MessageSent {
        public XYMessage message;
        public int code;

        public MessageSent(XYMessage msg, int code) {
            message = msg;
            this.code = code;
        }
    }
}
