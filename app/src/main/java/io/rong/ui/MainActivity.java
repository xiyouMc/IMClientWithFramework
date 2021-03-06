package io.rong.ui;

import com.xiaoying.imapi.XYConversationType;
import com.xiaoying.imapi.XYIMConnectCallback;
import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.model.ErrorCode;
import com.xiaoying.imapi.service.IMService;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.rong.liveapp.R;
import io.rong.util.IMUtil;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";

    private EditText uriEdit;
    private IMService imService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uriEdit = (EditText) findViewById(R.id.live_url);
        imService = IMUtil.getIMService();
        if (imService == null) {
            return;
        }
        Button user1 = (Button) findViewById(R.id.user1_login);
        user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectServer("+3713qqWNV6z9f4ZCpzktvM4R/7aneMGEIsu/XLBJSiFJCcpHgQN50UWTjykrLEPn77MvyuRhv2L+8VU3jyYdQ=="); // 公网
                imService.setCurrentUserInfo(new XYIMUserInfo("user1", "name1", Uri.parse(""))); // 公网
            }
        });

        Button user2 = (Button) findViewById(R.id.user2_login);
        user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectServer("QGYzwQpTQZPvaF5kWStcIXk0k60ZRpL1qDuQMag8ko3hmAxJE5B7se741P+VGDKzG7Dw/OKavOt4OvDSgIk6iw=="); // 公网
                imService.setCurrentUserInfo(new XYIMUserInfo("user2", "name2", Uri.parse(""))); // 公网
            }
        });

        Button user3 = (Button) findViewById(R.id.user3_login);
        user3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectServer("dBprC+kezixOB2dnz3F2+O1d1Wqt9AiqVLATGNTfx+OPUzoG4oG1HMajBLzpAtzeRRopWzMqRY2D2q5vKBJaKQ=="); // 公网
                imService.setCurrentUserInfo(new XYIMUserInfo("user3", "name3", Uri.parse(""))); // 公网
            }
        });

        Button user4 = (Button) findViewById(R.id.user4_login);
        user4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectServer("5N29xgzjduV0jGugKitsmnk0k60ZRpL1qDuQMag8ko3hmAxJE5B7sbw2z6OyyZ8bG7Dw/OKavOvFFhmcruGUfw=="); // 公网
                imService.setCurrentUserInfo(new XYIMUserInfo("user4", "name4", Uri.parse(""))); // 公网
            }
        });
    }

    private void connectServer(String token) {
        imService.connect(token, new XYIMConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "——onTokenIncorrect—-");
            }

            @Override
            public void onSuccess(String userId) {
                Log.d(TAG, "——onSuccess—-" + userId);
                imService.startConversation(MainActivity.this, XYConversationType.CHATROOM, "chatroom002", uriEdit.getText().toString());
            }

            @Override
            public void onError(ErrorCode errorCode) {
                Log.e(TAG, "——onError—-" + errorCode);
            }
        });
    }
}
