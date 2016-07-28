package io.rong.liveapp;

import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.message.XYMessageContent;
import com.xiaoying.imapi.util.XYParcelUtils;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;

import java.io.UnsupportedEncodingException;

import io.rong.imlib.MessageTag;

@MessageTag(value = "RC:PersistMsg", flag = MessageTag.ISPERSISTED)
public class PersistMessage extends XYMessageContent {

    private String content;

    public PersistMessage() {
    }

    public PersistMessage(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public PersistMessage(byte[] data) {
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
        XYParcelUtils.writeToParcel(dest, getUserInfo());
    }

    protected PersistMessage(Parcel in) {
        content = in.readString();
        setUserInfo(XYParcelUtils.readFromParcel(in, XYIMUserInfo.class));
    }

    public static final Creator<PersistMessage> CREATOR = new Creator<PersistMessage>() {
        @Override
        public PersistMessage createFromParcel(Parcel source) {
            return new PersistMessage(source);
        }

        @Override
        public PersistMessage[] newArray(int size) {
            return new PersistMessage[size];
        }
    };

    @Override
    public String getMessage() {
        return content;
    }
}