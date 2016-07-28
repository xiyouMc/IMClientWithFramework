package io.rong.template;

import com.xiaoying.imapi.BaseMessageTemplate;
import com.xiaoying.imapi.api.TemplateTag;
import com.xiaoying.imapi.message.UIMessage;
import com.xiaoying.imapi.message.XYMessage;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.rong.liveapp.R;
import io.rong.liveapp.XYInformationNotificationMessage;

@TemplateTag(messageContent = XYInformationNotificationMessage.class)
public class InformationNotificationMessageTemplate implements BaseMessageTemplate {
    private final static String TAG = "Information";

    @Override
    public View getView(View convertView, int position, ViewGroup parent, UIMessage data) {
        Log.e(TAG, "getView " + position + " " + convertView);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_infor_message, null);
            holder.content = (TextView) convertView.findViewById(R.id.rc_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        XYMessage msg = data.getMessage();
        XYInformationNotificationMessage infoMsg = (XYInformationNotificationMessage) msg.getContent();
        holder.content.setText(infoMsg.getMessage());
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
        TextView content;
    }
}