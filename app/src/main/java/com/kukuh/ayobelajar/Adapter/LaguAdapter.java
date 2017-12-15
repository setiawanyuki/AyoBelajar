package com.kukuh.ayobelajar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kukuh.ayobelajar.Model.Lagu;
import com.kukuh.ayobelajar.R;

import java.util.ArrayList;

/**
 * Created by Kukuh Wijanarko on 21/11/2017.
 */

public class LaguAdapter extends ArrayAdapter<Lagu> {

    private ArrayList<Lagu> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtJudulLagu;
    }

    public LaguAdapter(ArrayList<Lagu> data, Context context) {
        super(context, R.layout.custom_list_lagu, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Lagu lagu = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final LaguAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        viewHolder = new LaguAdapter.ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.custom_list_lagu, parent, false);
        viewHolder.txtJudulLagu = convertView.findViewById(R.id.txt_lagu);
        viewHolder.txtJudulLagu.setText(lagu.getJudulLagu());
        convertView.setTag(viewHolder);
        // Return the completed view to render on screen
        return convertView;
    }
}