package com.xiaoying.imapi.message;

import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.util.XYParcelUtils;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiyoumc on 16/7/27.
 */
public class XYTextMessage extends XYMessageContent {
    private static final String TAG = "TextMessage";
    private String content;
    protected String extra;
    public static final Parcelable.Creator<XYTextMessage> CREATOR = new Parcelable.Creator() {
        public XYTextMessage createFromParcel(Parcel source) {
            return new XYTextMessage(source);
        }

        public XYTextMessage[] newArray(int size) {
            return new XYTextMessage[size];
        }
    };

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("content", this.getEmotion(this.getContent()));
            if (!TextUtils.isEmpty(this.getExtra())) {
                jsonObj.put("extra", this.getExtra());
            }

            if (this.getJSONUserInfo() != null) {
                jsonObj.putOpt("user", this.getJSONUserInfo());
            }
        } catch (JSONException var4) {
            Log.e("TextMessage", "JSONException " + var4.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    private String getEmotion(String content) {
        Pattern pattern = Pattern.compile("\\[/u([0-9A-Fa-f]+)\\]");
        Matcher matcher = pattern.matcher(content);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            int inthex = Integer.parseInt(matcher.group(1), 16);
            matcher.appendReplacement(sb, String.valueOf(Character.toChars(inthex)));
        }

        matcher.appendTail(sb);
        Log.d("TextMessage", "getEmotion--" + sb.toString());
        return sb.toString();
    }

    protected XYTextMessage() {
    }

    public static XYTextMessage obtain(String text) {
        XYTextMessage model = new XYTextMessage();
        model.setContent(text);
        return model;
    }

    public XYTextMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException var5) {
            ;
        }

        try {
            JSONObject e = new JSONObject(jsonStr);
            if (e.has("content")) {
                this.setContent(e.optString("content"));
            }

            if (e.has("extra")) {
                this.setExtra(e.optString("extra"));
            }

            if (e.has("user")) {
                this.setUserInfo(this.parseJsonToUserInfo(e.getJSONObject("user")));
            }
        } catch (JSONException var4) {
            Log.e("TextMessage", "JSONException " + var4.getMessage());
        }

    }

    public void setContent(String content) {
        this.content = content;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        XYParcelUtils.writeToParcel(dest, this.getExtra());
        XYParcelUtils.writeToParcel(dest, this.content);
        XYParcelUtils.writeToParcel(dest, this.getUserInfo());
    }

    public XYTextMessage(Parcel in) {
        this.setExtra(XYParcelUtils.readFromParcel(in));
        this.setContent(XYParcelUtils.readFromParcel(in));
        this.setUserInfo((XYIMUserInfo) XYParcelUtils.readFromParcel(in, XYIMUserInfo.class));
    }

    public XYTextMessage(String content) {
        this.setContent(content);
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String getMessage() {
        return this.content;
    }
}
