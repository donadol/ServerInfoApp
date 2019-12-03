package com.truora.serverinfoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.truora.serverinfoapp.R;
import com.truora.serverinfoapp.model.Server;

import java.util.List;

public class ServerAdapter extends ArrayAdapter<Server> {

    public ServerAdapter(@NonNull Context context, @NonNull List<Server> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Server server = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_server, parent, false);
        }

        TextView tv_address = convertView.findViewById(R.id.tv_address);
        TextView tv_grade = convertView.findViewById(R.id.tv_grade);
        TextView tv_country = convertView.findViewById(R.id.tv_country);
        TextView tv_owner = convertView.findViewById(R.id.tv_owner);

        tv_address.setText(server.getAddress());
        tv_grade.setText(server.getGrade());
        tv_country.setText(server.getCountry());
        tv_owner.setText(server.getOwner());

        return convertView;
    }
}
