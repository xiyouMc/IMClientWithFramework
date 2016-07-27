package com.xiaoying.imapi.message;

import org.json.JSONObject;

/**
 * Created by xiyoumc on 16/7/27.
 */
public class NativeMessage {
    private int conversationType;
    private String targetId;
    private int messageId;
    private boolean messageDirection;
    private String senderUserId;
    private int readStatus;
    private int sentStatus;
    private long receivedTime;
    private long sentTime;
    private String objectName;
    private byte[] content;
    private String extra;
    private String pushContent;
    private String UId;

    public NativeMessage(JSONObject jsonObj) {
        this.conversationType = jsonObj.optInt("conversation_category");
        this.targetId = jsonObj.optString("target_id");
        this.messageId = jsonObj.optInt("id");
        this.messageDirection = jsonObj.optBoolean("message_direction");
        this.senderUserId = jsonObj.optString("sender_user_id");
        this.readStatus = jsonObj.optInt("read_status");
        this.sentStatus = jsonObj.optInt("send_status");
        this.receivedTime = jsonObj.optLong("receive_time");
        this.sentTime = jsonObj.optLong("send_time");
        this.objectName = jsonObj.optString("object_name");
        this.content = jsonObj.optString("content").getBytes();
        this.extra = jsonObj.optString("extra");
        this.pushContent = jsonObj.optString("push");
    }

    public NativeMessage() {
    }

    public String getUId() {
        return this.UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public int getConversationType() {
        return this.conversationType;
    }

    public void setConversationType(int conversationType) {
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

    public boolean getMessageDirection() {
        return this.messageDirection;
    }

    public void setMessageDirection(boolean messageDirection) {
        this.messageDirection = messageDirection;
    }

    public int getReadStatus() {
        return this.readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public int getSentStatus() {
        return this.sentStatus;
    }

    public void setSentStatus(int sentStatus) {
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

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
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
}
