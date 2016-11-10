package io.rong.ui;

import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.api.UserInfoProvider;
import com.xiaoying.imapi.service.IMService;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import io.rong.liveapp.R;
import io.rong.util.IMUtil;

public class LiveChatRoomActivity extends FragmentActivity {

    private LiveChatRoomFragment liveChatRoomFragment;
    private IMService mIMService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_chatroom);
        liveChatRoomFragment = (LiveChatRoomFragment) getSupportFragmentManager().findFragmentById(R.id.live_chatroom_fragment);
        mIMService = IMUtil.getIMService();
        if (mIMService == null) {
            return;
        }

        mIMService.setUserInfoProvider(new UserInfoProvider() {
            @Override
            public XYIMUserInfo getUserInfo(String userId) {

                return mIMService.getCurrentUserInfo();
            }
        });
    }

    @Override
    public void onBackPressed() {
        mIMService.logout();
        super.onBackPressed();
    }
}
