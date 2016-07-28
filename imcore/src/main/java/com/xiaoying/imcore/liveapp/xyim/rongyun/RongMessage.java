package com.xiaoying.imcore.liveapp.xyim.rongyun;

import com.xiaoying.imapi.util.XYParcelUtils;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * Created by xiyoumc on 16/7/27.
 */
@MessageTag(value = "RC:RongMsg", flag = MessageTag.STATUS)
public class RongMessage extends MessageContent {
    private static final String TAG = "InformationNotificationMessage";
    private String message;
    protected String extra;
    public static final Creator<RongMessage> CREATOR = new Creator() {
        public RongMessage createFromParcel(Parcel source) {
            return new RongMessage(source);
        }

        public RongMessage[] newArray(int size) {
            return new RongMessage[size];
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

    protected RongMessage() {
    }

    public static RongMessage obtain(String message) {
        RongMessage model = new RongMessage();
        model.setMessage(message);
        return model;
    }

    public RongMessage(byte[] data) {
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

    public RongMessage(Parcel in) {
        this.setMessage(XYParcelUtils.readFromParcel(in));
        this.setExtra(XYParcelUtils.readFromParcel(in));
        this.setUserInfo((UserInfo) ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public RongMessage(String message) {
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