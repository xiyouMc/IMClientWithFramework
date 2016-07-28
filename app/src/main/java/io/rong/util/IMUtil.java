package io.rong.util;

import com.xiaoying.imapi.service.IMService;
import com.xiaoying.imcore.service.IMServiceImpl;

import de.greenrobot.event.EventBus;

/**
 * Created by xiyoumc on 16/7/25.
 */
public class IMUtil {
    private static IMService imService;

    private static EventBus eventBus;

//    public static MicroApplicationContext getMicroApplicationContext() {
//        return VivaApplication.getInstance().getMicroApplicationContext();
//    }

    public static IMService getIMService() {
        if (imService == null) {
//            imService = getMicroApplicationContext().findServiceByInterface(IMService.class.getName());
            imService = new IMServiceImpl();
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
