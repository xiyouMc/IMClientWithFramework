package io.rong.liveapp;

import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.message.XYMessageContent;
import com.xiaoying.imapi.util.XYParcelUtils;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;

import java.io.UnsupportedEncodingException;

import io.rong.imlib.MessageTag;

@MessageTag(value = "RC:NoneMsg", flag = MessageTag.NONE)
public class NoneMessage extends XYMessageContent {

    private String content;

    public NoneMessage() {
    }

    public NoneMessage(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public NoneMessage(byte[] data) {
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

    protected NoneMessage(Parcel in) {
        content = in.readString();
        setUserInfo(XYParcelUtils.readFromParcel(in, XYIMUserInfo.class));
    }

    public static final Creator<NoneMessage> CREATOR = new Creator<NoneMessage>() {
        @Override
        public NoneMessage createFromParcel(Parcel source) {
            return new NoneMessage(source);
        }

        @Override
        public NoneMessage[] newArray(int size) {
            return new NoneMessage[size];
        }
    };

    @Override
    public String getMessage() {
        return content;
    }
}