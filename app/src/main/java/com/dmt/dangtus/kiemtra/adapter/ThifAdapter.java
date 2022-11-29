package com.dmt.dangtus.kiemtra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dmt.dangtus.kiemtra.R;
import com.dmt.dangtus.kiemtra.model.Thif;

import java.util.List;

public class ThifAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Thif> thifList;

    public ThifAdapter(Context context, int layout, List<Thif> thifList) {
        this.context = context;
        this.layout = layout;
        this.thifList = thifList;
    }

    @Override
    public int getCount() {
        return thifList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);

            holder = new ViewHolder();
            //anh xa
            holder.txtTen = view.findViewById(R.id.ten_textview);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //gan gia tri
        Thif phepTinh = thifList.get(i);
        holder.txtTen.setText(phepTinh.getTen());

        return view;
    }

    private class ViewHolder {
        TextView txtTen;
    }
}
