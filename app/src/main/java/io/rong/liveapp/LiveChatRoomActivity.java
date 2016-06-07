package io.rong.liveapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import io.rong.imlib.model.UserInfo;
import io.rong.livekit.RongIM;
import io.rong.livekit.livechat.LiveChatRoomFragment;

public class LiveChatRoomActivity extends FragmentActivity {

    private LiveChatRoomFragment liveChatRoomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_chatroom);
        liveChatRoomFragment = (LiveChatRoomFragment) getSupportFragmentManager().findFragmentById(R.id.live_chatroom_fragment);

        RongIM.getInstance().setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String userId) {
                return App.getCurrentUserInfo();
            }
        });
    }

    @Override
    public void onBackPressed() {
        RongIM.getInstance().logout();
        super.onBackPressed();
    }
}
