package io.rong.liveapp;

import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.message.XYMessageContent;
import com.xiaoying.imapi.util.XYParcelUtils;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;

import io.rong.imlib.MessageTag;

@MessageTag(value = "RC:GiftMsg", flag = MessageTag.STATUS)
public class GiftMessage extends XYMessageContent {

    private String type;
    private String content;

    public GiftMessage(String type) {
        this.type = type;
    }

    public GiftMessage(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public GiftMessage(byte[] data) {
        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            if (jsonObj.has("content"))
                content = jsonObj.optString("content");
            if (jsonObj.has("type"))
                type = jsonObj.optString("type");
            if (jsonObj.has("user"))
                setUserInfo(parseJsonToUserInfo(jsonObj.getJSONObject("user")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("content", content);
            if (!TextUtils.isEmpty(getType()))
                jsonObj.put("type", type);
            if (getJSONUserInfo() != null) {
                jsonObj.putOpt("user", getJSONUserInfo());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(type);
        XYParcelUtils.writeToParcel(dest, getUserInfo());
    }

    protected GiftMessage(Parcel in) {
        content = in.readString();
        type = in.readString();
        setUserInfo(XYParcelUtils.readFromParcel(in, XYIMUserInfo.class));
    }

    public static final Creator<GiftMessage> CREATOR = new Creator<GiftMessage>() {
        @Override
        public GiftMessage createFromParcel(Parcel source) {
            return new GiftMessage(source);
        }

        @Override
        public GiftMessage[] newArray(int size) {
            return new GiftMessage[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return content;
    }
}