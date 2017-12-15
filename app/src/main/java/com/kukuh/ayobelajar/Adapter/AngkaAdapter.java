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
import com.kukuh.ayobelajar.Model.Angka;
import com.kukuh.ayobelajar.Model.Huruf;
import com.kukuh.ayobelajar.R;

import java.util.ArrayList;

/**
 * Created by Kukuh Wijanarko on 21/11/2017.
 */

public class AngkaAdapter extends ArrayAdapter<Angka> {
    private ArrayList<Angka> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView imGridMenu;
        TextView angkaInd, angkaEng;
    }

    public AngkaAdapter(ArrayList<Angka> data, Context context) {
        super(context, R.layout.custom_list_huruf, data);
        this.dataSet = data;
        this.mContext = context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Angka angka = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final AngkaAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        viewHolder = new AngkaAdapter.ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.custom_list_angka, parent, false);
        viewHolder.imGridMenu = convertView.findViewById(R.id.grid_image);
        viewHolder.angkaInd = convertView.findViewById(R.id.txt_angkaInd);
        viewHolder.angkaEng = convertView.findViewById(R.id.txt_angkaEng);
        viewHolder.angkaInd.setText(angka.getAngkaIndo());
        viewHolder.angkaEng.setText(angka.getAngkaEngl());
        Glide.with(mContext)
                .load(angka.getDaftarAngka())
                .into(viewHolder.imGridMenu);
        convertView.setTag(viewHolder);
        return convertView;
    }
}