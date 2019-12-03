package com.truora.serverinfoapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.truora.serverinfoapp.R;
import com.truora.serverinfoapp.model.Domain;
import com.truora.serverinfoapp.utils.DownloadImageTask;
import com.truora.serverinfoapp.utils.Utils;

import java.util.List;

public class DomainAdapter extends ArrayAdapter<Domain> {

    public DomainAdapter(@NonNull Context context, @NonNull List<Domain> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Domain domain = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_domain, parent, false);
        }

        TextView tv_title = convertView.findViewById(R.id.tv_title);
        ImageView iv_logo = convertView.findViewById(R.id.iv_logo);
        ImageView iv_is_down = convertView.findViewById(R.id.iv_is_down);

        String title=domain.getInfoServer().getTitle();
        if(title.contains(" ") && title.length()>65) {
            title = title.substring(0, title.indexOf(" "));
        }
        tv_title.setText(title);

        Utils.downloadImage(domain.getInfoServer().getLogo(), domain.getHost(), iv_logo);

        if(!domain.getInfoServer().isDown()){
            iv_is_down.setImageResource(android.R.drawable.presence_online);
        }

        return convertView;
    }
}
