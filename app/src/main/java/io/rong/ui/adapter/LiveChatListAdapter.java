package io.rong.ui.adapter;

import com.xiaoying.imapi.BaseMessageTemplate;
import com.xiaoying.imapi.message.UIMessage;
import com.xiaoying.imapi.message.XYMessage;
import com.xiaoying.imapi.service.IMService;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import io.rong.liveapp.R;
import io.rong.ui.BaseContainerView;
import io.rong.util.IMUtil;

public class LiveChatListAdapter extends BaseAdapter {

    private static final String TAG = "LiveChatListAdapter";

    private List<UIMessage> uiMessageList = new ArrayList<>();

    public void addMessage(XYMessage message) {
        uiMessageList.add(UIMessage.obtain(message));
    }

    @Override
    public int getCount() {
        return uiMessageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView position = " + position + ", convertView = " + convertView);
        ViewHolder holder;
        final UIMessage data = uiMessageList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_livechat_message, null);
            holder = new ViewHolder();
            holder.baseContainerView = (BaseContainerView) convertView.findViewById(R.id.rc_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        XYMessage msg = data.getMessage();
        IMService imService = IMUtil.getIMService();
        if (imService == null) {
            return null;
        }
        final BaseMessageTemplate template = imService.getMessageTemplate(msg.getContent().getClass());
        if (template != null) {
            View view = holder.baseContainerView.inflate(template, position, msg.getObjectName(), data);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    template.onItemClick(v, position, data);
                }
            });
        }
        return convertView;
    }

    private class ViewHolder {
        public BaseContainerView baseContainerView;
    }
}
