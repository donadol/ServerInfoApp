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
import com.truora.serverinfoapp.utils.utils;

import java.util.List;

public class DomainAdapter extends ArrayAdapter<Domain> {

    public DomainAdapter(@NonNull Context context, int resource, @NonNull List<Domain> objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Domain domain = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_domain, parent, false);
        }
        // Lookup view for data population
        TextView tv_title = convertView.findViewById(R.id.tv_title);
        ImageView iv_logo = convertView.findViewById(R.id.iv_logo);
        ImageView iv_is_down = convertView.findViewById(R.id.iv_is_down);

        // Populate the data into the template view using the data object
        tv_title.setText(domain.getInfoServer().getTitle());

        if(domain.getInfoServer().getLogo() == null || domain.getInfoServer().getLogo() == ""){
            //poner una x
        } else {
            new DownloadImageTask((ImageView) iv_logo)
                    .execute(domain.getInfoServer().getLogo());
        }

        if(!domain.getInfoServer().isDown()){
            iv_is_down.setImageResource(android.R.drawable.presence_online);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
