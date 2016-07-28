package io.rong.liveapp;

import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.message.XYMessageContent;
import com.xiaoying.imapi.util.XYParcelUtils;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;

import io.rong.imlib.MessageTag;

/**
 * Created by xiyoumc on 16/7/27.
 */
@MessageTag(value = "RC:InformationNotificationMsg", flag = MessageTag.STATUS)
public class XYInformationNotificationMessage extends XYMessageContent {
    private static final String TAG = "InformationNotificationMessage";
    private String message;
    protected String extra;
    public static final Creator<XYInformationNotificationMessage> CREATOR = new Creator() {
        public XYInformationNotificationMessage createFromParcel(Parcel source) {
            return new XYInformationNotificationMessage(source);
        }

        public XYInformationNotificationMessage[] newArray(int size) {
            return new XYInformationNotificationMessage[size];
        }
    };

    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            if (!TextUtils.isEmpty(this.getMessage())) {
                jsonObj.put("message", this.getMessage());
            }

            if (!TextUtils.isEmpty(this.getExtra())) {
                jsonObj.put("extra", this.getExtra());
            }

            if (this.getJSONUserInfo() != null) {
                jsonObj.putOpt("user", this.getJSONUserInfo());
            }
        } catch (JSONException var4) {
            Log.e("Information", "JSONException " + var4.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    protected XYInformationNotificationMessage() {
    }

    public static XYInformationNotificationMessage obtain(String message) {
        XYInformationNotificationMessage model = new XYInformationNotificationMessage();
        model.setMessage(message);
        return model;
    }

    public XYInformationNotificationMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException var5) {
            ;
        }

        try {
            JSONObject e = new JSONObject(jsonStr);
            if (e.has("message")) {
                this.setMessage(e.optString("message"));
            }

            if (e.has("extra")) {
                this.setExtra(e.optString("extra"));
            }

            if (e.has("user")) {
                this.setUserInfo(this.parseJsonToUserInfo(e.getJSONObject("user")));
            }
        } catch (JSONException var4) {
            Log.e("Information", "JSONException " + var4.getMessage());
        }

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        XYParcelUtils.writeToParcel(dest, this.getMessage());
        XYParcelUtils.writeToParcel(dest, this.getExtra());
        XYParcelUtils.writeToParcel(dest, this.getUserInfo());
    }

    public XYInformationNotificationMessage(Parcel in) {
        this.setMessage(XYParcelUtils.readFromParcel(in));
        this.setExtra(XYParcelUtils.readFromParcel(in));
        this.setUserInfo((XYIMUserInfo) XYParcelUtils.readFromParcel(in, XYIMUserInfo.class));
    }

    public XYInformationNotificationMessage(String message) {
        this.setMessage(message);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
