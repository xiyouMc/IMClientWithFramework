package com.xiaoying.imcore;

import com.dynamicload.framework.framework.BaseMetaInfo;
import com.dynamicload.framework.service.ServiceDescription;
import com.xiaoying.imapi.service.IMService;
import com.xiaoying.imcore.service.IMServiceImpl;

import android.util.Log;

/**
 * Created by xiyoumc on 16/7/23.
 */
public class MetaInfo extends BaseMetaInfo {
    private static final String TAG = "MetaInfo";

    public MetaInfo() {
        Log.d(TAG, "MetaInfo init");
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setInterfaceName(IMService.class.getName());
        serviceDescription.setBundleName("imcore");
        serviceDescription.setClassName(IMServiceImpl.class.getName());
        services.add(serviceDescription);
    }
}
