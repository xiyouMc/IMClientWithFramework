package io.rong.liveapp;

//import com.dynamicload.framework.util.FrameworkUtil;

import com.xiaoying.imapi.XYIMOnReceiveMessageListener;
import com.xiaoying.imapi.api.BusEvent;
import com.xiaoying.imapi.message.XYEmoji;
import com.xiaoying.imapi.message.XYMessage;
import com.xiaoying.imapi.message.XYTextMessage;
import com.xiaoying.imapi.service.IMService;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import io.rong.template.InformationNotificationMessageTemplate;
import io.rong.template.TextMessageTemplate;
import io.rong.util.IMUtil;

public class App extends Application {

    private boolean isIint = false;

    private IMService imService;

    @Override
    public void onCreate() {
        super.onCreate();
        if (!isIint) {
            XYEmoji.init(this);
//            FrameworkUtil.setContext(this);
//            FrameworkUtil.prepare();
            imService = IMUtil.getIMService();
            isIint = true;
        }

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            if (imService != null) {
                imService.init(this, "mgb7ka1nbsv8g");// 公网环境
                //            imService.init(this, "e0x9wycfx7flq"); // 小乔环境

                if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
                    imService.registerMessageType(GiftMessage.class);
                    imService.registerMessageTemplate(new GiftMessageTemplate());
                    imService.registerMessageType(PersistMessage.class);
                    imService.registerMessageTemplate(new PersistMessageTemplate());
                    imService.registerMessageType(NoneMessage.class);
                    imService.registerMessageTemplate(new NoneMessageTemplate());

                    imService.registerMessageType(XYInformationNotificationMessage.class);
                    imService.registerMessageType(XYTextMessage.class);
                    imService.registerMessageTemplate(new TextMessageTemplate());
                    imService.registerMessageTemplate(new InformationNotificationMessageTemplate());
                    imService.registerMessageEvent(new XYIMOnReceiveMessageListener() {
                        @Override
                        public boolean onReceived(XYMessage message, int left) {
                            Log.d("APP", "onReceived left = " + left);
                            IMUtil.getEventBus().post(new BusEvent.MessageReceived(message, left));
                            return true;
                        }
                    });
                }
            }
        }
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}