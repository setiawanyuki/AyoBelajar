package com.kukuh.ayobelajar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kukuh.ayobelajar.Model.Binatang;
import com.kukuh.ayobelajar.Model.Buah;
import com.kukuh.ayobelajar.R;

import java.util.ArrayList;

/**
 * Created by Kukuh Wijanarko on 21/11/2017.
 */

public class BuahAdapter extends ArrayAdapter<Buah> {
    private ArrayList<Buah> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView imGridBuah;
        TextView tvBuahId, tvBuahEng;
    }

    public BuahAdapter(ArrayList<Buah> data, Context context) {
        super(context, R.layout.custom_list_buah, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Buah buah = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final BuahAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        viewHolder = new BuahAdapter.ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.custom_list_buah, parent, false);
        viewHolder.imGridBuah = convertView.findViewById(R.id.iv_buah);
        viewHolder.tvBuahId = convertView.findViewById(R.id.tv_buah_indonesia);
        viewHolder.tvBuahEng = convertView.findViewById(R.id.tv_buah_English);
        viewHolder.tvBuahId.setText(buah.getBuahIndonesia());
        viewHolder.tvBuahEng.setText(buah.getBuahEnglish());

        Glide.with(mContext)
                .load(buah.getDaftarBuah())
                .into(viewHolder.imGridBuah);
        convertView.setTag(viewHolder);
        return convertView;
    }
}