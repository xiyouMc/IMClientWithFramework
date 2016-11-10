
# IMSDk
基于Rongyun的即时通讯SDK


## Dependency

```
dependencies{
     compile 'com.vivavideo.mobile:imapi:1.61.16101901@aar'
     compile 'com.vivavideo.mobile:imcore:1.39.16101902@aar'
}
```

## Usage

* Step 1---------实现BaseMessageTemplate

```
@TemplateTag(messageContent = XYTextMessage.class)  //必须配Tag，融云SDK有使用到。
public class TextMessageTemplate implements BaseMessageTemplate{

     @Override
     public View getView(View convertView, int position, ViewGroup parent, UIMessage data) {
      //do something...
     }

     @Override
     public void onItemClick(View view, int position, UIMessage data) {

     }

     @Override
     public void onItemLongClick(View view, int position, UIMessage data) {

     }

     @Override
     public void destroyItem(ViewGroup group, Object template) {

     }
}


```

*  Step 2---------注册Message类

```
IMservice imService = new IMServiceImpl();
if (imService != null) {
     Log.d(TAG, "init ImService");
     imService.init(this, APP_KEY);// 公网环境
     if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
         imService.registerMessageTemplate(livePlayerService.getTextMessageTemplate());
         imService.registerMessageType(XYTextMessage.class);
         //注册消息接受回调，本例子通过EventBus实现消息路由
         imService.registerMessageEvent(new XYIMOnReceiveMessageListener() {
             @Override
             public boolean onReceived(XYMessage message, int left) {
                   Log.d("APP", "onReceived left = " + left);
                   LiveIMUtil.getEventBus().post(new BusEvent.MessageReceived(message, left));
                   return false;
             }
         });
     }
}
```

* Step 3---------连接服务
```
imService.connect(TOKEN,XYIMConnectCallback);
imService.setCurrentUserInfo(new XYIMUserInfo(userId,userName,portraitUrl));
```

* Step 4---------构造消息(XYMessage)并发送
```
XYTextMessage content = XYTextMessage.obtain(text);
XYMessage msg = XYMessage.obtain(targetId, XYConversationType, content);
msg.setMessageDirection(XYMessage.MessageDirection.SEND);
//设置该消息用户
XYIMUserInfo myInfo = imService.getCurrentUserInfo();
msg.getContent().setUserInfo(myInfo);
//发送消息，回调发送状态
imService.sendMessage(msg,new IMSendMessageCallback(),new XYIMResultCallback<XYMessage>());

```

* Step 5--------接受消息
```
通过之前注册的XYIMOnReceiveMessageListener处理。
```

        