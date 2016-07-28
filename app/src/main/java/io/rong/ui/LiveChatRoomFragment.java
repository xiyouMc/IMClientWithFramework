package io.rong.ui;

import com.xiaoying.imapi.XYConversationType;
import com.xiaoying.imapi.XYIMOnReceiveMessageListener;
import com.xiaoying.imapi.XYIMResultCallback;
import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.XYOperationCallback;
import com.xiaoying.imapi.api.BusEvent;
import com.xiaoying.imapi.message.XYMessage;
import com.xiaoying.imapi.message.XYTextMessage;
import com.xiaoying.imapi.model.ErrorCode;
import com.xiaoying.imapi.service.IMService;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import io.rong.listener.XYIMSendMessageCallbackImpl;
import io.rong.liveapp.GiftMessage;
import io.rong.liveapp.R;
import io.rong.ui.adapter.LiveChatListAdapter;
import io.rong.liveapp.XYInformationNotificationMessage;
import io.rong.util.IMUtil;

public class LiveChatRoomFragment extends Fragment implements XYIMOnReceiveMessageListener, Handler.Callback {

    private static final String TAG = "LiveChatRoomFragment";

    private ListView liveChatListView;
    private LiveChatListAdapter liveChatListAdapter;
    private NewMessageHint newMessage;
    private EditText rongInputBoard;

    private XYConversationType conversationType;
    private String targetId;
    private String liveUrl;

    private ImageView gift_flower;
    private ImageView gift_applaud;
    private boolean longClicked;
    private Handler handler = new Handler(this);

    private static int count;
    private IMService imService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imService = IMUtil.getIMService();
        if (imService == null) {
            return;
        }
        IMUtil.getEventBus().register(this);

        Uri uri = getActivity().getIntent().getData();
        String typeStr = uri.getLastPathSegment().toUpperCase();
        conversationType = XYConversationType.valueOf(typeStr);
        targetId = uri.getQueryParameter("targetId");
        liveUrl = uri.getQueryParameter("liveUrl");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.rc_fragment_live_chatroom, container, false);
        liveChatListView = (ListView) root.findViewById(R.id.live_chatlist);
        newMessage = new NewMessageHint(root, liveChatListView);
        rongInputBoard = (EditText) root.findViewById(R.id.input_board);
//        rongInputBoard.setActivity(getActivity());
        gift_flower = (ImageView) root.findViewById(R.id.gift_flower);
        gift_applaud = (ImageView) root.findViewById(R.id.gift_applaud);

//        rongInputBoard.setInputBarStyle(InputBar.Style.STYLE_CONTAINER);
        liveChatListAdapter = new LiveChatListAdapter();
        liveChatListView.setAdapter(liveChatListAdapter);

        imService.joinChatRoom(targetId, -1, new XYOperationCallback() {
            @Override
            public void onSuccess() {
                if (imService == null) {
                    return;
                }
                XYIMUserInfo info = imService.getCurrentUserInfo();
                String infoText = String.format(getResources().getString(R.string.live_join_chatroom), info.getName());
                Log.d(TAG, "infoText = " + infoText);
                XYTextMessage content = XYTextMessage.obtain(infoText);
                XYMessage msg = XYMessage.obtain(targetId, conversationType, content);
                imService.sendMessage(msg, new XYIMSendMessageCallbackImpl(msg), new XYIMResultCallback<XYMessage>() {
                    @Override
                    public void onSuccess(XYMessage message) {
                        Log.d(TAG, "message:" + message.getContent());
                    }

                    @Override
                    public void onError(ErrorCode var1) {
                        Log.e(TAG,"error:" + var1.getValue() + " " + var1.getMessage());
                    }
                });
            }

            @Override
            public void onError(ErrorCode errorCode) {

            }
        });

//        rongInputBoard.setInputBoardClickListener(new IInputBoardClickListener() {
//            @Override
//            public void onSendToggleClick(View v, String text) {
//                if (imService == null) {
//                    return;
//                }
//                Message message = Message.obtain(targetId, conversationType, TextMessage.obtain(text));
//                UserInfo myInfo = imService.getCurrentUserInfo();
//                message.getContent().setUserInfo(myInfo);
//                message.setMessageDirection(Message.MessageDirection.SEND);
//                imService.sendMessage(message, new XYIMSendMessageCallbackImpl(message), new XYIMResultCallbackImpl());
//            }
//
//            @Override
//            public void onEditTextClick(EditText editText) {
//            }
//
//            @Override
//            public void onImageSendResult(List<Uri> selectedImages) {
//            }
//
//            @Override
//            public void onLocationSendResult() {
//            }
//
//            @Override
//            public boolean onSwitchToggleClick(View v, ViewGroup inputBoard) {
//                return false;
//            }
//
//            @Override
//            public void onAudioInputToggleTouch(View v, MotionEvent event) {
//            }
//
//            @Override
//            public boolean onEmoticonToggleClick(View v, ViewGroup emotionBoard) {
//                return false;
//            }
//
//            @Override
//            public boolean onPluginToggleClick(View v, ViewGroup pluginBoard) {
//                return false;
//            }
//        });

        gift_flower.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    longClicked = true;
                    Thread t = new Thread() {
                        @Override
                        public void run() {
                            while (longClicked) {
                                handler.sendEmptyMessage(0);
                                try {
                                    sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    t.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    longClicked = false;
                }
                return true;
            }
        });

        gift_applaud.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    longClicked = true;
                    Thread t = new Thread() {
                        @Override
                        public void run() {
                            while (longClicked) {
                                handler.sendEmptyMessage(1);
                                try {
                                    sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    t.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    longClicked = false;
                }
                return true;
            }
        });

//        Button btn1 = (Button) root.findViewById(R.id.test_1);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Message message = Message.obtain(targetId, conversationType, new NoneMessage(Integer.toString(count++)));
//                message.getContent().setUserInfo(RongIM.getInstance().getCurrentUserInfo());
//                RongIM.getInstance().sendMessage(message);
//            }
//        });
//        Button btn2 = (Button) root.findViewById(R.id.test_2);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Message message = Message.obtain(targetId, conversationType, new PersistMessage(Integer.toString(count++)));
//                message.getContent().setUserInfo(RongIM.getInstance().getCurrentUserInfo());
//                RongIM.getInstance().sendMessage(message);
//            }
//        });
//        Button btn3 = (Button) root.findViewById(R.id.test_3);
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Message message = Message.obtain(targetId, conversationType, new TextMessage("存库计数消息, num = " + count++));
//                message.getContent().setUserInfo(RongIM.getInstance().getCurrentUserInfo());
//                RongIM.getInstance().sendMessage(message);
//            }
//        });
//        Button btn4 = (Button) root.findViewById(R.id.test_4);
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Message message = Message.obtain(targetId, conversationType, new GiftMessage("1", Integer.toString(count++)));
//                message.getContent().setUserInfo(RongIM.getInstance().getCurrentUserInfo());
//                RongIM.getInstance().sendMessage(message);
//            }
//        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public boolean onReceived(XYMessage message, int left) {
        Log.d(TAG, "onReceived");
        if (message.getConversationType() == conversationType && message.getTargetId().equals(targetId)) {
            liveChatListAdapter.addMessage(message);
            liveChatListAdapter.notifyDataSetChanged();
        }
        return false;
    }

    @Override
    public boolean handleMessage(android.os.Message msg) {
        if (imService == null) {
            return false;
        }
        String type = Integer.toString(msg.what);
        XYMessage message = XYMessage.obtain(targetId, conversationType, new GiftMessage(type));
        message.getContent().setUserInfo(imService.getCurrentUserInfo());
        imService.sendMessage(message, new XYIMSendMessageCallbackImpl(message), new XYIMResultCallback<XYMessage>() {
            @Override
            public void onSuccess(XYMessage message) {
                Log.d(TAG, "messsage:" + message.getContent());
            }

            @Override
            public void onError(ErrorCode var1) {

            }
        });
        return false;
    }

    public void onEventMainThread(BusEvent.MessageReceived event) {
        Log.d(TAG, "BusEvent.MessageReceived left = " + event.left);
        XYMessage msg = event.message;
        if (targetId.equals(msg.getTargetId()) && conversationType == XYConversationType.CHATROOM) {
            liveChatListAdapter.addMessage(msg);
            liveChatListAdapter.notifyDataSetChanged();
            newMessage.messageArrived();
        }
    }

    public void onEventMainThread(BusEvent.MessageSent event) {
        Log.d(TAG, "BusEvent.MessageSent");
        XYMessage msg = event.message;
        if (targetId.equals(msg.getTargetId()) && conversationType == XYConversationType.CHATROOM) {
            int errorCode = event.code;
            if (errorCode == 0) {
                liveChatListAdapter.addMessage(msg);
                liveChatListAdapter.notifyDataSetChanged();
                newMessage.messageArrived();
            } else {
                Toast toast = Toast.makeText(getActivity(), R.string.live_send_failed, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }

    private class NewMessageHint {
        private TextView hint;
        private ListView listView;
        private boolean isActive;

        public NewMessageHint(View root, ListView bondView) {
            listView = bondView;

            hint = (TextView) root.findViewById(R.id.new_message);
            hint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listView.setSelection(listView.getBottom());
                }
            });

            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                        hint.setVisibility(View.GONE);
                        isActive = false;
                        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                    } else {
                        isActive = true;
                        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
                    }
                }
            });
        }

        public void messageArrived() {
            if (isActive) {
                hint.setVisibility(View.VISIBLE);
            }
        }
    }
}