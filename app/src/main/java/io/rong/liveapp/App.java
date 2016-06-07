package io.rong.liveapp;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import io.rong.imlib.model.UserInfo;
import io.rong.liveapp.message.GiftMessageTemplate;
import io.rong.liveapp.message.NoneMessage;
import io.rong.liveapp.message.NoneMessageTemplate;
import io.rong.liveapp.message.PersistMessage;
import io.rong.liveapp.message.PersistMessageTemplate;
import io.rong.livekit.RongIM;
import io.rong.liveapp.message.GiftMessage;

public class App extends Application {

    private static UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
             RongIM.init(this, "mgb7ka1nbsv8g"); // 公网环境
//            RongIM.init(this, "e0x9wycfx7flq"); // 小乔环境
            if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
                RongIM.getInstance().registerMessageType(GiftMessage.class);
                RongIM.getInstance().registerMessageTemplate(new GiftMessageTemplate());
                RongIM.getInstance().registerMessageType(PersistMessage.class);
                RongIM.getInstance().registerMessageTemplate(new PersistMessageTemplate());
                RongIM.getInstance().registerMessageType(NoneMessage.class);
                RongIM.getInstance().registerMessageTemplate(new NoneMessageTemplate());
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

    public static UserInfo getCurrentUserInfo() {
        return userInfo;
    }

    public static void setCurrentUserInfo(UserInfo info) {
        userInfo = info;
    }
}