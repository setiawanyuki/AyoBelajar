package com.kukuh.ayobelajar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kukuh.ayobelajar.Model.Huruf;
import com.kukuh.ayobelajar.R;

import java.util.ArrayList;

/**
 * Created by Kukuh Wijanarko on 21/11/2017.
 */

public class HurufAdapter extends ArrayAdapter<Huruf> {
    private ArrayList<Huruf> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView imGridMenu;
    }

    public HurufAdapter(ArrayList<Huruf> data, Context context) {
        super(context, R.layout.custom_list_huruf, data);
        this.dataSet = data;
        this.mContext = context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Huruf huruf = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final HurufAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        viewHolder = new HurufAdapter.ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.custom_list_huruf, parent, false);
        viewHolder.imGridMenu = convertView.findViewById(R.id.grid_image);
        Glide.with(mContext)
                .load(huruf.getDaftarHuruf())
                .into(viewHolder.imGridMenu);
        convertView.setTag(viewHolder);
        return convertView;
    }
}

