package io.rong.util;

import com.dynamicload.framework.framework.VivaApplication;
import com.dynamicload.framework.framework.api.MicroApplicationContext;
import com.xiaoying.imapi.service.IMService;

import de.greenrobot.event.EventBus;

/**
 * Created by xiyoumc on 16/7/25.
 */
public class IMUtil {
    private static IMService imService;

    private static EventBus eventBus;

    public static MicroApplicationContext getMicroApplicationContext() {
        return VivaApplication.getInstance().getMicroApplicationContext();
    }

    public static IMService getIMService() {
        if (imService == null) {
            imService = getMicroApplicationContext().findServiceByInterface(IMService.class.getName());
        }
        return imService;
    }

    public static EventBus getEventBus() {
        if (eventBus == null) {
            eventBus = EventBus.getDefault();
        }
        return eventBus;
    }
}
