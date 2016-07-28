package com.xiaoying.imcore.liveapp.xyim.rongyun;

import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.message.XYMessageContent;

import org.json.JSONObject;

import android.os.Parcel;

import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * Created by xiyoumc on 16/7/28.
 */
@MessageTag(value = "RC:RongMessageContent", flag = MessageTag.STATUS)
public class RongMessageContent extends MessageContent {
    private Class<? extends XYMessageContent> mXYMessageContent;
    private XYMessageContent mMessageContent;

    public RongMessageContent(Class<? extends XYMessageContent> mXYMessageContent) {
        super();
        this.mXYMessageContent = mXYMessageContent;
    }

    public RongMessageContent(XYMessageContent content) {
        this.mMessageContent = content;
    }


    public RongMessageContent(byte[] data) {
        super(data);
    }

    @Override
    public UserInfo getUserInfo() {
        if (this.mMessageContent != null) {
            XYIMUserInfo userInfo = this.mMessageContent.getUserInfo();
            return new UserInfo(userInfo.getUserId(), userInfo.getName(), userInfo.getPortraitUri());
        }
        return null;
    }

    public String getName() {
        return mXYMessageContent.getName();
    }

    @Override
    public void setUserInfo(UserInfo info) {
        super.setUserInfo(info);
        if (this.mMessageContent != null){
            mMessageContent.setUserInfo(new XYIMUserInfo(info.getUserId(), info.getName(), info.getPortraitUri()));
        }
//
    }

    @Override
    public JSONObject getJSONUserInfo() {
        if (this.mMessageContent != null){
            return this.mMessageContent.getJSONUserInfo();
        }
        return null;
    }

    @Override
    public UserInfo parseJsonToUserInfo(JSONObject jsonObj) {
        if (this.mMessageContent != null){
            XYIMUserInfo userInfo = mMessageContent.parseJsonToUserInfo(jsonObj);

        }
//
        return null;
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
