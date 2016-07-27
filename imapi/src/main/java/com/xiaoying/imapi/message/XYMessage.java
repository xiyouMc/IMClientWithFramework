package com.xiaoying.imapi.message;

import com.xiaoying.imapi.XYConversationType;
import com.xiaoying.imapi.util.XYParcelUtils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiyoumc on 16/7/27.
 */
public class XYMessage implements Parcelable {
    private XYConversationType conversationType;
    private String targetId;
    private int messageId;
    private XYMessage.MessageDirection messageDirection;
    private String senderUserId;
    private XYMessage.ReceivedStatus receivedStatus;
    private XYMessage.SentStatus sentStatus;
    private long receivedTime;
    private long sentTime;
    private String objectName;
    private XYMessageContent content;
    private String extra;
    private String UId;
    public static final Creator<XYMessage> CREATOR = new Creator() {
        public XYMessage createFromParcel(Parcel source) {
            return new XYMessage(source);
        }

        public XYMessage[] newArray(int size) {
            return new XYMessage[size];
        }
    };

    public String getUId() {
        return this.UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public XYMessage() {
    }

    public XYMessage(NativeMessage msg) {
        this.conversationType = XYConversationType.setValue(msg.getConversationType());
        this.targetId = msg.getTargetId();
        this.messageId = msg.getMessageId();
        this.messageDirection = !msg.getMessageDirection() ? XYMessage.MessageDirection.SEND : XYMessage.MessageDirection.RECEIVE;
        this.senderUserId = msg.getSenderUserId();
        this.receivedStatus = new XYMessage.ReceivedStatus(msg.getReadStatus());
        this.sentStatus = XYMessage.SentStatus.setValue(msg.getSentStatus());
        this.receivedTime = msg.getReceivedTime();
        this.sentTime = msg.getSentTime();
        this.objectName = msg.getObjectName();
        this.UId = msg.getUId();
        this.extra = msg.getExtra();
    }

    public static XYMessage obtain(String targetId, XYConversationType type, XYMessageContent content) {
        XYMessage obj = new XYMessage();
        obj.setTargetId(targetId);
        obj.setConversationType(type);
        obj.setContent(content);
        return obj;
    }

    public XYConversationType getConversationType() {
        return this.conversationType;
    }

    public void setConversationType(XYConversationType conversationType) {
        this.conversationType = conversationType;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public XYMessage.MessageDirection getMessageDirection() {
        return this.messageDirection;
    }

    public void setMessageDirection(XYMessage.MessageDirection messageDirection) {
        this.messageDirection = messageDirection;
    }

    public XYMessage.ReceivedStatus getReceivedStatus() {
        return this.receivedStatus;
    }

    public void setReceivedStatus(XYMessage.ReceivedStatus receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public XYMessage.SentStatus getSentStatus() {
        return this.sentStatus;
    }

    public void setSentStatus(XYMessage.SentStatus sentStatus) {
        this.sentStatus = sentStatus;
    }

    public long getReceivedTime() {
        return this.receivedTime;
    }

    public void setReceivedTime(long receivedTime) {
        this.receivedTime = receivedTime;
    }

    public long getSentTime() {
        return this.sentTime;
    }

    public void setSentTime(long sentTime) {
        this.sentTime = sentTime;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public XYMessageContent getContent() {
        return this.content;
    }

    public void setContent(XYMessageContent content) {
        this.content = content;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public int describeContents() {
        return 0;
    }

    public XYMessage(Parcel in) {
        String className = XYParcelUtils.readFromParcel(in);
        Class loader = null;
        if (className != null) {
            try {
                loader = Class.forName(className);
            } catch (ClassNotFoundException var5) {
                var5.printStackTrace();
            }
        }

        this.setTargetId(XYParcelUtils.readFromParcel(in));
        this.setMessageId(XYParcelUtils.readIntFromParcel(in).intValue());
        this.setSenderUserId(XYParcelUtils.readFromParcel(in));
        this.setReceivedTime(XYParcelUtils.readLongFromParcel(in).longValue());
        this.setSentTime(XYParcelUtils.readLongFromParcel(in).longValue());
        this.setObjectName(XYParcelUtils.readFromParcel(in));
        this.setContent((XYMessageContent) XYParcelUtils.readFromParcel(in, loader));
        this.setExtra(XYParcelUtils.readFromParcel(in));
        this.setUId(XYParcelUtils.readFromParcel(in));
        this.setConversationType(XYConversationType.setValue(XYParcelUtils.readIntFromParcel(in).intValue()));
        this.setMessageDirection(XYMessage.MessageDirection.setValue(XYParcelUtils.readIntFromParcel(in).intValue()));
        this.setReceivedStatus(new XYMessage.ReceivedStatus(XYParcelUtils.readIntFromParcel(in).intValue()));
        this.setSentStatus(XYMessage.SentStatus.setValue(XYParcelUtils.readIntFromParcel(in).intValue()));
    }

    public void writeToParcel(Parcel dest, int flags) {
        XYParcelUtils.writeToParcel(dest, this.getContent() != null ? this.getContent().getClass().getName() : null);
        XYParcelUtils.writeToParcel(dest, this.getTargetId());
        XYParcelUtils.writeToParcel(dest, Integer.valueOf(this.getMessageId()));
        XYParcelUtils.writeToParcel(dest, this.getSenderUserId());
        XYParcelUtils.writeToParcel(dest, Long.valueOf(this.getReceivedTime()));
        XYParcelUtils.writeToParcel(dest, Long.valueOf(this.getSentTime()));
        XYParcelUtils.writeToParcel(dest, this.getObjectName());
        XYParcelUtils.writeToParcel(dest, this.getContent());
        XYParcelUtils.writeToParcel(dest, this.getExtra());
        XYParcelUtils.writeToParcel(dest, this.getUId());
        XYParcelUtils.writeToParcel(dest, Integer.valueOf(this.getConversationType().getValue()));
        XYParcelUtils.writeToParcel(dest, Integer.valueOf(this.getMessageDirection() == null ? 0 : this.getMessageDirection().getValue()));
        XYParcelUtils.writeToParcel(dest, Integer.valueOf(this.getReceivedStatus() == null ? 0 : this.getReceivedStatus().getFlag()));
        XYParcelUtils.writeToParcel(dest, Integer.valueOf(this.getSentStatus() == null ? 0 : this.getSentStatus().getValue()));
    }

    public boolean equals(Object o) {
        return o == null ? false : (o instanceof XYMessage ? this.messageId == ((XYMessage) o).getMessageId() : super.equals(o));
    }

    public static class ReceivedStatus {
        private static final int READ = 1;
        private static final int LISTENED = 2;
        private static final int DOWNLOADED = 4;
        private static final int RETRIEVED = 8;
        private static final int MULTIPLERECEIVE = 16;
        private int flag = 0;
        private boolean isRead = false;
        private boolean isListened = false;
        private boolean isDownload = false;
        private boolean isRetrieved = false;
        private boolean isMultipleReceive = false;

        public ReceivedStatus(int flag) {
            this.flag = flag;
            this.isRead = (flag & 1) == 1;
            this.isListened = (flag & 2) == 2;
            this.isDownload = (flag & 4) == 4;
            this.isRetrieved = (flag & 8) == 8;
            this.isMultipleReceive = (flag & 16) == 16;
        }

        public int getFlag() {
            return this.flag;
        }

        public boolean isRead() {
            return this.isRead;
        }

        public void setRead() {
            this.flag |= 1;
            this.isRead = true;
        }

        public boolean isListened() {
            return this.isListened;
        }

        public void setListened() {
            this.flag |= 2;
            this.isListened = true;
        }

        public boolean isDownload() {
            return this.isDownload;
        }

        public void setDownload() {
            this.flag |= 4;
            this.isDownload = true;
        }

        public boolean isRetrieved() {
            return this.isRetrieved;
        }

        public void setRetrieved() {
            this.flag |= 8;
            this.isRetrieved = true;
        }

        public boolean isMultipleReceive() {
            return this.isMultipleReceive;
        }

        public void setMultipleReceive() {
            this.flag |= 16;
            this.isMultipleReceive = true;
        }
    }

    public static enum SentStatus {
        SENDING(10),
        FAILED(20),
        SENT(30),
        RECEIVED(40),
        READ(50),
        DESTROYED(60);

        private int value = 1;

        private SentStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static XYMessage.SentStatus setValue(int code) {
            XYMessage.SentStatus[] arr$ = values();
            int len$ = arr$.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                XYMessage.SentStatus c = arr$[i$];
                if (code == c.getValue()) {
                    return c;
                }
            }

            return SENDING;
        }
    }

    public static enum MessageDirection {
        SEND(1),
        RECEIVE(2);

        private int value = 1;

        private MessageDirection(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static XYMessage.MessageDirection setValue(int code) {
            XYMessage.MessageDirection[] arr$ = values();
            int len$ = arr$.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                XYMessage.MessageDirection c = arr$[i$];
                if (code == c.getValue()) {
                    return c;
                }
            }

            return SEND;
        }
    }
}
