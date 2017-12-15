package com.kukuh.ayobelajar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kukuh.ayobelajar.Model.Binatang;
import com.kukuh.ayobelajar.R;

import java.util.ArrayList;

/**
 * Created by Kukuh Wijanarko on 21/11/2017.
 */

public class BinatangAdapter extends ArrayAdapter<Binatang> {
    private ArrayList<Binatang> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView imGridBinatang;
        TextView tvBinatangId, tvBinatangEng;
    }

    public BinatangAdapter(ArrayList<Binatang> data, Context context) {
        super(context, R.layout.custom_list_binatang, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Binatang binatang = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final BinatangAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        viewHolder = new BinatangAdapter.ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.custom_list_binatang, parent, false);
        viewHolder.imGridBinatang =  convertView.findViewById(R.id.iv_binatang);
        viewHolder.tvBinatangId = convertView.findViewById(R.id.tv_binatang_indonesia);
        viewHolder.tvBinatangEng = convertView.findViewById(R.id.tv_binatang_English);
        viewHolder.tvBinatangId.setText(binatang.getBinatangIndonesia());
        viewHolder.tvBinatangEng.setText(binatang.getBinatangEnglish());

        Glide.with(mContext)
                .load(binatang.getDaftarBinatang())
                .into(viewHolder.imGridBinatang);
        convertView.setTag(viewHolder);
        return convertView;
    }
}