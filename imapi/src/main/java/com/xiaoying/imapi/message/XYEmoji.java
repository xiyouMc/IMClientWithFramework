package com.xiaoying.imapi.message;

/**
 * Created by xiyoumc on 16/7/27.
 */
public class XYEmoji {
    private int code;
    private int res;

    public XYEmoji(int code, int res) {
        this.code = code;
        this.res = res;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
