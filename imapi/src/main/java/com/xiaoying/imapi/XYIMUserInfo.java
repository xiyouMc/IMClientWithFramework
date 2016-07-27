package com.xiaoying.imapi;

import com.xiaoying.imapi.util.XYParcelUtils;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by Administrator on 2016/6/7.
 */
public class XYIMUserInfo implements Parcelable {
    private String id;
    private String name;
    private Uri portraitUri;
    public static final Parcelable.Creator<XYIMUserInfo> CREATOR = new Parcelable.Creator() {
        public XYIMUserInfo createFromParcel(Parcel source) {
            return new XYIMUserInfo(source);
        }

        public XYIMUserInfo[] newArray(int size) {
            return new XYIMUserInfo[size];
        }
    };

    public XYIMUserInfo(Parcel in) {
        this.setUserId(XYParcelUtils.readFromParcel(in));
        this.setName(XYParcelUtils.readFromParcel(in));
        this.setPortraitUri((Uri) XYParcelUtils.readFromParcel(in, Uri.class));
    }

    public XYIMUserInfo(String id, String name, Uri portraitUri) {
        if (TextUtils.isEmpty(id)) {
            throw new NullPointerException("userId is null");
        } else {
            this.id = id;
            this.name = name;
            this.portraitUri = portraitUri;
        }
    }

    public String getUserId() {
        if (TextUtils.isEmpty(this.id)) {
            throw new NullPointerException("userId  is null");
        } else {
            return this.id;
        }
    }

    public void setUserId(String userId) {
        this.id = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getPortraitUri() {
        return this.portraitUri;
    }

    public void setPortraitUri(Uri uri) {
        this.portraitUri = uri;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        XYParcelUtils.writeToParcel(dest, this.getUserId());
        XYParcelUtils.writeToParcel(dest, this.getName());
        XYParcelUtils.writeToParcel(dest, this.getPortraitUri());
    }
}
