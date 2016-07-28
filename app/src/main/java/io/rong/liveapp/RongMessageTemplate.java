package io.rong.liveapp;

import com.xiaoying.imapi.BaseMessageTemplate;
import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.api.TemplateTag;
import com.xiaoying.imapi.message.UIMessage;
import com.xiaoying.imapi.message.XYEmoji;
import com.xiaoying.imapi.message.XYMessage;
import com.xiaoying.imapi.message.XYTextMessage;
import com.xiaoying.imcore.liveapp.xyim.rongyun.XYIMRongyunClient;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xiyoumc on 16/7/28.
 */
@TemplateTag(messageContent = XYIMRongyunClient.class)
public class RongMessageTemplate implements BaseMessageTemplate {
    private final static String TAG = "TextMessageTemplate";

    @Override
    public View getView(View convertView, int position, ViewGroup parent, UIMessage data) {
        Log.e(TAG, "getView " + position + " " + convertView);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_text_message, null);
            holder.username = (TextView) convertView.findViewById(R.id.rc_username);
            holder.content = (TextView) convertView.findViewById(R.id.rc_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        XYMessage msg = data.getMessage();
        XYIMUserInfo info = msg.getContent().getUserInfo();
        if (info != null) {
            holder.username.setText(info.getName() + ":");
        } else {
            holder.username.setText(msg.getSenderUserId() + ":");
        }

        if (msg.getMessageDirection() == XYMessage.MessageDirection.SEND) {
            holder.username.setTextColor(parent.getContext().getResources().getColor(R.color.live_me));
        } else if (msg.getMessageDirection() == XYMessage.MessageDirection.RECEIVE) {
            holder.username.setTextColor(parent.getContext().getResources().getColor(R.color.live_other));
        }

        XYTextMessage textMsg = (XYTextMessage) msg.getContent();
        CharSequence text = XYEmoji.ensure(parent.getContext(), textMsg.getContent());
        holder.content.setText(text);
        return convertView;
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

    private class ViewHolder {
        TextView username;
        TextView content;
    }
}
