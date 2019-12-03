package com.truora.serverinfoapp.adapter;

import android.content.Context;
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

        tv_title.setText(domain.getInfoServer().getTitle());
        if(!(domain.getInfoServer().getLogo() == null) && !(domain.getInfoServer().getLogo().isEmpty())){
            new DownloadImageTask(iv_logo)
                    .execute(domain.getInfoServer().getLogo());
        }

        if(!domain.getInfoServer().isDown()){
            iv_is_down.setImageResource(android.R.drawable.presence_online);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
