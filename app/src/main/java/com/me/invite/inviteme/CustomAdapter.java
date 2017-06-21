package com.me.invite.inviteme;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jared on 6/19/2017.
 */

// Found a tutorial on how to create a custom array adapter and implement it in our application
// Guide:  https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView

public class CustomAdapter extends ArrayAdapter<Shindig> {


    public CustomAdapter(Context context, ArrayList<Shindig> shindigs) {
        super(context, 0, shindigs);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Shindig shindig = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customized_event_box, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView desc = (TextView) convertView.findViewById(R.id.txtDesc);
        TextView host = (TextView) convertView.findViewById(R.id.txtHost);
        TextView time = (TextView) convertView.findViewById(R.id.txtTime);

        title.setText(shindig.getTitle());
        desc.setText(shindig.getDescription());
        host.setText(shindig.getHost().getUserName());
        time.setText(shindig.getDate());

        return convertView;
    }
}
