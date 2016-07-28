package com.xiaoying.imapi.message;

import com.xiaoying.imapi.XYIMUserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by xiyoumc on 16/7/27.
 */
public abstract class XYMessageContent implements Parcelable {
    private static final String TAG = "MessageContent";
    private XYIMUserInfo userInfo;

    protected XYMessageContent() {
    }

    public XYMessageContent(byte[] data) {
    }

    public XYIMUserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(XYIMUserInfo info) {
        this.userInfo = info;
    }

    public JSONObject getJSONUserInfo() {
        if (this.getUserInfo() != null && this.getUserInfo().getUserId() != null) {
            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("id", this.getUserInfo().getUserId());
                if (!TextUtils.isEmpty(this.getUserInfo().getName())) {
                    jsonObject.put("name", this.getUserInfo().getName());
                }

                if (this.getUserInfo().getPortraitUri() != null) {
                    jsonObject.put("portrait", this.getUserInfo().getPortraitUri());
                }
            } catch (JSONException var3) {
                Log.e("MessageContent", "JSONException " + var3.getMessage());
            }

            return jsonObject;
        } else {
            return null;
        }
    }

    public XYIMUserInfo parseJsonToUserInfo(JSONObject jsonObj) {
        XYIMUserInfo info = null;
        String id = jsonObj.optString("id");
        String name = jsonObj.optString("name");
        String icon = jsonObj.optString("portrait");
        if (TextUtils.isEmpty(icon)) {
            icon = jsonObj.optString("icon");
        }

        if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(name)) {
            Uri portrait = icon != null ? Uri.parse(icon) : null;
            info = new XYIMUserInfo(id, name, portrait);
        }

        return info;
    }

    public abstract byte[] encode();

    public abstract static class MessageHandler<T extends XYMessageContent> {
        private Context context;

        public MessageHandler(Context context) {
            this.context = context;
        }

        public void decodeMessage(XYMessage message, T messageContent) {
            this.afterDecodeMessage(message, messageContent);
        }

        public boolean encodeMessage(XYMessage message, T messageContent) {
            return this.beforeEncodeMessage(message, messageContent);
        }

        protected Context getContext() {
            return this.context;
        }

        public abstract void afterDecodeMessage(XYMessage var1, T var2);

        public abstract boolean beforeEncodeMessage(XYMessage var1, T var2);

        public static class DefaultMessageHandler extends XYMessageContent.MessageHandler<XYMessageContent> {
            public DefaultMessageHandler(Context context) {
                super(context);
            }

            public void afterDecodeMessage(XYMessage message, XYMessageContent messageContent) {
            }

            public boolean beforeEncodeMessage(XYMessage message, XYMessageContent messageContent) {
                return false;
            }
        }
    }

    public abstract String getMessage();
}

