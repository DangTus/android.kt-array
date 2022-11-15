package com.dmt.dangtus.kiemtra.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dmt.dangtus.kiemtra.R;
import com.dmt.dangtus.kiemtra.model.CongAn;

import java.util.List;

public class CongAnAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CongAn> congAnList;

    public CongAnAdapter(Context context, int layout, List<CongAn> congAnList) {
        this.context = context;
        this.layout = layout;
        this.congAnList = congAnList;
    }

    @Override
    public int getCount() {
        return congAnList.size();
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
            holder.txtChucVu = view.findViewById(R.id.chucvu_textview);
            holder.txtCongTac = view.findViewById(R.id.congtac_textview);
            holder.txtQuocGia = view.findViewById(R.id.quocgia_textview);
            holder.rbSao = view.findViewById(R.id.sao_ratingbar);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //gan gia tri
        CongAn congAn = congAnList.get(i);
        holder.txtTen.setText(congAn.getTen());
        holder.txtChucVu.setText(congAn.getCapBac());
        holder.txtCongTac.setText(congAn.getNoiCongTac());
        holder.txtQuocGia.setText(congAn.getQuocGia());
        holder.rbSao.setRating(congAn.getSoSao());

        return view;
    }

    private class ViewHolder {
        TextView txtTen, txtChucVu, txtCongTac, txtQuocGia;
        RatingBar rbSao;
    }
}
