package com.project.protectpetapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.protectpetapp.R;
import com.project.protectpetapp.model.Breeds;

import java.util.ArrayList;

//https://kitesoft.tistory.com/72
public class ListViewAdapter extends BaseAdapter {

    ArrayList<Breeds> datas;
    LayoutInflater inflater;

    public ListViewAdapter(LayoutInflater layoutInflater, ArrayList<Breeds> breeds) {
        this.datas = breeds;
        this.inflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_breed, null);

        TextView breeds = convertView.findViewById(R.id.item_breeds_tv_breeds);
        breeds.setText(datas.get(position).getBreeze());

        return convertView;
    }
}
