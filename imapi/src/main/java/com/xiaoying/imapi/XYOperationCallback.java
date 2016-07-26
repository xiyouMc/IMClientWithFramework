package com.xiaoying.imapi;

import com.xiaoying.imapi.model.ErrorCode;

/**
 * Created by xiyoumc on 16/7/26.
 */
public interface XYOperationCallback {
    public void onSuccess();

    public void onError(ErrorCode errorCode);
}
